package com.focus.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import com.focus.DAO.UserDAO;
import com.focus.Model.SessionManager;

public class FocusManagerController {

    @FXML
    private ImageView logoImageView;

    public void initialize() {
        try {
            // Debug log to ensure the initialize method is called
            System.out.println("Initializing controller...");

            // Charger l'image depuis les ressources
            Image logoImage = new Image(getClass().getResourceAsStream("/com/focus/images/logo.jpg"));
            if (logoImage != null) {
                logoImageView.setImage(logoImage);
                System.out.println("Logo image loaded successfully.");
            } else {
                System.out.println("Logo image could not be loaded.");
            }
        } catch (Exception e) {
            System.out.println("Error during image loading: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGetStarted() {
        System.out.println("Get Started button clicked!");
        try {
            UserDAO userDAO = new UserDAO(); // Initialize UserDAO

            // Check if the user is signed in using the updated logic
            boolean isSignedIn = userDAO.isUserSignedIn();
            System.out.println("User signed in: " + isSignedIn);

            // Debugging log: Check if SessionManager is set correctly
            if (SessionManager.isUserSignedIn()) {
                System.out.println("SessionManager indicates the user is signed in with user ID: " + SessionManager.getCurrentUserId());
            } else {
                System.out.println("SessionManager does not indicate a user is signed in.");
            }

            // Navigate to the appropriate screen based on the session status
            String fxmlFile = isSignedIn ? "/com/focus/view/AppUsage.fxml" : "/com/focus/view/Signin.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));

            // Change the scene
            Stage stage = (Stage) logoImageView.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (SQLException e) {
            System.out.println("Error initializing UserDAO: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // Handle case when no user is signed in and session is invalid
            System.out.println("User not signed in: " + e.getMessage());
        }
    }
}
