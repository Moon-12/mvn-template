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
        Map<String, Object> config = Utility.yamlToMap("secrets.yaml");
        Map<String, String> dbConfig = (Map<String, String>) config.get("db");
        String url = dbConfig.get("url");
        String username = dbConfig.get("username");
        String password = dbConfig.get("password");
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("connection success");
        } catch (SQLException e) {
            System.out.println("could not connect");
            e.printStackTrace();
        }
    }


}
