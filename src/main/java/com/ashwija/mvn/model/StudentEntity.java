package com.ashwija.mvn.model;

import java.util.ArrayList;
import java.util.List;

public class StudentEntity extends AppEntity {
    private String rollNo;
    private String name;
    private int id;
    final static List<String> headers = new ArrayList<>();

    static {
        List.of("ID", "Roll No", "Name").forEach(headers::add);
    }

    public StudentEntity(int id, String rollNo, String name) {
        this.id = id;
        this.rollNo = rollNo;
        this.name = name;

    }

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

    public String getHeader() {
        return formatHeader(headers);
    }

    public String toString() {
        String stringFrmt = String.format("%-12d| %-12s| %-12s", this.id, this.rollNo, this.name);
        return stringFrmt;
    }

}
