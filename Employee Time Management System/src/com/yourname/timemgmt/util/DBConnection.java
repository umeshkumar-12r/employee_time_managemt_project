package com.yourname.timemgmt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for creating JDBC connections to the application database.
 */
public class DBConnection {

    // JDBC URL pointing to the employee_time_management database on the local MySQL server.
    private static final String URL = "jdbc:mysql://localhost:3306/employee_time_management";

    // Database username (change to match your local DB configuration).
    private static final String USER = "root";

    // Database password (change to match your local DB configuration).
    private static final String PASSWORD = "password";

    // Static initialization block to load the MySQL JDBC driver once when the class is first used.
    static {
        try {
            // Load MySQL 8+ JDBC driver so DriverManager knows how to create MySQL connections.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    /**
     * Creates and returns a new database connection using the configured URL, user, and password.
     *
     * @return a new Connection to the employee_time_management database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        // Delegate to DriverManager to open a new JDBC connection.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
