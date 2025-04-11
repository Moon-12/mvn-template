package com.ashwija.mvn.driver;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.DateAndTime;
import com.ashwija.mvn.dao.MessageDao;
import com.ashwija.mvn.dao.UserProfileDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationReplyMessageTest {
    Connection h2Connection;
    UserProfileDao userProfileDao = new UserProfileDao();
    MainDriver mainDriver = new MainDriver();
    MessageDao messageDao = new MessageDao();
    InputStream inputStream;

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize H2 in-memory database
        this.h2Connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        DatabaseConnection.con = h2Connection;
        // Create the table for testing
        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("""
                    CREATE TABLE `user_profile` (
                      `id` varchar(10) NOT NULL,
                      `password` varchar(20) NOT NULL,
                      `gender` varchar(10) NOT NULL,
                      `school` varchar(20) NOT NULL
                    )""");
            stmt.execute("""
                    CREATE TABLE `post` (
                        `id` INT AUTO_INCREMENT NOT NULL,
                        `content` VARCHAR(200),
                        `hashtag_id` VARCHAR(50),
                        `user_id` VARCHAR(20),
                        `created_at` DATETIME
                    )""");
            stmt.execute("CREATE TABLE `friend` (" +
                    "  `id` int AUTO_INCREMENT NOT NULL," +
                    "  `sender_id` varchar(20)," +
                    "  `receiver_id` varchar(20)," +
                    "  `created_at` datetime," +
                    "  `status` varchar(25) DEFAULT 'PENDING'" +
                    ")");
            stmt.execute("""
                    CREATE TABLE message (
                        id INT AUTO_INCREMENT NOT NULL,
                        content VARCHAR(100),
                        sender_id VARCHAR(20),
                        receiver_id VARCHAR(20),
                        created_at DATETIME,
                        status VARCHAR(20) DEFAULT 'UNREAD'
                    )""");
        }
        List<Object> inputList = List.of("ash", "test", "F", "uhcl");
        userProfileDao.save(inputList);

        messageDao.save(List.of("ash", "hello", "swap", DateAndTime.getCurrentTimestamp()));

    }

    @AfterEach
    void tearDown() throws SQLException {
        // Clean up the database
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE friend");
            stmt.execute("DROP TABLE message");
            stmt.execute("DROP TABLE post");
            stmt.execute("DROP TABLE user_profile");
        }
        DatabaseConnection.con.close();
        CentralContext.logOut();
    }


    @Test
    void execute() throws SQLException {
        inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("NotificationMessageReplyTest.txt");
        mainDriver.execute(inputStream);
        try (PreparedStatement stmt = DatabaseConnection.con.prepareStatement(
                "select * from message")) {
            ResultSet resultSet = stmt.executeQuery();
            int totalRows = 0;
            while (resultSet.next()) {
                totalRows++;
            }
            assertEquals(2, totalRows, "reply message was added");
        }
    }
}
