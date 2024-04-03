package com.helha.java.q2.cinephile.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FilmView {
    private Stage primaryStage;

    public FilmView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        init();
    }

    private void init() {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/cinephile/filmView.fxml"));

            Parent root = loader.load();
            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Configurer la scène et afficher la fenêtre principale
            primaryStage.setScene(scene);
            primaryStage.setTitle("Liste des Films");
            primaryStage.setWidth(1000); // Largeur en pixels
            primaryStage.setHeight(700); // Hauteur en pixels
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

