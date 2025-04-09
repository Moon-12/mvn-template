package com.ashwija.mvn.dao;

import com.ashwija.mvn.DatabaseConnection;
import com.ashwija.mvn.model.UserProfileEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserProfileDao extends AppDao<UserProfileEntity> {
    private String validationFailed;

    @Override
    String getInsertSql() {
        return "INSERT into USER_PROFILE(id,password,gender,school) values(?,?,?,?)";
    }

    @Override
    String getDeleteSql() {
        return "";
    }

    @Override
    String getFetchSql() {
        return "select id,gender,school from user_profile where id=?";
    }

    String getLoginSql() {
        return "SELECT SUM(checksum) check_sum_total FROM " +
                "(SELECT DISTINCT 1 checksum FROM USER_PROFILE u1 WHERE u1.id = ? " +
                "UNION ALL " +
                "SELECT DISTINCT 2 checksum FROM USER_PROFILE u2 WHERE u2.id = ? AND u2.password = ?) checksum_tbl";

    }

    public int login(List<Object> attributes) throws SQLException {
        PreparedStatement pstmt = DatabaseConnection.con.prepareStatement(this.getLoginSql());
        pstmt.setString(1, attributes.get(0).toString());
        pstmt.setString(2, attributes.get(0).toString());
        pstmt.setString(3, attributes.get(1).toString());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt("check_sum_total");
    }

    @Override
    String getFetchAllSql() {
        return "";
    }

    @Override
    UserProfileEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserProfileEntity(resultSet.getString("id"), resultSet.getString("gender"), resultSet.getString("school"));
    }

    @Override
    public String getSaveSuccessMessage() {
        return "User Registered Successfully";
    }

    @Override
    public String getValidationFailureMessage() {
        return this.validationFailed;
    }


    @Override
    public boolean validateInput(List<Object> attributes) {
        String userID = (String) attributes.get(0);
        String password = (String) attributes.get(1);

        // Check length
        if (userID.length() < 3 || userID.length() > 10) {
            this.validationFailed = "User ID needs to be between 3 and 10 characters";
            return false;
        }

        // Check if userID and password are the same
        if (userID.equals(password)) {
            this.validationFailed = "ID cannot be the same as password";
            return false;
        }

        // Check for at least one letter, one digit, and one special character using regex
        if (!userID.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[#?!*]).*$")) {
            this.validationFailed = "User ID must contain at least one letter, one digit, and one special character from {#, ?, !, *}";
            return false;
        }

        return true;
    }
}
