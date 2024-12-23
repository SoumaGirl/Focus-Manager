package com.focus.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OverviewController {

    @FXML
    private Button homebutton;

    @FXML
    private Button rapportButton;

    @FXML
    private Button sesionButton;

    @FXML
    private Button settingsButton;

    @FXML
    public Button dayButton;

    @FXML
    public Button weekButton;

    @FXML
    public Button monthButton;

    @FXML
    public Button yearButton;

    @FXML
    public BarChart<String, Number> timeDistributionChart;

    @FXML
    private CategoryAxis daysAxis;

    @FXML
    private NumberAxis timeAxis;

    @FXML
    public void initialize() {
        // Link chart buttons to data-loading methods
        dayButton.setOnAction(event -> loadDayData());
        weekButton.setOnAction(event -> loadWeeklyData());
        monthButton.setOnAction(event -> loadMonthlyData());
        yearButton.setOnAction(event -> loadYearlyData());

        // Default chart view: Week
        loadWeeklyData();
    }

    // Handles Home Button Click
    @FXML
    private void handleHomeButton() {
        loadView("AppUsage.fxml");
    }

    // Handles Rapport Button Click
    @FXML
    private void handleRapportButton() {
        loadView("Overview.fxml");
    }

    // Handles Session Button Click
    @FXML
    private void handleSessionButton() {
        loadView("FocusApp.fxml");
    }

    // Handles Settings Button Click
    @FXML
    private void handleSettingsButton() {
        loadView("Setting.fxml");
    }

    // Generic method to load an FXML file
    private void loadView(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/focus/view/" + fxmlFile));
            ((Node) homebutton).getScene().setRoot(root); // Replace the current scene root
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load Day Data into BarChart
    private void loadDayData() {
        resetChart("Hour", "Time (minutes)", 0, 60); // 0-60 minutes range

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Daily Focused Time");

        for (int i = 1; i <= 24; i++) { // 24 hours
            series.getData().add(new XYChart.Data<>(i + "H", Math.random() * 60));
        }

        timeDistributionChart.getData().add(series);
    }

    // Load Weekly Data into BarChart
    private void loadWeeklyData() {
        resetChart("Days", "Time (hours)", 0, 24); // 0-24 hours range

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Weekly Focused Time");

        series.getData().add(new XYChart.Data<>("Sunday", Math.random() * 24));
        series.getData().add(new XYChart.Data<>("Monday", Math.random() * 24));
        series.getData().add(new XYChart.Data<>("Tuesday", Math.random() * 24));
        series.getData().add(new XYChart.Data<>("Wednesday", Math.random() * 24));
        series.getData().add(new XYChart.Data<>("Thursday", Math.random() * 24));
        series.getData().add(new XYChart.Data<>("Friday", Math.random() * 24));
        series.getData().add(new XYChart.Data<>("Saturday", Math.random() * 24));

        timeDistributionChart.getData().add(series);
    }

    // Load Monthly Data into BarChart
    private void loadMonthlyData() {
        resetChart("Days", "Time (hours)", 0, 24); // 0-24 hours range

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Focused Time");

        for (int i = 1; i <= 30; i++) {
            series.getData().add(new XYChart.Data<>("Day " + i, Math.random() * 24));
        }

        timeDistributionChart.getData().add(series);
    }

    // Load Yearly Data into BarChart
    private void loadYearlyData() {
        resetChart("Months", "Time (hours)", 0, 744); // 0-744 hours range (31 days * 24 hours)

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Yearly Focused Time");

        series.getData().add(new XYChart.Data<>("Jan", Math.random() * 744));
        series.getData().add(new XYChart.Data<>("Feb", Math.random() * 672)); // 28 days
        series.getData().add(new XYChart.Data<>("Mar", Math.random() * 744));
        series.getData().add(new XYChart.Data<>("Apr", Math.random() * 720)); // 30 days
        series.getData().add(new XYChart.Data<>("May", Math.random() * 744));
        series.getData().add(new XYChart.Data<>("Jun", Math.random() * 720));
        series.getData().add(new XYChart.Data<>("Jul", Math.random() * 744));
        series.getData().add(new XYChart.Data<>("Aug", Math.random() * 744));
        series.getData().add(new XYChart.Data<>("Sep", Math.random() * 720));
        series.getData().add(new XYChart.Data<>("Oct", Math.random() * 744));
        series.getData().add(new XYChart.Data<>("Nov", Math.random() * 720));
        series.getData().add(new XYChart.Data<>("Dec", Math.random() * 744));

        timeDistributionChart.getData().add(series);
    }

    /**
     * Resets the chart and updates axis labels and bounds.
     *
     * @param xAxisLabel Label for the X-axis
     * @param yAxisLabel Label for the Y-axis
     * @param lowerBound Lower bound of the Y-axis
     * @param upperBound Upper bound of the Y-axis
     */
    private void resetChart(String xAxisLabel, String yAxisLabel, int lowerBound, int upperBound) {
        timeDistributionChart.getData().clear();
        daysAxis.setLabel(xAxisLabel);
        timeAxis.setLabel(yAxisLabel);
        timeAxis.setAutoRanging(false);
        timeAxis.setLowerBound(lowerBound);
        timeAxis.setUpperBound(upperBound);
    }
}
