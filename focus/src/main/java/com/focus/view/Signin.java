package com.focus.view; 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Signin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        Scene scene = new Scene(loader.load());
        
        // Définir le titre de la fenêtre et ajouter la scène
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set the minimum size for the window to ensure it's resizable but not too small
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(800);

        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
