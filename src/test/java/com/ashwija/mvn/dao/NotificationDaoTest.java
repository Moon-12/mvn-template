package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.DateAndTime;
import com.ashwija.mvn.model.NotificationEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationDaoTest {
    Connection h2Connection;
    FriendDao friendDao = new FriendDao();
    MessageDao messageDao = new MessageDao();
    NotificationDao notificationDao = new NotificationDao();

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
                    "  `status` varchar(25) DEFAULT 'pending'" +
                    ")");
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
        friendDao.save(List.of("swap", "ash", DateAndTime.getCurrentTimestamp()));
        messageDao.save(List.of("swap", "hello", "ash", DateAndTime.getCurrentTimestamp()));
        CentralContext.setLoggedInUserID("swap");
    }


    @AfterEach
    void tearDown() throws SQLException {
        // Clean up the database
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE friend");
            stmt.execute("DROP TABLE message");
        }
        DatabaseConnection.con.close();
        CentralContext.logOut();
    }

    @Test
    void getAllActiveNotifications() throws SQLException {
        List<NotificationEntity> notificationEntityList = notificationDao.getAllActiveNotifications();
        assertEquals(2, notificationEntityList.size(), "2 new notifications received");
    }
}