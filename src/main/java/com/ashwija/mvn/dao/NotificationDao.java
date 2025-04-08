package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.NotificationEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationDao extends AppDao<NotificationEntity> {

    @Override
    String getInsertSql() {
        return "insert into NOTIFICATION(type,content,sender_id,receiver_id) values(?,?,?,?)";
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
        return null;
    }
}
