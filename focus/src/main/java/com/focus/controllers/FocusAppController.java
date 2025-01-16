package com.focus.controllers;

import com.focus.DAO.PomodoroDAO;
import com.focus.Model.Pomodoro;
import com.focus.Service.AppTrackerService;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;

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

    private AppTrackerService appTrackerService;
    private ScheduledExecutorService scheduler;

    private Timeline timeline;
    private int secondsElapsed = 0;
    private LocalDateTime startTime;
    private PomodoroDAO pomodoroDAO;

    public FocusAppController() {
        try {
            pomodoroDAO = new PomodoroDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        try {
            Pomodoro activePomodoro = pomodoroDAO.getActivePomodoro(1); // Assuming user ID is 1

            if (activePomodoro != null) {
                // Resume an active session
                LocalDateTime now = LocalDateTime.now();
                secondsElapsed = (int) java.time.Duration.between(activePomodoro.getSessionDate(), now).getSeconds();

                // Update the UI
                timerLabel.setText(formatTime(secondsElapsed));
                startTimeLabel.setText("Start Time: " + activePomodoro.getSessionDate().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                endTimeLabel.setText("End Time: " + activePomodoro.getSessionEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                startButton.setDisable(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        startButton.setOnAction(event -> startTimer());
        stopButton.setOnAction(event -> stopTimer());
    }

    private void updateTimer() {
        secondsElapsed++;
        timerLabel.setText(formatTime(secondsElapsed));
    }

    private void startTimer() {
        timeline.play();
        secondsElapsed = 0;

        startTime = LocalDateTime.now();
        startTimeLabel.setText("Start Time: " + startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        startButton.setDisable(true);
    }

    private void stopTimer() {
        timeline.stop();

        LocalDateTime endTime = LocalDateTime.now();
        endTimeLabel.setText("End Time: " + endTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        savePomodoroSession(endTime);
        secondsElapsed = 0;
        timerLabel.setText("00:00");
        startButton.setDisable(false);
    }

    private void savePomodoroSession(LocalDateTime endTime) {
        try {
            int sessionDuration = secondsElapsed / 60;
            int breakDuration = 5; // Example fixed break duration
            LocalDate sessionDate = startTime.toLocalDate();
    
            // Use the updated constructor
            Pomodoro pomodoro = new Pomodoro(0, 1, sessionDuration, breakDuration, sessionDate, startTime);
    
            // Save the Pomodoro session
            pomodoroDAO.addPomodoro(pomodoro);
            System.out.println("Pomodoro session saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @FXML
    private void handleHomeButton() {
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
            Parent root = FXMLLoader.load(getClass().getResource("/com/focus/view/" + fxmlFile));
            ((Node) homebutton).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
