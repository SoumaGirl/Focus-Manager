package com.focus.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
import java.util.Arrays;

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
    @FXML private ComboBox<String> focusTimeComboBox;
    @FXML private ComboBox<String> shortBreakComboBox;
    @FXML private ComboBox<String> longBreakComboBox;

    // Initialization method
    @FXML
    public void initialize() {
        // Initialize Alarm Sound Options
        alarmSoundComboBox.getItems().addAll("Annonce", "Silence");
        alarmSoundComboBox.setValue("Annonce");

        // Initialize Intervals Options
        intervalsComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        intervalsComboBox.setValue(4);

        // Initialize Focus Time Options
        focusTimeComboBox.getItems().addAll("20min", "25min", "30min", "35min", "40min");
        focusTimeComboBox.setValue("25min");

        // Initialize Short Break Options
        shortBreakComboBox.getItems().addAll("3min", "5min", "7min", "10min");
        shortBreakComboBox.setValue("5min");

        // Initialize Long Break Options
        longBreakComboBox.getItems().addAll("10min", "15min", "20min", "25min");
        longBreakComboBox.setValue("15min");

    }
      @FXML
    public void handleHomeButton() {
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
}
