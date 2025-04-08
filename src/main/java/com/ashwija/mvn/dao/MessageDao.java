package com.ashwija.mvn.dao;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.NotificationType;
import com.ashwija.mvn.model.MessageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MessageDao extends AppDao<MessageEntity> {
    @Override
    String getInsertSql() {
        return "insert into MESSAGE(receiver_id,content,sender_id,created_at) values(?,?,?,?)";
    }

    @Override
    String getSaveSuccessMessage() {
        return "Message sent successfully!";
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
    public int save(List<Object> attributes) throws SQLException {
        int rowsAffected = super.save(attributes);
        if (rowsAffected > 0) {
            NotificationDao notificationDao = new NotificationDao();
            notificationDao.save(List.of(NotificationType.message, attributes.get(1), CentralContext.getLoggedInUserID(), attributes.get(0)));
        }
        return rowsAffected;
    }


    @Override
    MessageEntity getEntityFromResultSet(ResultSet resultSet) {
        return null;
    }
}
