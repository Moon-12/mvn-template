package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AppDao<T> {

    public String getInsertSql() throws SQLException {
        throw new SQLException("undefined query");
    }

    public String getSaveSuccessMessage() {
        return "Saved Successfully";
    }

    public String getSaveFailureMessage() {
        return "Failed to insert";
    }

    public String getFetchFailureMessage() {
        return "Failed to fetch";
    }

    public boolean validateInput(List<Object> inputList) {
        return true;
    }

    public String getValidationFailureMessage() {
        return "Input validation failed";
    }


    //return no. of affected rows post insert
    public int save(List<Object> attributes) throws SQLException {
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getInsertSql());
        for (int i = 0; i < attributes.size(); i++) {
            Object attr = attributes.get(i);
            if (attr instanceof Timestamp timestamp) {
                pstmt.setTimestamp(i + 1, timestamp);
            } else if (attr instanceof Integer integer) {
                pstmt.setInt(i + 1, integer);
            } else {
                pstmt.setString(i + 1, attr.toString());
            }
        }
        return pstmt.executeUpdate(); // return rowsAffected
    }

    public String getDeleteSql() throws SQLException {
        throw new SQLException("undefined query");
    }

    public String getFetchSql() throws SQLException {
        throw new SQLException("undefined query");
    }

    public Optional<T> fetch(Object entityId) throws SQLException {
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getFetchSql());

        if (entityId instanceof String) {
            pstmt.setString(1, (String) entityId);
        } else {
            pstmt.setInt(1, (Integer) entityId);
        }

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {  // Use rs.next() to move cursor and check for results
            T entity = getEntityFromResultSet(rs);  // Assuming this returns T
            return Optional.ofNullable(entity);     // Wrap the result in Optional
        }
        return Optional.empty();  // Return empty Optional if no result
    }

    //return no. of affected rows post delete
    public int delete(int entityId) throws SQLException {
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getDeleteSql());
        pstmt.setInt(1, entityId);
        return pstmt.executeUpdate(); //return rowsAffected

    }

    public String getDeleteFailureMessage() {
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

        }
        return list;
    }

    public String getFetchAllSql() throws SQLException {
        throw new SQLException("undefined query");
    }

    abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;

    public String getDeleteSuccessMessage() {
        return "Delete successfully!";
    }
}
