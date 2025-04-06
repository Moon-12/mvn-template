package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.common.LoginStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginTest {
    Connection h2Connection;
    private UserProfileDao userProfileDao = new UserProfileDao();

    @BeforeEach
    public void setUp() throws SQLException {
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
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Clean up the database
        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("DROP TABLE user_profile");
        }
        DatabaseConnection.con.close();
    }

    //login fails
    @Test
    void loginFails() throws SQLException {
        List<Object> inputList = List.of("ash6?", "test");
        int checksumTotal = userProfileDao.login(inputList);
        //if user not found
        assertEquals(LoginStatus.FAILURE.getCode(), checksumTotal);

    }

    //login fails password does not macth
    @Test
    void loginError() throws SQLException {
        //register user first
        List<Object> inputList = List.of("ash6?", "test", "F", "uhcl");
        int rowsChanged = userProfileDao.save(inputList);

        int checksumTotal = userProfileDao.login(List.of(inputList.get(0), "test1"));

        //if user found but password does not match
        assertEquals(LoginStatus.ERROR.getCode(), checksumTotal);

    }

    //login success
    @Test
    void loginSuccess() throws SQLException {
        //register user first
        List<Object> inputList = List.of("ash6?", "test", "F", "uhcl");
        int rowsChanged = userProfileDao.save(inputList);

        int checksumTotal = userProfileDao.login(List.of(inputList.get(0), inputList.get(1)));
        assertEquals(LoginStatus.SUCCESS.getCode(), checksumTotal);

    }
}