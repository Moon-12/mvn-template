package com.ashwija.mvn;

import com.ashwija.mvn.common.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DatabaseConnection {
    public static Connection con;

    static {
        connectDb();
    }

    private static void connectDb() {
        String envUrl = System.getenv("MYSQL_URL");
        String envUsername = System.getenv("MYSQL_USERNAME");
        String envPassword = System.getenv("MYSQL_PASSWORD");
        try {
            con = DriverManager.getConnection(envUrl, envUsername, envPassword);
//            System.out.println("connection success");
        } catch (SQLException e) {
            System.out.println("could not connect");
            e.printStackTrace();
        }
    }


}
