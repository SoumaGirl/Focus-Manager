package com.focus.DAO;

import com.focus.Model.User;
import com.focus.Model.Database;
import com.focus.Model.SessionManager;

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
        // Hash the password if necessary
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);  // Remember to hash this before comparison
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
                );
    
                // Set the user ID in session after successful login
                SessionManager.setCurrentUserId(user.getId());
    
                // Create session in the database as well
                createSession(user.getId());
    
                return user;
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

    // Create a session for the user
    public boolean createSession(int userId) throws SQLException {
        // First, check if there's already an active session for the user
        String checkQuery = "SELECT * FROM sessions WHERE user_id = ? AND is_active = true";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, userId);
            ResultSet checkResult = checkStmt.executeQuery();
            if (checkResult.next()) {
                // If there is already an active session, do not create a new one
                System.out.println("Active session already exists for user ID: " + userId);
                return true;
            }
        }
    
        // No active session found, create a new one
        String query = "INSERT INTO sessions (user_id, is_active) VALUES (?, true)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
    

    // Check if a user is signed in (session-based or token-based)
    public boolean isUserSignedIn() throws SQLException {
        // First, check the SessionManager to see if the user is set
        if (!SessionManager.isUserSignedIn()) {
            System.out.println("No user in SessionManager");
            return false;
        }
    
        // If SessionManager indicates a user is signed in, check the database for session status
        int userId = SessionManager.getCurrentUserId();
        String query = "SELECT * FROM sessions WHERE user_id = ? AND is_active = true";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Session found in database for user ID: " + userId);
                return true; // Session is active in the database
            } else {
                System.out.println("No active session in the database for user ID: " + userId);
                return false; // No active session found, user needs to log in
            }
        }
    }
    
    
    

    // Get the current user ID from session management logic
    private int getCurrentUserId() {
        // Check if the user is signed in before retrieving the user ID
        if (!SessionManager.isUserSignedIn()) {
            throw new IllegalStateException("User is not signed in, no user ID available.");
        }
        return SessionManager.getCurrentUserId(); // Return the current user ID
    }
}
