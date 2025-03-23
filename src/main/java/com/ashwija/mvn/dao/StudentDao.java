package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends AppDao<Student> {

    @Override
    String getInsertSql() {
        return "INSERT INTO student (roll_no, name) VALUES (?, ?)";
    }

    @Override
    String getFetchAllSql() {
        return "SELECT * FROM student where active=1";
    }

    @Override
    Student getEntityFromResultSet(ResultSet resultSet) {
        return new Student(resultSet.getString("roll_no"), resultSet.getString("name"));
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

    public List<Student> fetchAll() {
        List<Student> studentEntities = new ArrayList<>();
        studentEntities = super.fetchAll();
        return studentEntities;
    }

}
