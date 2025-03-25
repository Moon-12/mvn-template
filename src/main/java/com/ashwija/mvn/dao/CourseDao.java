package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.CourseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDao extends AppDao<CourseEntity> {
    @Override
    String getInsertSql() {
        return "INSERT INTO course (name) VALUES (?)";
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
        return "select * from course where active=1";
    }

    @Override
    CourseEntity getEntityFromResultSet(ResultSet resultSet) {
        try {
            return new CourseEntity(resultSet.getInt("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void assignTeacher() {

    }
}
