package com.focus.DAO;

import com.focus.Model.Usage;
import com.focus.Model.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsageDAO {
    private Connection connection;

    public UsageDAO() throws SQLException {
        this.connection = Database.getConnection();
    }

    // Récupérer toutes les applications et leur temps d'utilisation
    public List<Usage> getAllUsages() throws SQLException {
        List<Usage> usages = new ArrayList<>();
        String sql = "SELECT app_name, usage_time, is_productive FROM app_usage";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Usage usage = new Usage(
                rs.getString("app_name"),
                rs.getInt("usage_time"),
                rs.getBoolean("is_productive")
            );
            usages.add(usage);
        }
        return usages;
    }

    // Récupérer le temps total d'utilisation
    public int getTotalTime() throws SQLException {
        String sql = "SELECT SUM(usage_time) AS total_time FROM app_usage";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("total_time");
        }
        return 0;
    }

    // Récupérer le temps productif
    public int getProductiveTime() throws SQLException {
        String sql = "SELECT SUM(usage_time) AS productive_time FROM app_usage WHERE is_productive = TRUE";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("productive_time");
        }
        return 0;
    }

    // Récupérer les applications productives
    public List<String> getProductiveApps() throws SQLException {
        List<String> productiveApps = new ArrayList<>();
        String sql = "SELECT app_name FROM app_usage WHERE is_productive = TRUE";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            productiveApps.add(rs.getString("app_name"));
        }
        return productiveApps;
    }

    // Récupérer les applications non-productives
    public List<String> getUnproductiveApps() throws SQLException {
        List<String> unproductiveApps = new ArrayList<>();
        String sql = "SELECT app_name FROM app_usage WHERE is_productive = FALSE";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            unproductiveApps.add(rs.getString("app_name"));
        }
        return unproductiveApps;
    }
}

