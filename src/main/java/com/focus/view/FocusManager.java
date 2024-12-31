package com.focus.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FocusManager extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/focus/view/focus_manager.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
 // Ch
    public static void main(String[] args) {
        launch(args);
    }
}
