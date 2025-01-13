package com.focus.DAO;

import com.focus.Model.Database;
import com.focus.Model.UserSettings;

import java.sql.*;

public class UserSettingsDAO {
    private Connection connection;

    // Constructor that uses the shared connection from the Database class
    public UserSettingsDAO() throws SQLException {
        this.connection = Database.getConnection();
        if (this.connection != null) {
            System.out.println("Database connection established.");
        } else {
            System.out.println("Database connection failed.");
        }
    }

    // Retrieve user settings by ID
    public UserSettings getUserSettings(int id) {
        String query = "SELECT * FROM user_settings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new UserSettings(
                        resultSet.getInt("id"),
                        resultSet.getString("alarm_sound"),
                        resultSet.getInt("intervals"),
                        resultSet.getString("focus_time"),
                        resultSet.getString("short_break"),
                        resultSet.getString("long_break")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user settings for ID: " + id);
            e.printStackTrace();
        }
        return null;  // Return null if no settings found
    }

    // Save user settings to the database (INSERT)
    public void saveUserSettings(UserSettings settings) {
        String query = "INSERT INTO user_settings (id, alarm_sound, intervals, focus_time, short_break, long_break) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, settings.getId());
            statement.setString(2, settings.getAlarmSound());
            statement.setInt(3, settings.getIntervals());
            statement.setString(4, settings.getFocusTime());
            statement.setString(5, settings.getShortBreak());
            statement.setString(6, settings.getLongBreak());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Settings saved successfully.");
            } else {
                System.out.println("Failed to save settings.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting user settings.");
            e.printStackTrace();
        }
    }

    // Update user settings in the database (UPDATE)
    public void updateUserSettings(UserSettings settings) throws SQLException {
        String query = "UPDATE user_settings SET alarm_sound = ?, intervals = ?, focus_time = ?, short_break = ?, long_break = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, settings.getAlarmSound());
            statement.setInt(2, settings.getIntervals());
            statement.setString(3, settings.getFocusTime());
            statement.setString(4, settings.getShortBreak());
            statement.setString(5, settings.getLongBreak());
            statement.setInt(6, settings.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Settings updated successfully.");
            } else {
                System.out.println("No rows updated, likely because no matching ID was found.");
                throw new SQLException("No rows updated.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating user settings.");
            e.printStackTrace();
            throw e;  // Re-throw the exception to propagate the error
        }
    }

    // Check if user settings exist for a specific ID
    public boolean userSettingsExist(int id) {
        String query = "SELECT COUNT(*) AS count FROM user_settings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    System.out.println("User settings count: " + count);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking if user settings exist for ID: " + id);
            e.printStackTrace();
        }
        return false;
    }
}
