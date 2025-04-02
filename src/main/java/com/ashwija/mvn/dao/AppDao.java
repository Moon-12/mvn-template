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
                System.out.println("Failed to insert.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    abstract String getDeleteSql();

    public Boolean checkEntityActive(int entityId) {
        return this.fetch(entityId) != null ? true : false;
    }

    abstract String getFetchSql();

    public T fetch(int entityId) {
        T entity = null;
        try {
            Connection con = DatabaseConnection.con;
            PreparedStatement pstmt = con.prepareStatement(this.getFetchSql());
            pstmt.setInt(1, entityId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {  // Use rs.next() to move cursor and check for results
                    entity = getEntityFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching entity with ID " + entityId + ": " + e.getMessage());
            e.printStackTrace();

        }
        return entity;
    }

    public void delete(int entityId) {
        try {
            Connection con = DatabaseConnection.con;
            PreparedStatement pstmt = con.prepareStatement(this.getDeleteSql());
            pstmt.setInt(1, entityId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Deleted successfully !");
            } else {
                System.out.println("Failed to delete");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<T> fetchAll() {
        List<T> list = new ArrayList<>();
        ResultSet resultSet;
        try {
            Connection con = DatabaseConnection.con;
            PreparedStatement pstmt = con.prepareStatement(this.getFetchAllSql());

            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(this.getEntityFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching from database: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    abstract String getFetchAllSql();

    abstract T getEntityFromResultSet(ResultSet resultSet);
}
