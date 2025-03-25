package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.model.StudentCourse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    Object getEntityFromResultSet(ResultSet resultSet) {
        try {
            return (new StudentCourse(resultSet.getInt("std_id"), resultSet.getInt("course_id"), resultSet.getFloat("marks")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
