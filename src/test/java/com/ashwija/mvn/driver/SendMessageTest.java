package com.ashwija.mvn.driver;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.dao.MessageDao;
import com.ashwija.mvn.dao.UserProfileDao;
import org.h2.engine.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendMessageTest {
    Connection h2Connection;
    private UserProfileDao userProfileDao = new UserProfileDao();
    private MessageDao messageDao = new MessageDao();
    MainDriver mainDriver = new MainDriver();

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize H2 in-memory database
        this.h2Connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        DatabaseConnection.con = h2Connection;
        // Create the table for testing
        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("CREATE TABLE `user_profile` (\n" +
                    "  `id` varchar(10) NOT NULL,\n" +
                    "  `password` varchar(20) NOT NULL,\n" +
                    "  `gender` varchar(10) NOT NULL,\n" +
                    "  `school` varchar(20) NOT NULL\n" +
                    ")");
        }
        List<Object> inputList = List.of("ash6?", "test", "F", "uhcl");
        userProfileDao.save(inputList);

        List<Object> inputList1 = List.of("swap6?", "test", "M", "uhcl");
        userProfileDao.save(inputList1);

        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("CREATE TABLE message (\n" +
                    "    id INT AUTO_INCREMENT NOT NULL,\n" +
                    "    content VARCHAR(100),\n" +
                    "    sender_id VARCHAR(20),\n" +
                    "    receiver_id VARCHAR(20),\n" +
                    "    created_at DATETIME\n" +
                    ")");
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
        DatabaseConnection.con.close();
    }

    @Test
    void execute() throws SQLException {
        InputStream inputStream = getClass()
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
