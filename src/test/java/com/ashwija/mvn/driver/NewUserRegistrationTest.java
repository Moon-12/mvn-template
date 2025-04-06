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

import static org.junit.jupiter.api.Assertions.*;

class NewUserRegistrationTest {
    Connection h2Connection;
    private UserProfileDao userProfileDao = new UserProfileDao();
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
    }

    @AfterEach
    void tearDown() throws SQLException, IOException {
        // Clean up the database
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE user_profile");
        }
        DatabaseConnection.con.close();
        if (inputStream != null) {
            inputStream.close();
        }
        CentralContext.resetToMainMenu();
    }

    @Test
    void execute() throws SQLException {
        inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("NewUserRegistrationTestInput.txt");

        mainDriver.execute(inputStream);
        try (PreparedStatement stmt = DatabaseConnection.con.prepareStatement(
                "select * from user_profile")) {
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int totalRows = resultSet.getRow();
            assertEquals(1, totalRows, "One row was inserted");
        }
    }
}