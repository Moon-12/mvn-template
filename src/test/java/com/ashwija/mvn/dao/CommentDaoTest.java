package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.common.DateAndTime;
import com.ashwija.mvn.model.CommentEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CommentDaoTest {
    Connection h2Connection;
    private final CommentDao commentDao = new CommentDao();

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize H2 in-memory database
        this.h2Connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        DatabaseConnection.con = h2Connection;
        // Create the table for testing
        try (Statement stmt = h2Connection.createStatement()) {
            stmt.execute("CREATE TABLE `comment` (" +
                    "  `id` int AUTO_INCREMENT NOT NULL," +
                    "  `content` varchar(100)," +
                    "  `post_id` int, " +
                    "  `user_id` varchar(20)," +
                    "  `created_at` datetime" +
                    ")");
        }
        commentDao.save(List.of("good morning", 10, 1, DateAndTime.getCurrentTimestamp()));
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Clean up the database
        try (Statement stmt = DatabaseConnection.con.createStatement()) {
            stmt.execute("DROP TABLE comment");
        }
        DatabaseConnection.con.close();
    }

    @Test
    void getCommentListByPostId() throws SQLException {
        Integer postID = 10;
        Optional<List<CommentEntity>> commentEntityList = commentDao.getCommentListByPostId(postID);
        assertTrue(commentEntityList.isPresent(), "Comment list should be present for post ID: " + postID);
    }
}