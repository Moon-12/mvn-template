package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.StudentEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends AppDao<StudentEntity> {

    @Override
    String getInsertSql() {
        return "INSERT INTO student (roll_no, name) VALUES (?, ?)";
    }

    @Override
    String getDeleteSql() {
        return "UPDATE student SET active=0 where id=? and active=1";
    }

    @Override
    String getFetchAllSql() {
        return "SELECT * FROM student where active=1";
    }

    @Override
    StudentEntity getEntityFromResultSet(ResultSet resultSet) {
        try {
            return new StudentEntity(resultSet.getInt("id"), resultSet.getString("roll_no"), resultSet.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void delete(AppDao myEntity) {
//
//    }
//
//    @Override
//    public AppDao fetch(String id) {
//
//        fetchAll();
//        return null;
//    }

    public List<StudentEntity> fetchAll() {
        List<StudentEntity> studentEntities = new ArrayList<>();
        studentEntities = super.fetchAll();
        return studentEntities;
    }

}
