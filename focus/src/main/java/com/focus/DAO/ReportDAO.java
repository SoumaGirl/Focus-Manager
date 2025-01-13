package com.focus.DAO;


import com.focus.Model.Report;
import com.focus.Model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public List<Report> getReports(String period) {
        List<Report> reports = new ArrayList<>();
        String query = "";

        // Construire la requête SQL selon la période
        switch (period) {
            case "Day":
                query = "SELECT day_name AS period, SUM(focused_time) AS focused_time FROM daily_reports GROUP BY day_name";
                break;
            case "Week":
                query = "SELECT week_number AS period, SUM(focused_time) AS focused_time FROM weekly_reports GROUP BY week_number";
                break;
            case "Month":
                query = "SELECT month_name AS period, SUM(focused_time) AS focused_time FROM monthly_reports GROUP BY month_name";
                break;
            case "Year":
                query = "SELECT year AS period, SUM(focused_time) AS focused_time FROM yearly_reports GROUP BY year";
                break;
            default:
                throw new IllegalArgumentException("Période invalide : " + period);
        }

        // Utiliser la classe Database pour se connecter
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Lire les données de la base et les ajouter à la liste
            while (resultSet.next()) {
                String periodName = resultSet.getString("period");
                int focusedTime = resultSet.getInt("focused_time");
                reports.add(new Report(periodName, focusedTime));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'accès à la base de données !");
        }

        return reports;
    }
}