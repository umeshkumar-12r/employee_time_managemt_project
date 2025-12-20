package com.yourname.timemgmt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Define the database URL, which includes the database name, timezone, and SSL setting.
    private static final String URL =
            "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";

// Define the database username (default root) - change if needed.
    private static final String USER = "root";        // change if needed

    // Define the database password (default root) - change if needed.
    private static final String PASSWORD = "root";    // change if needed

    // Static block to load the MySQL JDBC driver when the class is first loaded
    static {
        try {
            // Explicitly load MySQL JDBC Driver to establish a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
System.out.println("✅ MySQL JDBC Driver loaded");
} catch (ClassNotFoundException e) {
            // Handle case if the JDBC Driver is not found in classpath
            System.out.println("❌ MySQL JDBC Driver NOT found");
e.printStackTrace();
}
    }

    // Static method to return a Connection object to the database
    public static Connection getConnection() throws SQLException {
        // Returns a connection to the MySQL database using the provided URL, user, and password
        return DriverManager.getConnection(URL, USER, PASSWORD);
}
}
