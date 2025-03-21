package com.ashwija.mvn.entity;

import com.ashwija.mvn.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AppEntity {
    public void save(AppEntity myEntity) {

    }

    public void save(String sql, List<Object> attributes, String entityName) {
        try {
            Connection con = DatabaseConnection.con;
            PreparedStatement pstmt = con.prepareStatement(sql);
            for (int i = 0; i < attributes.size(); i++) {
                pstmt.setString(i + 1, attributes.get(i).toString());
            }
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(entityName + " inserted successfully !");
            } else {
                System.out.println("Failed to insert student.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(AppEntity myEntity) {

    }


    public AppEntity fetch(AppEntity myEntity) {
        return null;
    }

    abstract public AppEntity performObjectFactoryFromList(List<Object> inputs);
}
