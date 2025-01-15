package com.focus.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Overview extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/focus/view/Overview.fxml"));
        primaryStage.setTitle("Overview");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

       // Set the minimum size for the window to ensure it's resizable but not too small
       primaryStage.setMinWidth(800);
       primaryStage.setMinHeight(600);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
