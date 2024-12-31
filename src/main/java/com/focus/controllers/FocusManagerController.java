package com.focus.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FocusManagerController {

    @FXML
    private ImageView logoImageView;
    public void initialize() {
        try {
            
            Image logoImage = new Image(getClass().getResourceAsStream("/com/focus/images/logo.jpg"));
            logoImageView.setImage(logoImage);
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de l'image: " + e.getMessage());
        }
    }

    @FXML
    private void handleGetStarted() {
        System.out.println("Bouton 'Get Started' cliqué !");
        // Ajoutez ici la logique pour le clic du bouton
    }
}
