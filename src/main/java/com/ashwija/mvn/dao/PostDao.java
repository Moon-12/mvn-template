package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.model.PostEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class PostDao extends AppDao<PostEntity> {
    @Override
    String getInsertSql() {
        return "insert into POST(content,user_id,created_at) values(?,?,?)";
    }

    String getInsertSqlWithTag() {
        return "insert into POST(content,hashtag_id,user_id,created_at) values(?,?,?,?)";
    }

    @Override
    public int save(List<Object> attributes) throws SQLException {
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(attributes.size() == 3 ? this.getInsertSql() : this.getInsertSqlWithTag());
        for (int i = 0; i < attributes.size(); i++) {
            Object attr = attributes.get(i);
            if (attr instanceof Timestamp timestamp) {
                pstmt.setTimestamp(i + 1, timestamp);
            } else if (attr instanceof Integer integer) {
                pstmt.setInt(i + 1, integer);
            } else {
                pstmt.setString(i + 1, attr.toString());
            }
        }
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected;
    }

    @Override
    String getSaveSuccessMessage() {
        return "Post created successfully!";
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
    PostEntity getEntityFromResultSet(ResultSet resultSet) {
        return null;
    }
}
