package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.model.StudentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends AppDao<StudentEntity> {

    @Override
    String getInsertSql() {
        return "INSERT INTO student (roll_no, name) VALUES (?, ?)";
    }

    @Override
    String getDeleteSql() {
        return "UPDATE student SET active=0 where id=? and active=1";
    }

    @Override
    String getFetchSql() {
        return "select id,roll_no, name from student where id=? and active=1";
    }

    @Override
    String getFetchAllSql() {
        return "SELECT * FROM student where active=1";
    }

    @Override
    StudentEntity getEntityFromResultSet(ResultSet resultSet) {
        try {
            return new StudentEntity(resultSet.getInt("id"), resultSet.getString("roll_no"), resultSet.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    List<StudentEntity> getStudentListByIds(List<Integer> id) {
        List<StudentEntity> studentEntitiesList = new ArrayList<>();
        String sql = "SELECT id,roll_no,name FROM student WHERE id IN (";
        for (int i = 0; i < id.size(); i++) {
            sql += "?";
            if (i < id.size() - 1) {
                sql += ",";
            }
        }
        sql += ")";
        ResultSet resultSet;
        try {
            Connection con = DatabaseConnection.con;
            PreparedStatement pstmt = con.prepareStatement(sql);
            for (int i = 0; i < id.size(); i++) {
                pstmt.setInt(i + 1, id.get(i));
            }

            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                studentEntitiesList.add(this.getEntityFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching from database: " + e.getMessage());
            e.printStackTrace();
        }
        return studentEntitiesList;
    }

}
