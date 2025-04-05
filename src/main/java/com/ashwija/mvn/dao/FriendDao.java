package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.FriendEntity;

import java.sql.ResultSet;

public class FriendDao extends AppDao<FriendEntity> {
    @Override
    String getInsertSql() {
        return "insert into FRIEND(receiver_id,sender_id,created_at) values(?,?,?)";
    }

    @Override
    String getSaveSuccessMessage() {
        return "Friend Request Sent Successfully!";
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
    FriendEntity getEntityFromResultSet(ResultSet resultSet) {
        return null;
    }
}
