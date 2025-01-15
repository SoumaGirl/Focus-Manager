package com.focus.DAO;

import com.focus.Model.Usage;
import com.focus.Model.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsageDAO {

    public void saveUsage(Usage usage) throws SQLException {
        String query = "INSERT INTO usages (app_name, usage_time) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usage.getAppName());
            statement.setInt(2, usage.getUsageTime());
            statement.executeUpdate();
        }
    }

    public List<Usage> getAllUsage() throws SQLException {
        String query = "SELECT * FROM usages";
        List<Usage> usageList = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String appName = resultSet.getString("app_name");
                int usageTime = resultSet.getInt("usage_time");
                usageList.add(new Usage(id, appName, usageTime));
            }
        }
        return usageList;
    }

    // Method to get a usage entry by app name
    public Usage getUsageByAppName(String appName) throws SQLException {
        String query = "SELECT * FROM usages WHERE app_name = ?"; // Fixed table and column names
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, appName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usage(rs.getInt("id"), rs.getString("app_name"), rs.getInt("usage_time"));
            }
        }
        return null;
    }

    // Method to update an existing usage entry
    public void updateUsage(Usage usage) throws SQLException {
        String query = "UPDATE usages SET usage_time = ? WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usage.getUsageTime());
            stmt.setInt(2, usage.getId());
            stmt.executeUpdate();
        }
    }
}
