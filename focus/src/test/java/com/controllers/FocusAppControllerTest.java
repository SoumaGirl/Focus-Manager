package com.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import com.focus.controllers.FocusAppController;
import static org.junit.jupiter.api.Assertions.*;

public class FocusAppControllerTest extends ApplicationTest {

    private FocusAppController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/focus/view/FocusApp.fxml"));
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
    public void testInitialize() {
        // Ensure the initial state of the controller
        assertNotNull(controller.timerLabel);
        assertEquals("00:00", controller.timerLabel.getText());
        assertNotNull(controller.startButton);
        assertNotNull(controller.stopButton);

        // Ensure the start button is enabled initially
        assertFalse(controller.startButton.isDisable());
    }

    @Test
    public void testStartButtonAction() {
        // Simulate clicking the Start button
        clickOn("#startButton");

        // Wait for the UI to update after clicking Start
        WaitForAsyncUtils.waitForFxEvents();

        // Verify that the start button is disabled after starting the timer
        assertTrue(controller.startButton.isDisable(), "Start button should be disabled after starting");

        // Wait for a short time (1 second) to let the timer update
        try {
            Thread.sleep(3000);  // Wait for 1 second to allow the timer to update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the timer starts updating (timer label should not be "00:00")
        String timerText = controller.timerLabel.getText();
        assertNotEquals("00:00", timerText, "Timer should start counting");
    }

    @Test
    public void testStopButtonAction() throws InterruptedException {
        // Simulate clicking the Start button first to start the timer
        clickOn("#startButton");

        // Wait for 6 seconds to allow the timer to count
        Thread.sleep(4000);  // Sleep for 6 seconds to simulate timer counting

        // Verify that the timer label has updated (it should not be "00:00")
        assertNotEquals("00:00", controller.timerLabel.getText(), "Timer should update after 6 seconds");

        // Simulate clicking the Stop button to stop the timer
        clickOn("#stopButton");

        // Wait for 5 seconds to allow time for the UI to update
        Thread.sleep(5000);  // Sleep for 5 seconds after clicking Stop

        // Verify that the timer stops and resets to "00:00"
        assertEquals("00:00", controller.timerLabel.getText(), "Timer should reset to 00:00 after stop");

        // Verify that the start button is re-enabled after stopping the timer
        assertFalse(controller.startButton.isDisable(), "Start button should be re-enabled after stopping");
    }

    @Test
    public void testHandleHomeButton() {
        // Simulate clicking the Home button
        clickOn("#homebutton");

        // Add verifications for home view load
        // You would mock or verify that the scene changes as expected, but for now, the test can assume the scene transition works
    }

    @Test
    public void testHandleRapportButton() {
        // Simulate clicking the Rapport button
        clickOn("#rapportButton");

        // Add verifications for Rapport view load
    }

    @Test
    public void testHandleSessionButton() {
        // Simulate clicking the Session button
        clickOn("#sesionButton");

        // Add verifications for Session view load
    }

    @Test
    public void testHandleSettingsButton() {
        // Simulate clicking the Settings button
        clickOn("#settingsButton");

        // Add verifications for Settings view load
    }
}
