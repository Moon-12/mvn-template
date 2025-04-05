package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.Message;

import java.sql.ResultSet;

public class MessageDao extends AppDao<Message> {
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
    Message getEntityFromResultSet(ResultSet resultSet) {
        return null;
    }
}
