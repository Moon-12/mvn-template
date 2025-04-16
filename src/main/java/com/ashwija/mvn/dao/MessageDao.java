package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.MessageEntity;

import java.sql.ResultSet;
import java.util.List;

public class MessageDao extends AppDao<MessageEntity> implements WhoTransformer {
    @Override
    public String getInsertSql() {
        return "insert into MESSAGE(receiver_id,content,sender_id,created_at) values(?,?,?,?)";
    }

    @Override
    public String getSaveSuccessMessage() {
        return "Message sent successfully!";
    }

    @Override
    public String getSaveFailureMessage() {
        return "Message could not be sent!";
    }


    @Override
    MessageEntity getEntityFromResultSet(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<Object> transform(List<Object> inputList) {
        inputList = this.whoTransform(inputList);
        return super.transform(inputList);
    }
}
