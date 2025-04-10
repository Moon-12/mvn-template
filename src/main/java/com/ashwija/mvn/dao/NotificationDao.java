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
    NotificationEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new NotificationEntity(resultSet.getString("sender_id"), resultSet.getString("type"), resultSet.getInt("object_id"));
    }

    public String getAllActiveNotificationSql() {
        return "select notification.* from (select 'MESSAGE' as type, m.id as object_id,m.sender_id,m.created_at " +
                "from message m where m.receiver_id=? and m.status='unread' " +
                "UNION ALL " +
                "select 'FRIEND' as type,f.id as object_id,f.sender_id,f.created_at " +
                "from friend f where f.receiver_id=? and f.status='pending') notification " +
                "order by created_at desc";
    }

    public List<NotificationEntity> getAllActiveNotifications() throws SQLException {
        List<NotificationEntity> notificationEntityList = new ArrayList<>();
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getAllActiveNotificationSql());
        pstmt.setString(1, CentralContext.getLoggedInUserID());
        pstmt.setString(2, CentralContext.getLoggedInUserID());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            notificationEntityList.add(this.getEntityFromResultSet(rs));
        }
        return notificationEntityList;
    }
}
