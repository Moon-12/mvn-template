package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.model.CommentEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentDao extends AppDao<CommentEntity> {

    @Override
    CommentEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new CommentEntity(resultSet.getString("content"), resultSet.getString("user_id"), resultSet.getTimestamp("created_at"));
    }

    @Override
    public String getInsertSql() {
        return "insert into comment(content,post_id,user_id,created_at) values(?,?,?,?)";
    }

    public String getCommentByPostIdSql() {
        return "select * from comment where post_id=?";
    }

    public Optional<List<CommentEntity>> getCommentListByPostId(Integer postId) throws SQLException {
        List<CommentEntity> commentEntityList = new ArrayList<>();
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getCommentByPostIdSql());
        pstmt.setInt(1, postId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            commentEntityList.add(this.getEntityFromResultSet(rs));
        }
        return Optional.ofNullable(commentEntityList.isEmpty() ? null : commentEntityList);
    }
}
