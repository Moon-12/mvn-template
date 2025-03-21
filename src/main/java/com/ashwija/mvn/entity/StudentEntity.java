package com.ashwija.mvn.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentEntity extends AppEntity {
    private String rollNo;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public StudentEntity() {

    }

    public StudentEntity(String rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public void save(AppEntity myEntity) {
        StudentEntity stdObj = (StudentEntity) myEntity;
        String sql = "INSERT INTO student (roll_no, name) VALUES (?, ?)";
        super.save(sql, List.of(stdObj.getRollNo(), stdObj.getName()), "student");
    }

    @Override
    public void delete(AppEntity myEntity) {

    }

    @Override
    public AppEntity fetch(AppEntity myEntity) {

        fetchAll();
        return null;
    }

    public List<StudentEntity> fetchAll() {
        List<StudentEntity> studentEntities = new ArrayList<>();
        String sql = "select * from student";
        super.fetchAll(sql);
        return studentEntities;
    }

    @Override
    public AppEntity performObjectFactoryFromList(List<Object> inputs) {
        if (!inputs.isEmpty()) {
            rollNo = (String) inputs.get(0);
            name = (String) inputs.get(1);
        }
        return new StudentEntity(rollNo, name);
    }
}
