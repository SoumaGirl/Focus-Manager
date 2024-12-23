package com.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import com.focus.controllers.SettingController;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class SettingControllerTest extends ApplicationTest {

    private SettingController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/focus/view/Setting.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        // Ensure the controller is initialized
        assertNotNull(controller);
    }

    @Test
    public void testInitializeComboBoxValues() {
        // Test Alarm Sound ComboBox
        assertNotNull(controller.alarmSoundComboBox);
        assertEquals("Annonce", controller.alarmSoundComboBox.getValue());
        assertTrue(controller.alarmSoundComboBox.getItems().containsAll(
            Arrays.asList("Annonce", "Silence")
        ));

        // Test Intervals ComboBox
        assertNotNull(controller.intervalsComboBox);
        assertEquals(4, controller.intervalsComboBox.getValue());
        assertTrue(controller.intervalsComboBox.getItems().containsAll(
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        ));
    }

    @Test
    public void testHandleHomeButton() {
        // Simulate clicking the Home button
        clickOn("#homebutton");

        // Verify that the Home view is loaded (if possible, mock the loadView behavior)
        // Here we assume the loadView method works as expected since it sets the scene root
        // Verification will depend on your testing setup
    }

    @Test
    public void testHandleRapportButton() {
        // Simulate clicking the Rapport button
        clickOn("#rapportButton");
        // Add assertions or verifications as needed
    }

    @Test
    public void testHandleSessionButton() {
        // Simulate clicking the Session button
        clickOn("#sesionButton");
        // Add assertions or verifications as needed
    }

    @Test
    public void testHandleSettingsButton() {
        // Simulate clicking the Settings button
        clickOn("#settingsButton");
        // Add assertions or verifications as needed
    }
}
