package com.controllers;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FocusManagerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        // Initialisation de l'application FocusManager
        new com.focus.view.FocusManager().start(stage);
    }

    @Test
    public void testUIElementsAreDisplayed() {
        // Vérifier que le texte "Welcome to" est visible
        FxAssert.verifyThat("Welcome to", isVisible());

        // Vérifier que l'ImageView est visible
        ImageView logoImageView = lookup("#logoImageView").query();
        FxAssert.verifyThat(logoImageView, isVisible());

        // Vérifier que le bouton "Get Started" est visible et affiche le bon texte
        FxAssert.verifyThat("#getStartedButton", LabeledMatchers.hasText("Get Started"));
    }

    @Test
    public void testGetStartedButtonAction() {
        // Récupérer le bouton "Get Started"
        Button getStartedButton = lookup("#getStartedButton").query();

        // Simuler un clic sur le bouton
        clickOn(getStartedButton);

        // Ajouter des vérifications en fonction de l'action déclenchée (par exemple, changement de scène)
        FxAssert.verifyThat("#getStartedButton", LabeledMatchers.hasText("Get Started"));

        // Si une nouvelle scène est chargée, vous pouvez vérifier son contenu ici
        // Exemple : FxAssert.verifyThat("Next Scene Title", isVisible());
    }
}