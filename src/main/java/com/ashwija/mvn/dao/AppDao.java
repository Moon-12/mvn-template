package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AppDao<T> {

    abstract String getInsertSql();


    public void save(List<Object> attributes) {
        try {
            Connection con = DatabaseConnection.con;
            PreparedStatement pstmt = con.prepareStatement(this.getInsertSql());
            for (int i = 0; i < attributes.size(); i++) {
                pstmt.setString(i + 1, attributes.get(i).toString());
            }
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Inserted successfully !");
            } else {
                System.out.println("Failed to insert student.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public T fetch(String entityId) {
//        return null;
//    }

//    public void delete(String entityId) {
//
//    }

    public List<T> fetchAll(String id) {
        List<T> list = new ArrayList<>();
        ResultSet resultSet;
        try {
            Connection con = DatabaseConnection.con;
            PreparedStatement pstmt = con.prepareStatement(this.getFetchAllSql());

            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
//                list.add();
            }

        } catch (SQLException e) {
            System.err.println("Error fetching from database: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    abstract String getFetchAllSql();

}
