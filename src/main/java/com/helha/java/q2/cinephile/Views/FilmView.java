package com.helha.java.q2.cinephile.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Vue pour afficher la liste des films.
 */
public class FilmView {
    private Stage primaryStage;

    /**
     * Constructeur de la classe FilmView.
     *
     * @param primaryStage La scène principale de l'application.
     */
    public FilmView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        init();
    }

    /**
     * Initialise la vue en chargeant le fichier FXML et en affichant la fenêtre principale.
     */
    private void init() {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/cinephile/FilmView.fxml"));

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


