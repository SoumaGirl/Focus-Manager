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

public class SignupTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        // Lancer l'application Signup
        new com.focus.view.Signup().start(stage);
    }

    @Test
    public void testUIElementsAreDisplayed() {
        // Vérifier que le titre "Sign up" est visible
        FxAssert.verifyThat("Sign up", isVisible());

        // Vérifier que le champ email est visible
        FxAssert.verifyThat("#emailField", isVisible());

        // Vérifier que le champ mot de passe est visible
        FxAssert.verifyThat("#passwordField", isVisible());

        // Vérifier que le champ de confirmation de mot de passe est visible
        FxAssert.verifyThat("#confirmPasswordField", isVisible());

        // Vérifier que le bouton "Sign up" est visible avec le bon texte
        FxAssert.verifyThat("#signupButton", LabeledMatchers.hasText("Sign up"));
    }

    @Test
    public void testSignupButtonAction() {
        // Récupérer les champs de saisie
        TextField emailField = lookup("#emailField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        PasswordField confirmPasswordField = lookup("#confirmPasswordField").query();

        // Simuler la saisie des données
        emailField.setText("test@example.com");
        passwordField.setText("password123");
        confirmPasswordField.setText("password123");

        // Récupérer le bouton "Sign up"
        Button signupButton = lookup("#signupButton").query();

        // Simuler un clic sur le bouton
        clickOn(signupButton);

        // Vérifier que le bouton reste visible après le clic
        FxAssert.verifyThat("#signupButton", LabeledMatchers.hasText("Sign up"));

        // Ajouter des assertions supplémentaires pour vérifier la logique métier (par exemple, changement de scène ou affichage d'un message)
        // Exemple : Vérifiez qu'une nouvelle scène est affichée ou qu'un message d'erreur apparaît.
        // FxAssert.verifyThat("Welcome Page", isVisible());
    }
}