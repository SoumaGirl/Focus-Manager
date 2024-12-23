package com.focus.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class FocusAppController {

    @FXML
    private Button homebutton;

    @FXML
    private Button rapportButton;

    @FXML
    private Button sesionButton;

    @FXML
    private Button settingsButton;


    @FXML
    public Label timerLabel;

    @FXML
    private Label startTimeLabel;

    @FXML
    private Label endTimeLabel;

    @FXML
    public Button startButton;

    @FXML
    public Button stopButton;

    private Timeline timeline;
    private int secondsElapsed = 0;
    private LocalTime startTime;

    /**
     * Initialize the controller.
     */
    @FXML
    public void initialize() {
        // Initialize the timeline for the timer
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Start button action
        startButton.setOnAction(event -> startTimer());

        // Stop button action
        stopButton.setOnAction(event -> stopTimer());
    }

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

    private void updateTimer() {
        secondsElapsed++;
        int minutes = secondsElapsed / 60;
        int seconds = secondsElapsed % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void startTimer() {
        timeline.play();
        secondsElapsed = 0;

        // Record and display the start time
        startTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        startTimeLabel.setText("Start Time: " + startTime.format(formatter));

        // Disable the start button while the timer is running
        startButton.setDisable(true);
    }

    private void stopTimer() {
        timeline.stop();

        // Record and display the end time
        LocalTime endTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        endTimeLabel.setText("End Time: " + endTime.format(formatter));

        // Reset the timer and re-enable the start button
        secondsElapsed = 0;
        timerLabel.setText("00:00");
        startButton.setDisable(false);
    }
}
