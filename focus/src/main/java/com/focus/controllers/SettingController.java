package com.focus.controllers;

import com.focus.DAO.UserSettingsDAO;
import com.focus.Model.UserSettings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;
import java.io.IOException;

public class SettingController {

    @FXML
    private Button homebutton;

    @FXML
    private Button rapportButton;

    @FXML
    private Button sesionButton;

    @FXML
    private Button settingsButton;

    // FXML Fields
    @FXML
    public ComboBox<String> alarmSoundComboBox;
    @FXML
    public ComboBox<Integer> intervalsComboBox;
    @FXML
    private ComboBox<String> focusTimeComboBox;
    @FXML
    private ComboBox<String> shortBreakComboBox;
    @FXML
    private ComboBox<String> longBreakComboBox;

    // DAO for database interaction
    private UserSettingsDAO settingsDAO;

    // Initialization method
    @FXML
    public void initialize() {
        try {
            settingsDAO = new UserSettingsDAO();
        } catch (SQLException e) {
            showError("Database Connection Error", "Unable to connect to the database. Please try again later.");
            e.printStackTrace();  // Log the stack trace for debugging
            return;
        }

        // Load user settings from the database
        UserSettings userSettings = settingsDAO.getUserSettings(1); // Assume user ID is 1 for now

        if (userSettings != null) {
            // Populate ComboBoxes with options
            alarmSoundComboBox.getItems().addAll("Annonce", "Silence");
            alarmSoundComboBox.setValue(userSettings.getAlarmSound());

            intervalsComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            intervalsComboBox.setValue(userSettings.getIntervals());

            focusTimeComboBox.getItems().addAll("20min", "25min", "30min", "35min", "40min");
            focusTimeComboBox.setValue(userSettings.getFocusTime());

            shortBreakComboBox.getItems().addAll("3min", "5min", "7min", "10min");
            shortBreakComboBox.setValue(userSettings.getShortBreak());

            longBreakComboBox.getItems().addAll("10min", "15min", "20min", "25min");
            longBreakComboBox.setValue(userSettings.getLongBreak());
        } else {
            // Initialize default values if no settings are found
            initializeDefaultValues();
        }
    }

    private void initializeDefaultValues() {
        alarmSoundComboBox.getItems().addAll("Annonce", "Silence");
        alarmSoundComboBox.setValue("Annonce");

        intervalsComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        intervalsComboBox.setValue(4);

        focusTimeComboBox.getItems().addAll("20min", "25min", "30min", "35min", "40min");
        focusTimeComboBox.setValue("25min");

        shortBreakComboBox.getItems().addAll("3min", "5min", "7min", "10min");
        shortBreakComboBox.setValue("5min");

        longBreakComboBox.getItems().addAll("10min", "15min", "20min", "25min");
        longBreakComboBox.setValue("15min");
    }

    // Handles the save button to persist settings in the database
    @FXML
    private void handleSaveSettingsButton() {
        try {
            // Ensure ComboBox values are not null
            if (alarmSoundComboBox.getValue() == null) {
                alarmSoundComboBox.setValue("Annonce");  // Default value
            }
            if (focusTimeComboBox.getValue() == null) {
                focusTimeComboBox.setValue("25min");  // Default value
            }
            if (shortBreakComboBox.getValue() == null) {
                shortBreakComboBox.setValue("5min");  // Default value
            }
            if (longBreakComboBox.getValue() == null) {
                longBreakComboBox.setValue("15min");  // Default value
            }

            // Get selected values from ComboBoxes and create a UserSettings object
            UserSettings newSettings = new UserSettings(
                1, // Assume user ID is 1
                alarmSoundComboBox.getValue(),
                intervalsComboBox.getValue(),
                focusTimeComboBox.getValue(),
                shortBreakComboBox.getValue(),
                longBreakComboBox.getValue()
            );

            System.out.println("Saving Settings: " + newSettings);

            // Check if user settings exist for the given ID before updating or inserting
            if (settingsDAO.userSettingsExist(1)) {
                settingsDAO.updateUserSettings(newSettings);  // Update if settings exist
                showInfo("Success", "Settings have been updated successfully!");
            } else {
                settingsDAO.saveUserSettings(newSettings);   // Insert if settings do not exist
                showInfo("Success", "Settings have been saved successfully!");
            }
        } catch (SQLException e) {
            String errorMessage = "Unable to save the settings. Please try again later.\n\n";
            errorMessage += "Error: " + e.getMessage();
            showError("Database Update Error", errorMessage);
            e.printStackTrace();  // Log the stack trace for debugging
        }
    }

    // Show an informational alert
    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Show an error alert
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Handles navigation to Home view
    @FXML
    public void handleHomeButton() {
        loadView("AppUsage.fxml");
    }

    // Handles navigation to Rapport view
    @FXML
    private void handleRapportButton() {
        loadView("Overview.fxml");
    }

    // Handles navigation to Session view
    @FXML
    private void handleSessionButton() {
        loadView("FocusApp.fxml");
    }

    // Handles navigation to Settings view
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
            showError("Error", "An error occurred while loading the view.");
            e.printStackTrace();  // Log the stack trace for debugging
        }
    }
}
