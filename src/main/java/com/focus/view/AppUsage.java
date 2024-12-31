package com.focus.view;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppUsage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/focus/view/AppUsage.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("FocusManager Dashboard");
        primaryStage.show();
    }
 // Ch
    public static void main(String[] args) {
        launch(args);
    }
}
