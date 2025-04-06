package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileDaoTest {
    Connection h2Connection;
    private final UserProfileDao userProfileDao = new UserProfileDao();

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
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Clean up the database
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE user_profile");
        }
        DatabaseConnection.con.close();
        CentralContext.logOut();
    }

    @Test
    void save() throws SQLException {
        List<Object> inputList = List.of("swap", "test", "M", "uhcl");
        int rowsChanged = userProfileDao.save(inputList);
        assertEquals(1, rowsChanged, "! record inserted");
    }
}