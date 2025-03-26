package com.ashwija.mvn.model;

import java.util.ArrayList;
import java.util.List;

public class CourseEntity extends AppEntity {
    private int id;
    private String name;
    private int teach_id;
    private TeacherEntity teacher;
    private List<StudentCourse> studentCourse;
    private static List<String> headers = new ArrayList<>();


    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    static {
        List.of("ID", "Name").forEach(headers::add);
    }

    public int getTeach_id() {
        return teach_id;
    }

    public void setTeach_id(int teach_id) {
        this.teach_id = teach_id;
    }

    public CourseEntity(int id, String name, int teachId) {
        this.id = id;
        this.name = name;
        teach_id = teachId;
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

    public List<StudentCourse> getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(List<StudentCourse> studentCourse) {
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

    public String getDetailedHeader() {
        headers = new ArrayList<>();
        List.of("Roll No", "Name", "Marks").forEach(headers::add);
        return formatHeader(headers);
    }

    public String detailedToString() {
        StringBuilder courseDetail = new StringBuilder();
        courseDetail.append("Course: " + name + "\n");

        String teacherName = teacher != null ? teacher.getName() : "NA";
        courseDetail.append("Designated Teacher: " + teacherName + "\n");
        courseDetail.append(getDetailedHeader() + "\n");
        if (studentCourse != null) {
            studentCourse.forEach(stdCourse -> {

                String formattedLine = String.format("%-12s| %-12s| %-5f%n",
                        stdCourse.getStudent().getRollNo(),    // %-12d : left-aligned integer, 12 characters wide
                        stdCourse.getStudent().getName(),      // %-20s : left-aligned string, 20 characters wide
                        stdCourse.getMarks());    // %-5d  : left-aligned integer, 5 characters wide
                courseDetail.append(formattedLine);
            });
        }
        return courseDetail.toString();
    }
}
