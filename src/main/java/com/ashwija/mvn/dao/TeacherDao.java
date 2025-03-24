package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.TeacherEntity;

import java.sql.ResultSet;

public class TeacherDao extends AppDao<TeacherEntity> {
    @Override
    String getInsertSql() {
        return "";
    }

    @Override
    String getFetchAllSql() {
        return "";
    }

    @Override
    TeacherEntity getEntityFromResultSet(ResultSet resultSet) {
        return null;
    }
}
