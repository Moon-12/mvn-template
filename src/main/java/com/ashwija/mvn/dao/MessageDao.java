package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.MessageEntity;

import java.sql.ResultSet;

public class MessageDao extends AppDao<MessageEntity> {
    @Override
    String getInsertSql() {
        return "insert into MESSAGE(receiver_id,content,sender_id,created_at) values(?,?,?,?)";
    }

    @Override
    public String getSaveSuccessMessage() {
        return "Message sent successfully!";
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


    @Override
    MessageEntity getEntityFromResultSet(ResultSet resultSet) {
        return null;
    }
}
