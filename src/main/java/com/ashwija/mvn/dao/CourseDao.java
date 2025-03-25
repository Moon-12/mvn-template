package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.CourseEntity;

import java.sql.ResultSet;

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
        return null;
    }

    public void assignTeacher() {

    }
}
