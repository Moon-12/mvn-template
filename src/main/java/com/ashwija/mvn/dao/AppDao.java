package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AppDao<T> {
    abstract String getInsertSql();

    abstract String getSaveSuccessMessage();

    String getSaveFailureMessage() {
        return "Failed to insert";
    }

    public boolean validateInput(List<Object> inputList) {
        return true;
    }

    abstract String getValidationFailureMessage();

    //return no. of affected rows post insert
    public int save(List<Object> attributes) throws SQLException {
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getInsertSql());
        for (int i = 0; i < attributes.size(); i++) {
            pstmt.setString(i + 1, attributes.get(i).toString());
        }
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected;
    }

    abstract String getDeleteSql();

    public Boolean checkEntityActive(int entityId) {
        return this.fetch(entityId) != null ? true : false;
    }

    abstract String getFetchSql();

    public Optional<T> fetch(Object entityId) {
        Optional<T> entity = null;
        try {
            PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getFetchSql());

            if (entityId instanceof String) {
                pstmt.setString(1, (String) entityId);
            } else {
                pstmt.setInt(1, (Integer) entityId);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {  // Use rs.next() to move cursor and check for results
                    entity = (Optional<T>) getEntityFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching entity with ID " + entityId + ": " + e.getMessage());
            e.printStackTrace();

        }
        return entity;
    }

    //return no. of affected rows post delete
    public int delete(int entityId) throws SQLException {
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getDeleteSql());
        pstmt.setInt(1, entityId);
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected;
    }

    String getDeleteFailureMessage() {
        return "Failed to delete";
    }

    public List<T> fetchAll() {
        List<T> list = new ArrayList<>();
        ResultSet resultSet;
        try {
            PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getFetchAllSql());

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

    public String getDeleteSuccessMessage() {
        return "Delete successfully!";
    }
}
