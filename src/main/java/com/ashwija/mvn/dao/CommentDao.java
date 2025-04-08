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
    String getInsertSql() {
        return "";
    }

    @Override
    String getSaveSuccessMessage() {
        return "";
    }

    @Override
    String getValidationFailureMessage() {
        return "";
    }

    @Override
    String getDeleteSql() {
        return "";
    }

    @Override
    String getFetchSql() {
        return "";
    }

    @Override
    String getFetchAllSql() {
        return "";
    }

    @Override
    CommentEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new CommentEntity(resultSet.getString("content"), resultSet.getString("user_id"), resultSet.getTimestamp("created_at"));
    }


    public String getCommentByPostIdSql() {
        return "select * from comment where post_id=?";
    }

    public Optional<List<CommentEntity>> getCommentListByPostId(Integer postId) throws SQLException {
        Optional<List<CommentEntity>> commentEntityList = Optional.of(new ArrayList<>());
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getCommentByPostIdSql());
        pstmt.setInt(1, postId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            commentEntityList.get().add(this.getEntityFromResultSet(rs));
        }
        return commentEntityList;
    }
}
