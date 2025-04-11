package com.ashwija.mvn.driver;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.dao.UserProfileDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendMessageTest {
    Connection h2Connection;
    private final UserProfileDao userProfileDao = new UserProfileDao();
    MainDriver mainDriver = new MainDriver();
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
        }
        List<Object> inputList = List.of("ash6?", "test", "F", "uhcl");
        userProfileDao.save(inputList);

        List<Object> inputList1 = List.of("swap6?", "test", "M", "uhcl");
        userProfileDao.save(inputList1);

        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("""
                    CREATE TABLE message (
                        id INT AUTO_INCREMENT NOT NULL,
                        content VARCHAR(100),
                        sender_id VARCHAR(20),
                        receiver_id VARCHAR(20),
                        created_at DATETIME,
                        status VARCHAR(20) DEFAULT 'unread'
                    )""");
        }

        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("""
                    CREATE TABLE `post` (
                      `id` int NOT NULL,
                      `content` varchar(200) DEFAULT NULL,
                      `hashtag_id` varchar(50) DEFAULT NULL,
                      `user_id` varchar(20) DEFAULT NULL,
                      `created_at` datetime DEFAULT NULL
                    )""");
        }
        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("""
                    CREATE TABLE friend (
                        id INT AUTO_INCREMENT NOT NULL,
                        sender_id VARCHAR(20),
                        receiver_id VARCHAR(20),
                        created_at DATETIME,
                        status varchar2(20) default 'pending'
                    )""");
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Clean up the database
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE user_profile");
        }
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE message");
        }
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE post");
        }
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE friend");
        }
        DatabaseConnection.con.close();
        CentralContext.logOut();
    }

    @Test
    void execute() throws SQLException {
        inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("NewMessageTestInput.txt");

        mainDriver.execute(inputStream);
        try (PreparedStatement stmt = DatabaseConnection.con.prepareStatement(
                "select * from message")) {
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int totalRows = resultSet.getRow();
            assertEquals(1, totalRows, "One row was inserted");
        }
    }
}
