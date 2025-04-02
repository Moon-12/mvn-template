package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.model.StudentCourse;
import com.ashwija.mvn.model.StudentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao extends AppDao {
    @Override
    String getInsertSql() {
        return "INSERT INTO std_course(std_id,course_id,marks) VALUES(?,?,?)";
    }

    @Override
    String getDeleteSql() {
        return "";
    }

    @Override
    String getFetchSql() {
        return "";
    }

    @Override
    String getFetchAllSql() {
        return "";
    }

    @Override
    public void save(List attributes) {
        int student_id = (int) attributes.get(0);
        int course_id = (int) attributes.get(1);
        StudentDao studentDao = new StudentDao();
        CourseDao courseDao = new CourseDao();
        if (studentDao.checkEntityActive(student_id) && courseDao.checkEntityActive(course_id)) {
            super.save(attributes);
        } else {
            System.out.println("Either course or student does not exist. Please cross check the IDs");
        }
    }

    @Override
    Object getEntityFromResultSet(ResultSet resultSet) {
        try {
            return (new StudentCourse(resultSet.getInt("std_id"), resultSet.getInt("course_id"), resultSet.getFloat("marks")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StudentCourse> getStudentCourse(int course_id) {
        List<StudentCourse> studentCourse = new ArrayList<>();
        String url = "Select * from std_course where course_id=? and active=1";
        try {
            Connection con = DatabaseConnection.con;
            PreparedStatement pstmt = con.prepareStatement(url);
            pstmt.setInt(1, course_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {  // Use rs.next() to move cursor and check for results
                    studentCourse.add((StudentCourse) this.getEntityFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching enrollment record " + e.getMessage());
            e.printStackTrace();

        }

        return studentCourse;
    }

}
