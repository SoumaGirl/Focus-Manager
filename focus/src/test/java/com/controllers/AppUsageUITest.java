package com.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class AppUsageUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new com.focus.view.AppUsage().start(stage); // Lancer l'application principale
    }

    @Test
    void testPieChartInitialization() {
        // Vérification que le PieChart est présent
        PieChart pieChart = lookup("#pieChart").query();
        FxAssert.verifyThat("#pieChart", NodeMatchers.isVisible());

        // Vérification du nombre de données dans le PieChart
        assertEquals(7, pieChart.getData().size(), "Le PieChart devrait contenir 7 éléments.");

        // Vérification des noms et des valeurs des segments
        assertEquals("PowerPoint", pieChart.getData().get(0).getName(), "Le premier segment devrait être 'PowerPoint'.");
        assertEquals(25, pieChart.getData().get(0).getPieValue(), "La valeur pour 'PowerPoint' devrait être 25.");
    }

    @Test
    void testLabelsAreDisplayedCorrectly() {
        // Vérification des labels principaux
        FxAssert.verifyThat("#totalTime", NodeMatchers.isVisible());
        FxAssert.verifyThat("#productiveTime", NodeMatchers.isVisible());
        FxAssert.verifyThat("#productivePercent", NodeMatchers.isVisible());
        FxAssert.verifyThat("#unproductiveTime", NodeMatchers.isVisible());
        FxAssert.verifyThat("#unproductivePercent", NodeMatchers.isVisible());

        // Vérification des valeurs
        Label totalTime = lookup("#totalTime").query();
        assertEquals("32h 59m", totalTime.getText(), "Le label 'totalTime' devrait afficher '32h 59m'.");

        Label productiveTime = lookup("#productiveTime").query();
        assertEquals("32h 47m", productiveTime.getText(), "Le label 'productiveTime' devrait afficher '32h 47m'.");

        Label unproductiveTime = lookup("#unproductiveTime").query();
        assertEquals("3h 12m", unproductiveTime.getText(), "Le label 'unproductiveTime' devrait afficher '3h 12m'.");
    }

    @Test
    void testListViewsArePopulated() {
        // Vérification des listes de productivité
        ListView<String> productiveApps = lookup("#productiveApps").query();
        ListView<String> unproductiveApps = lookup("#unproductiveApps").query();

        // Vérification des items dans la liste productiveApps
        assertEquals(4, productiveApps.getItems().size(), "La liste productiveApps devrait contenir 4 éléments.");
        assertEquals("Canva - 4h 50m", productiveApps.getItems().get(0), "Le premier élément devrait être 'Canva - 4h 50m'.");

        // Vérification des items dans la liste unproductiveApps
        assertEquals(3, unproductiveApps.getItems().size(), "La liste unproductiveApps devrait contenir 3 éléments.");
        assertEquals("Instagram - 1h 30m", unproductiveApps.getItems().get(0), "Le premier élément devrait être 'Instagram - 1h 30m'.");
    }
}