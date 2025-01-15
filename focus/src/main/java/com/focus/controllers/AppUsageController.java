package com.focus.controllers;

import com.focus.Model.Usage;
import com.focus.DAO.UsageDAO;
import com.focus.Service.AppTrackerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.Parent;
import javafx.application.Platform;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppUsageController {

    @FXML
    private PieChart pieChart;

    @FXML
    private Label totalTime;

    @FXML
    private ListView<String> productiveApps;

    @FXML
    private Button homebutton;
    @FXML
    private Button rapportButton;
    @FXML
    private Button sessionButton;
    @FXML
    private Button settingsButton;

    private final AppTrackerService trackerService;
    private final UsageDAO usageDAO;
    private final ScheduledExecutorService scheduler;

    public AppUsageController() {
        this.trackerService = new AppTrackerService();
        this.usageDAO = new UsageDAO();
        this.scheduler = Executors.newScheduledThreadPool(2); // Separate threads for tracking and UI updates
    }

    @FXML
    public void initialize() {
        startTracking();
        // Schedule the pie chart update to run every 5 seconds
        scheduler.scheduleAtFixedRate(this::refreshData, 0, 5, TimeUnit.SECONDS);
    }

    private void refreshData() {
        try {
            List<Usage> usageList = usageDAO.getAllUsage();
            Platform.runLater(() -> updateUI(usageList));
        } catch (SQLException e) {
            showError("Database Error", "Unable to fetch usage data.");
            e.printStackTrace();
        }
    }

    private void updateUI(List<Usage> usageList) {
        pieChart.getData().clear();
        int totalUsageTime = 0;
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

        for (Usage usage : usageList) {
            chartData.add(new PieChart.Data(usage.getAppName(), usage.getUsageTime()));
            totalUsageTime += usage.getUsageTime();
        }
        pieChart.setData(chartData);
        totalTime.setText(formatTime(totalUsageTime));
        updateProductiveAppsList(usageList);
    }

    private void updateProductiveAppsList(List<Usage> usageList) {
        productiveApps.getItems().clear();
        for (Usage usage : usageList) {
            productiveApps.getItems().add(usage.getAppName() + " - " + formatTime(usage.getUsageTime()));
        }
    }

    private String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        return String.format("%dh %dm", hours, minutes);
    }

    private void startTracking() {
        // Start application tracking in the background
        scheduler.scheduleAtFixedRate(() -> {
            try {
                trackerService.trackApplications();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @FXML
    public void handleHomeButton() {
        loadView("AppUsage.fxml");
    }

    @FXML
    private void handleRapportButton() {
        loadView("Overview.fxml");
    }

    @FXML
    private void handleSessionButton() {
        loadView("FocusApp.fxml");
    }

    @FXML
    private void handleSettingsButton() {
        loadView("Setting.fxml");
    }

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/focus/view/" + fxmlFile));
            Parent root = loader.load();
            homebutton.getScene().setRoot(root);
        } catch (IOException e) {
            showError("View Error", "Unable to load the requested view.");
            e.printStackTrace();
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void stopScheduler() {
        scheduler.shutdownNow();
    }
}
