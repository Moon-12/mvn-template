package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.model.CourseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return "";
    }

    @Override
    String getFetchAllSql() {
        return "select * from course where active=1";
    }

    @Override
    CourseEntity getEntityFromResultSet(ResultSet resultSet) {
        try {
            return new CourseEntity(resultSet.getInt("id"), resultSet.getString("name"));
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
        } catch (SQLException e) {
            System.err.println("Error assigning teacher: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
