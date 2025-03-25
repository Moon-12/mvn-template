package com.ashwija.mvn.model;

import java.util.List;

public class CourseEntity extends AppEntity {
    private int id;
    private String name;
    private int teach_id;
    private List<StudentCourse> studentCourse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentCourse> getStudentMarks() {
        return studentCourse;
    }

    public void setStudentMarks(List<StudentCourse> studentCourse) {
        this.studentCourse = studentCourse;
    }

    @Override
    public String getHeader() {
        return "";
    }
}
