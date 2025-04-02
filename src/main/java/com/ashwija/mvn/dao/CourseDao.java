package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.model.CourseEntity;
import com.ashwija.mvn.model.StudentCourse;
import com.ashwija.mvn.model.StudentEntity;
import com.ashwija.mvn.model.TeacherEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends AppDao<CourseEntity> {
    @Override
    String getInsertSql() {
        return "INSERT INTO course (name) VALUES (?)";
    }

    @Override
    String getDeleteSql() {
        return "";
    }

    @Override
    String getFetchSql() {
        return "select * from course where id=? and active=1";
    }

    @Override
    String getFetchAllSql() {
        return "select * from course where active=1";
    }

    @Override
    CourseEntity getEntityFromResultSet(ResultSet resultSet) {
        try {
            return new CourseEntity(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("teach_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    String getAssignTeacherSql() {
        return "UPDATE course set teach_id=? where id=? and active=1";
    }

    public void assignTeacher(List<Object> inputList) {

        try {
            Connection con = DatabaseConnection.con;
            //check if teacher is active
            TeacherDao teacherDao = new TeacherDao();
            int teacher_id = (int) inputList.get(0);
            if (teacherDao.checkEntityActive(teacher_id)) {
                PreparedStatement pstmt = con.prepareStatement(this.getAssignTeacherSql());
                for (int i = 0; i < inputList.size(); i++) {
                    pstmt.setString(i + 1, inputList.get(i).toString());
                }

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println(" Assigned teacher successfully !");
                } else {
                    System.out.println("Failed to assign teacher");
                }
            } else {
                System.out.println("Teacher does not exist, double check your teacher ID");
            }
        } catch (SQLException e) {
            System.err.println("Error assigning teacher: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public CourseEntity fetch(int course_id) {
        CourseEntity courseEntity = super.fetch(course_id);
        //get teacher_id for course being fetched
        int teacher_id = courseEntity.getTeach_id();
        // fetch teacher details using teacher_id
        TeacherDao teacherDao = new TeacherDao();
        TeacherEntity teacherEntity = teacherDao.fetch(teacher_id);
        //set teacher details in course

        courseEntity.setTeacher(teacherEntity);

        List<Integer> studentIdsList = new ArrayList<>();
        StudentCourseDao studentCourseDao = new StudentCourseDao();
        List<StudentCourse> studentCourseList = studentCourseDao.getStudentCourse(course_id);
        if (studentCourseList != null) {
            studentCourseList.forEach(stdCourse -> studentIdsList.add(stdCourse.getStd_id()));
        }

        //get student list from student table based on std_id in std_course table

        StudentDao studentDao = new StudentDao();
        List<StudentEntity> studentEntitieslist = studentDao.getStudentListByIds(studentIdsList);
        if (studentEntitieslist != null) {
            for (int i = 0; i < studentCourseList.size() && i < studentEntitieslist.size(); i++) {
                studentCourseList.get(i).setStudent(studentEntitieslist.get(i));
            }
        }

        courseEntity.setStudentCourse(studentCourseList);


        return courseEntity;
    }
}
