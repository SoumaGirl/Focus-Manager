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
        Stage stage = primaryStage;
        stage.setMinWidth(1024);  // Minimum width
        stage.setMinHeight(768); // Minimum height

    }

    public static void main(String[] args) {
    
        launch(args);
    }
}
