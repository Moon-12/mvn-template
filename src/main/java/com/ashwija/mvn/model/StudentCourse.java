package com.ashwija.mvn.model;

public class StudentCourse {
    private int id;
    private int std_id;
    private int course_id;
    private float marks;

    public StudentCourse(int stdId, int courseId, float marks) {
        this.std_id = stdId;
        this.course_id = courseId;
        this.marks = marks;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public int getStd_id() {
        return std_id;
    }

    public void setStd_id(int std_id) {
        this.std_id = std_id;
    }
}
