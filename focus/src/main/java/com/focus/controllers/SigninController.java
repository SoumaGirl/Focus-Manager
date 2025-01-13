package com.focus.controllers;

import com.focus.DAO.UserDAO;
import com.focus.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SigninController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Text forgetPasswordText;

    @FXML
    private Text signUpText;

    private UserDAO userDAO;

    // Initialization method
    @FXML
    public void initialize() {
        try {
            userDAO = new UserDAO();  // Instantiate DAO class
        } catch (SQLException e) {
            showError("Database Error", "Unable to connect to the database. Please try again later.");
            e.printStackTrace();
        }

        // Add event handler for the "Sign Up" text
        signUpText.setOnMouseClicked(event -> handleSignUpRedirect());
    }

    // Handle Sign In button click
    @FXML
    private void handleSignInClick() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showError("Validation Error", "Please enter both email and password.");
            return;
        }

        try {
            System.out.println("Attempting to log in with email: " + email);
            User user = userDAO.getUserByEmailAndPassword(email, password);

            if (user != null) {
                System.out.println("Login successful for email: " + email);
                showInfo("Login Successful", "Welcome back, " + user.getEmail() + "!");
                navigateToAppUsage();
            } else {
                System.out.println("Login failed: Incorrect credentials for email: " + email);
                showError("Login Failed", "Incorrect email or password. Please try again.");
            }
        } catch (SQLException e) {
            showError("Database Error", "An error occurred while trying to log in. Please try again later.");
            System.out.println("SQLException occurred during login:");
            e.printStackTrace();
        }
    }

    // Navigate to AppUsage screen after successful login
    private void navigateToAppUsage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/focus/view/AppUsage.fxml"));
            Stage stage = (Stage) signInButton.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            showError("Navigation Error", "Unable to load the application screen. Please try again.");
            e.printStackTrace();
        }
    }

    // Redirect to Sign Up screen
    private void handleSignUpRedirect() {
        try {
            // Load the Sign Up screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/focus/view/Signup.fxml"));
            Parent signUpPage = loader.load();

            // Create a new scene and set it to the current stage
            Stage stage = (Stage) signInButton.getScene().getWindow();
            stage.getScene().setRoot(signUpPage);
        } catch (IOException e) {
            showError("Navigation Error", "Unable to load the sign-up screen. Please try again.");
            e.printStackTrace();
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
}
