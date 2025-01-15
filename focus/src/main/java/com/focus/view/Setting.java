package com.focus.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Setting extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Setting.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Setting");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set the minimum size for the window to ensure it's resizable but not too small
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
    }

    public static void main(String[] args) {
    
        launch(args);
    }
}
