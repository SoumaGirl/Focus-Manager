package com.focus.controllers;

import com.focus.DAO.UserDAO;
import com.focus.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    private UserDAO userDAO;

    public SignupController() {
        try {
            this.userDAO = new UserDAO(); // Initialize UserDAO
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Database connection failed.");
        }
    }

    @FXML
    private void handleSignInClick(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        // Validate inputs
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Passwords do not match.");
            return;
        }

        try {
            // Check if user already exists
            if (userDAO.userExists(email)) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "User with this email already exists.");
                return;
            }

            // Create a new user
            User user = new User(email, password); // Ensure password is hashed if necessary
            if (userDAO.createUser(user)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "User signed up successfully.");
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to sign up. Please try again.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
        }
    }

    // Redirect to SignIn page
    @FXML
    private void handleSignInRedirect() {
        try {
            // Load the sign-in screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/focus/view/Signin.fxml"));
            AnchorPane signInPage = loader.load();

            // Create a new scene and set it to the current stage
            Stage stage = (Stage) emailField.getScene().getWindow();
            Scene scene = new Scene(signInPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to load sign-in screen.");
        }
    }

    private void clearFields() {
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
