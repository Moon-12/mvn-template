package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.model.NotificationEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao extends AppDao<NotificationEntity> {

    @Override
    String getInsertSql() {
        return "insert into NOTIFICATION(type,content,sender_id,receiver_id) values(?,?,?,?)";
    }

    String getInsertWithoutContentSQl() {
        return "insert into NOTIFICATION(type,sender_id,receiver_id) values(?,?,?)";
    }

    public int save(List<Object> attributes) throws SQLException {
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(attributes.size() == 4 ? this.getInsertSql() : this.getInsertWithoutContentSQl());
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
        return pstmt.executeUpdate();

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
    NotificationEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new NotificationEntity(resultSet.getString("sender_id"), resultSet.getString("content"), resultSet.getString("type"));
    }

    public String getAllActiveNotificationSql() {
        return "select * from notification where status='active' and receiver_id=? ";
    }

    public List<NotificationEntity> getAllActiveNotifications() throws SQLException {
        List<NotificationEntity> notificationEntityList = new ArrayList<>();
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getAllActiveNotificationSql());
        pstmt.setString(1, CentralContext.getLoggedInUserID());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            notificationEntityList.add(this.getEntityFromResultSet(rs));
        }
        return notificationEntityList;
    }
}
