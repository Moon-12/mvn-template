package com.ashwija.mvn.dao;

import com.ashwija.mvn.model.Student;

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

    public List<StudentDao> fetchAll() {
        List<StudentDao> studentEntities = new ArrayList<>();
        String sql = "select * from student";
        super.fetchAll(sql);
        return studentEntities;
    }

}
