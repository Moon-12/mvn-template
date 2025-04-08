package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.model.CommentEntity;
import com.ashwija.mvn.model.PopularHashTagEntity;
import com.ashwija.mvn.model.PostEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    PopularHashTagEntity getTagEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new PopularHashTagEntity(resultSet.getString("hashtag_id"), resultSet.getInt("frequency"));
    }

    public String getPopularHashTagSql() {
        return "select hashtag_id,count(1) frequency " +
                "from post " +
                "group by hashtag_id " +
                "order by count(1) desc " +
                "limit 2";
    }

    public List<PopularHashTagEntity> getPopularHashTags() throws SQLException {
        List<PopularHashTagEntity> popularHashTagEntityList = new ArrayList<>();
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getPopularHashTagSql());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {  // Use rs.next() to move cursor and check for results
            popularHashTagEntityList.add(this.getTagEntityFromResultSet(rs));
        }
        return popularHashTagEntityList;
    }

    public String getPostByHashtagSql() {
        return "select * from post where hashtag_id=?";
    }

    @Override
    PostEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new PostEntity(resultSet.getInt("id"), resultSet.getString("user_id"), resultSet.getString("content"), resultSet.getTimestamp("created_at"));
    }


    public List<PostEntity> getPostsByHashTags(String hashtag) throws SQLException {
        List<PostEntity> postEntityList = new ArrayList<>();
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getPostByHashtagSql());
        pstmt.setString(1, hashtag);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {  // Use rs.next() to move cursor and check for results
            postEntityList.add(this.getEntityFromResultSet(rs));
        }
        CommentDao commentDao = new CommentDao();
        Optional<List<CommentEntity>> commentEntityList;

        for (PostEntity postEntity : postEntityList) {
            try {
                commentEntityList = commentDao.getCommentListByPostId(postEntity.getId());
                commentEntityList.ifPresent(postEntity::setCommentEntityList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return postEntityList;
    }
}
