package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.model.FriendEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendDao extends AppDao<FriendEntity> {
    @Override
    String getInsertSql() {
        return "insert into FRIEND(receiver_id,sender_id,created_at) values(?,?,?)";
    }


    @Override
    public String getSaveSuccessMessage() {
        return "Friend Request Sent Successfully!";
    }

    @Override
    public String getValidationFailureMessage() {
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

    String getFriendListSql() {
        return "SELECT receiver_id AS friend_id, created_at " +
                "FROM FRIEND " +
                "WHERE sender_id = ? AND status = 'accept' " +
                "UNION " +
                "SELECT sender_id AS friend_id, created_at " +
                "FROM FRIEND " +
                "WHERE receiver_id = ? AND status = 'accept'";
    }

    @Override
    FriendEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new FriendEntity(resultSet.getString("friend_id"));
    }

    public List<FriendEntity> getListOfFriends() throws SQLException {
        List<FriendEntity> friendEntityList = new ArrayList<>();
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getFriendListSql());
        pstmt.setString(1, CentralContext.getLoggedInUserID());
        pstmt.setString(2, CentralContext.getLoggedInUserID());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            friendEntityList.add(getEntityFromResultSet(rs));
        }
        return friendEntityList;
    }
}
