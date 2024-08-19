package com.inventoryapp.main;

import com.inventoryapp.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class MainApp {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
