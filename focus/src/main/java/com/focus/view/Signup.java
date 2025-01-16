package com.focus.view;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Signup extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Scene scene = new Scene(loader.load());
        
        // Définir le titre de la fenêtre et ajouter la scène
        primaryStage.setTitle("Sign up");
        primaryStage.setScene(scene);
        primaryStage.show();

        
    }

    public static void main(String[] args) {
        launch(args);
    }
}

