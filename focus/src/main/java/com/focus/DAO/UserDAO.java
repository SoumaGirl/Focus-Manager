package com.focus.DAO;

import com.focus.Model.User;
import com.focus.Model.Database;

import java.sql.*;

public class UserDAO {
    private Connection connection;

    // Constructor to initialize the connection
    public UserDAO() throws SQLException {
        this.connection = Database.getConnection();
        if (this.connection != null) {
            System.out.println("Database connection established.");
        } else {
            System.out.println("Database connection failed.");
        }
    }

    // Insert a new user into the database (signup)
    public boolean createUser(User user) throws SQLException {
        String query = "INSERT INTO users (email, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword()); // Ensure password is hashed if necessary
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    

    // Get user by email and password (for login)
    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password); // Hash the password if necessary
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
                );
            }
        }
        return null; // User not found
    }
    
    

    // Check if a user with the given email already exists (for signup)
    public boolean userExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count") > 0;
            }
        }
        return false;
    }
    
    
}
