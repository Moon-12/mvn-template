package com.ashwija.mvn.entity;

import java.util.List;

public class StudentEntity implements AppEntity {
    private Integer rollNo;
    private String name;

    public StudentEntity() {

    }

    public StudentEntity(Integer rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public void save(AppEntity myEntity) {
        System.out.println(myEntity);
    }

    @Override
    public void delete(AppEntity myEntity) {

    }

    @Override
    public AppEntity view(AppEntity myEntity) {
        return null;
    }

    @Override
    public AppEntity performObjectFactoryFromList(List<Object> inputs) {
        rollNo = Integer.parseInt((String) inputs.get(0));
        name = (String) inputs.get(1);
        return new StudentEntity(rollNo, name);
    }
}
