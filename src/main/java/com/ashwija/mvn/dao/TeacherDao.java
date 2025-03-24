package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.TeacherEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao extends AppDao<TeacherEntity> {
    @Override
    String getInsertSql() {

        return "Insert into teacher (name) VALUES(?)";
    }

    @Override
    String getDeleteSql() {
        return "UPDATE teacher SET active=0 where id=? and active=1";
    }

    @Override
    String getFetchAllSql() {
        return "select * from teacher where active=1";
    }

    @Override
    TeacherEntity getEntityFromResultSet(ResultSet resultSet) {
        try {
            return new TeacherEntity(resultSet.getInt("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TeacherEntity> fetchAll() {
        List<TeacherEntity> teacherEntities = new ArrayList<>();
        teacherEntities = super.fetchAll();
        return teacherEntities;
    }
}
