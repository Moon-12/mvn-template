package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.model.FriendEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendDao extends AppDao<FriendEntity> implements WhoTransformer {
    @Override
    public String getInsertSql() {
        return "insert into FRIEND(receiver_id,sender_id,created_at) values(?,?,?)";
    }

    @Override
    public String getSaveSuccessMessage() {
        return "Friend Request Sent Successfully!";
    }

    @Override
    public String getSaveFailureMessage() {
        return "Friend request could not be sent. Already friends or you have a pending request from user";
    }

    String getFriendListSql() {
        return "SELECT receiver_id AS friend_id, created_at " +
                "FROM FRIEND " +
                "WHERE sender_id = ? AND status = 'ACCEPTED' " +
                "UNION " +
                "SELECT sender_id AS friend_id, created_at " +
                "FROM FRIEND " +
                "WHERE receiver_id = ? AND status = 'ACCEPTED'";
    }

    String getValidateFriendRequestSql() {
        return "select 0 from friend where  ((receiver_id=? and sender_id=?) or (receiver_id=? and sender_id=?))  and status in ('ACCEPTED','PENDING')";
    }

    @Override
    public boolean validateInput(List<Object> attributes) {
        try {
            PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getValidateFriendRequestSql());
            pstmt.setString(1, attributes.get(0).toString());
            pstmt.setString(2, attributes.get(1).toString());
            pstmt.setString(3, attributes.get(1).toString());
            pstmt.setString(4, attributes.get(0).toString());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            //check if no request exists
            return rs.getRow() == 0;
        } catch (SQLException exception) {
            return false;
        }
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

    @Override
    public List<Object> transform(List<Object> inputList) {
        inputList = this.whoTransform(inputList);
        return super.transform(inputList);
    }
}
