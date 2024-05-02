package com.mysql.cj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoadDriver {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            // Load the JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                                                "user=minty&password=greatsqldb");
            
            // If the connection is successful, you can proceed with database operations

            System.out.println("Connection established successfully!");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException ex) {
            // handle Class.forName() failure
            System.out.println("Failed to load MySQL JDBC driver class.");
            ex.printStackTrace();
        } finally {
            // Close the connection in a finally block to ensure it's always closed
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Error closing connection: " + ex.getMessage());
                }
            }
        }
    }
}
