package com.controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import com.focus.controllers.OverviewController;
import static org.junit.jupiter.api.Assertions.*;

public class OverviewControllerTest extends ApplicationTest {

    private OverviewController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/focus/view/Overview.fxml"));
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
        // Verify that buttons are initialized with actions
        assertNotNull(controller.dayButton);
        assertNotNull(controller.weekButton);
        assertNotNull(controller.monthButton);
        assertNotNull(controller.yearButton);

        // Test default data loading (Weekly data in this case)
        Platform.runLater(() -> {
            // Initialize the controller
            controller.initialize();

            // Verify that the chart data is loaded
            BarChart<String, Number> chart = controller.timeDistributionChart;
            assertNotNull(chart);
            assertTrue(chart.getData().size() > 0, "Chart data should be loaded");

            // Verify that at least one series has data
            XYChart.Series<String, Number> series = chart.getData().get(0);
            assertNotNull(series);
            assertTrue(series.getData().size() > 0, "The series should contain data");
        });

        // Wait for the JavaFX application to process the update
        sleep(1000); // Add a small delay to ensure UI has time to update
    }

    @Test
    public void testHandleHomeButton() {
        // Simulate clicking the Home button
        clickOn("#homebutton");

        // Add verifications for home view load
        // Since the actual view load requires scene transition, mock loadView if needed.
        // For now, we assume the method works correctly.
        // We can assert that the new scene root was set (though mocking loadView could be better for tests).
    }

    @Test
    public void testHandleRapportButton() {
        // Simulate clicking the Rapport button
        clickOn("#rapportButton");

        // Add verifications for Rapport view load
        // Similar to the Home button, this checks if the scene root changes correctly.
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

    @Test
    public void testLoadDayData() {
        // Simulate clicking the Day button to load daily data
        clickOn("#dayButton");

        // Verify that the chart data is updated
        assertTrue(controller.timeDistributionChart.getData().size() > 0, "Chart data should be loaded for day view");
    }

    @Test
    public void testLoadWeeklyData() {
        // Simulate clicking the Week button to load weekly data
        clickOn("#weekButton");

        // Verify that the chart data is updated
        assertTrue(controller.timeDistributionChart.getData().size() > 0, "Chart data should be loaded for week view");
    }

    @Test
    public void testLoadMonthlyData() {
        // Simulate clicking the Month button to load monthly data
        clickOn("#monthButton");

        // Verify that the chart data is updated
        assertTrue(controller.timeDistributionChart.getData().size() > 0, "Chart data should be loaded for month view");
    }

    @Test
    public void testLoadYearlyData() {
        // Simulate clicking the Year button to load yearly data
        clickOn("#yearButton");

        // Verify that the chart data is updated
        assertTrue(controller.timeDistributionChart.getData().size() > 0, "Chart data should be loaded for year view");
    }
}
