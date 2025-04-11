package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.DateAndTime;
import com.ashwija.mvn.model.PopularHashTagEntity;
import com.ashwija.mvn.model.PostEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDaoTest {
    Connection h2Connection;
    PostDao postDao = new PostDao();
    FriendDao friendDao = new FriendDao();

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize H2 in-memory database
        this.h2Connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        DatabaseConnection.con = h2Connection;
        // Create the table for testing
        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("""
                    CREATE TABLE `post` (
                        `id` INT AUTO_INCREMENT NOT NULL,
                        `content` VARCHAR(200),
                        `hashtag_id` VARCHAR(50),
                        `user_id` VARCHAR(20),
                        `created_at` DATETIME
                    )""");
            stmt.execute("CREATE TABLE `comment` (" +
                    "  `id` int AUTO_INCREMENT NOT NULL," +
                    "  `content` varchar(100)," +
                    "  `post_id` int, " +
                    "  `user_id` varchar(20)," +
                    "  `created_at` datetime" +
                    ")");
            stmt.execute("CREATE TABLE `friend` (" +
                    "  `id` int AUTO_INCREMENT NOT NULL," +
                    "  `sender_id` varchar(20)," +
                    "  `receiver_id` varchar(20)," +
                    "  `created_at` datetime," +
                    "  `status` varchar(25) DEFAULT 'accepted'" +
                    ")");
        }
        postDao.save(List.of("good morning", "#newday", "swap", DateAndTime.getCurrentTimestamp()));
        postDao.save(List.of("good morning", "#newday", "sam", DateAndTime.getCurrentTimestamp()));
        postDao.save(List.of("good morning", "#goodday", "levi", DateAndTime.getCurrentTimestamp()));
        postDao.save(List.of("good morning", "#morning", "ash", DateAndTime.getCurrentTimestamp()));
        postDao.save(List.of("hello", "#newday", "swap", DateAndTime.getCurrentTimestamp()));
        postDao.save(List.of("fun day", "#morning", "mikasa", DateAndTime.getCurrentTimestamp()));

        friendDao.save(List.of("ash", "swap", DateAndTime.getCurrentTimestamp()));
        friendDao.save(List.of("ash", "mikasa", DateAndTime.getCurrentTimestamp()));
        CentralContext.setLoggedInUserID("ash");
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Clean up the database
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE post");
            stmt.execute("DROP TABLE comment");
            stmt.execute("DROP TABLE friend");
        }
        DatabaseConnection.con.close();
        CentralContext.logOut();
    }

    @Test
    void getPopularHashTags() throws SQLException {
        List<PopularHashTagEntity> popularHashTagEntityList = postDao.getPopularHashTags();
        assertEquals("#newday", popularHashTagEntityList.get(0).getName(), "1st popular");
        assertEquals("#morning", popularHashTagEntityList.get(1).getName(), "2nd popular");
    }

    @Test
    void getPostsByHashTags() throws SQLException {
        List<PostEntity> postEntityList = postDao.getPostsByHashTags("#newday");
        assertEquals(3, postEntityList.size(), "3 posts with hashtag #newday");
    }

    @Test
    void get2PostsFromFriends() throws SQLException {
        List<PostEntity> postEntityList = postDao.get2LatestPostsFromFriends();
        assertEquals(2, postEntityList.size(), "Total of 2 posts , 1 per each friend");
        assertEquals("fun day", postEntityList.get(0).getContent(), "This is the first post");
        assertEquals("hello", postEntityList.get(1).getContent(), "This is the second post");
    }
}