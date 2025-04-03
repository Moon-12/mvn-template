package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.SampleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SampleEntityDao extends AppDao<SampleEntity> {

    @Override
    String getInsertSql() {
        return "INSERT INTO scrubbed_template_test (name, age) VALUES (?, ?)";
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
        return "UPDATE scrubbed_template_test SET active=0 where id=? and active=1";
    }

    @Override
    String getFetchSql() {
        return "select id,name,age from scrubbed_template_test where id=? and active=1";
    }

    @Override
    String getFetchAllSql() {
        return "SELECT * FROM scrubbed_template_test where active=1";
    }

    @Override
    SampleEntity getEntityFromResultSet(ResultSet resultSet) {
        try {
            return new SampleEntity(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
