package com.focus.controllers;


import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AppUsageController {

    @FXML
    private PieChart pieChart;

    @FXML
    private Label totalTime;

    @FXML
    private Label productiveTime;

    @FXML
    private Label productivePercent;

    @FXML
    private Label unproductiveTime;

    @FXML
    private Label unproductivePercent;

    @FXML
    private ListView<String> productiveApps;

    @FXML
    private ListView<String> unproductiveApps;

    @FXML
    public void initialize() {
        // Configure the Pie Chart
        pieChart.getData().add(new PieChart.Data("PowerPoint", 25));
        pieChart.getData().add(new PieChart.Data("Excel", 20));
        pieChart.getData().add(new PieChart.Data("Photoshop", 15));
        pieChart.getData().add(new PieChart.Data("Canva", 15));
        pieChart.getData().add(new PieChart.Data("Messenger", 10));
        pieChart.getData().add(new PieChart.Data("Instagram", 10));
        pieChart.getData().add(new PieChart.Data("Twitter", 5));

        // Set Labels
        totalTime.setText("32h 59m");
        productiveTime.setText("32h 47m");
        productivePercent.setText("80.3% of total");
        unproductiveTime.setText("3h 12m");
        unproductivePercent.setText("19.7% of total");

        // Populate App Usage Lists
        productiveApps.getItems().addAll("Canva - 4h 50m", "Excel - 14h 12m", "PowerPoint - 7h 55m", "Photoshop - 5h 41m");
        unproductiveApps.getItems().addAll("Instagram - 1h 30m", "Messenger - 48m", "Twitter - 1h");
    }
}
