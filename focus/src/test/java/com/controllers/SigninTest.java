package com.controllers;


import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SigninTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        
        new com.focus.view.Signin().start(stage);
    }

    @Test
    public void testUIElementsAreDisplayed() {
        // Verify that the title "Sign in" is visible
        FxAssert.verifyThat("Sign in", isVisible());

        // Verify that the email field is visible
        FxAssert.verifyThat("#emailField", isVisible());

        // Verify that the password field is visible
        FxAssert.verifyThat("#passwordField", isVisible());

        // Verify that the "Sign In" button is visible with the correct text
        FxAssert.verifyThat("#signInButton", LabeledMatchers.hasText("Sign In"));

        // Verify that the link "Don't have an account? Sign Up" is visible
        FxAssert.verifyThat("Don't have an account? Sign Up", isVisible());
    }

    @Test
    public void testSignInButtonAction() {
        // Enter email and password
        TextField emailField = lookup("#emailField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        emailField.setText("test@example.com");
        passwordField.setText("password123");

        // Click the "Sign In" button
        Button signInButton = lookup("#signInButton").query();
        clickOn(signInButton);

        // Add additional assertions to verify sign-in logic
        // Example: Verify console output or scene change
        FxAssert.verifyThat("#signInButton", LabeledMatchers.hasText("Sign In"));
    }
}
