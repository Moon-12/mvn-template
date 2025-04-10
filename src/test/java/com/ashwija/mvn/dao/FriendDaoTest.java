package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.DateAndTime;
import com.ashwija.mvn.model.FriendEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FriendDaoTest {
    Connection h2Connection;
    FriendDao friendDao = new FriendDao();

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize H2 in-memory database
        this.h2Connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        DatabaseConnection.con = h2Connection;
        // Create the table for testing
        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("CREATE TABLE `friend` (" +
                    "  `id` int AUTO_INCREMENT NOT NULL," +
                    "  `sender_id` varchar(20)," +
                    "  `receiver_id` varchar(20)," +
                    "  `created_at` datetime," +
                    "  `status` varchar(25) DEFAULT 'accepted'" +
                    ")");
        }
        friendDao.save(List.of("ash", "swap", DateAndTime.getCurrentTimestamp()));
        friendDao.save(List.of("ash", "mikasa?", DateAndTime.getCurrentTimestamp()));
        CentralContext.setLoggedInUserID("ash");
    }


    @AfterEach
    void tearDown() throws SQLException {
        // Clean up the database
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE friend");
        }
        DatabaseConnection.con.close();
        CentralContext.logOut();
    }

    @Test
    void getListOfFriends() throws SQLException {
        List<FriendEntity> friendEntityList = friendDao.getListOfFriends();
        assertEquals(2, friendEntityList.size(), "Logged in user has 2 friends");
    }
}