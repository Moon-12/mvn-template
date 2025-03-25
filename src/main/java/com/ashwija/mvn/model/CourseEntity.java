package com.ashwija.mvn.model;

import java.util.ArrayList;
import java.util.List;

public class CourseEntity extends AppEntity {
    private int id;
    private String name;
    private int teach_id;
    private List<StudentCourse> studentCourse;
    private static List<String> headers = new ArrayList<>();

    static {
        List.of("ID", "Name").forEach(headers::add);
    }

    public CourseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void setHeaders(List<String> headers) {
        CourseEntity.headers = headers;
    }

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
    public String toString() {
        return String.format("%-12d| %-12s", this.id, this.name);
    }

    @Override
    public String getHeader() {
        return formatHeader(headers);
    }
}
