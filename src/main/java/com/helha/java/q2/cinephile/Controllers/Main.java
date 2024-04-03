package com.helha.java.q2.cinephile.Controllers;

import com.helha.java.q2.cinephile.Models.FilmDb;
import com.helha.java.q2.cinephile.Views.FilmView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe principale de l'application Cinephile.
 */
public class Main extends Application {

    /**
     * Méthode principale appelée au démarrage de l'application.
     *
     * @param primaryStage La scène principale de l'application.
     */
    @Override
    public void start(Stage primaryStage) {
        FilmView view = new FilmView(primaryStage);
        FilmDb model = new FilmDb();
        FilmController controller = new FilmController(view, model);
    }

    /**
     * Méthode principale du programme.
     *
     * @param args Les arguments passés lors du lancement du programme.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
