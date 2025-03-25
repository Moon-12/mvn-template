package com.ashwija.mvn.model;

public class StudentCourse {
    private StudentEntity student;
    private float marks;

    public float getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }
}
