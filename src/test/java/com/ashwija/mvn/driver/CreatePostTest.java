package com.ashwija.mvn.driver;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.dao.UserProfileDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatePostTest {
    Connection h2Connection;
    private final UserProfileDao userProfileDao = new UserProfileDao();
    MainDriver mainDriver = new MainDriver();
    InputStream inputStream;

    @BeforeEach
    public void setUp() throws SQLException {
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
        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("""
                    CREATE TABLE `post` (
                        `id` INT AUTO_INCREMENT NOT NULL,
                        `content` VARCHAR(200),
                        `hashtag_id` VARCHAR(50),
                        `user_id` VARCHAR(20),
                        `created_at` DATETIME
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
    public void tearDown() throws SQLException, IOException {
        // Clean up the database
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE user_profile");
            stmt.execute("DROP TABLE post");
            stmt.execute("DROP TABLE friend");
        }
        DatabaseConnection.con.close();
        if (inputStream != null) {
            inputStream.close();
        }
        CentralContext.logOut();
    }

    @Test
    void save() throws SQLException {
        inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("NewPostTestInput.txt");
        mainDriver.execute(inputStream);
        try (PreparedStatement stmt = DatabaseConnection.con.prepareStatement(
                "select * from post")) {
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int totalRows = resultSet.getRow();
            assertEquals(1, totalRows, "One row was inserted");
        }
    }
}
