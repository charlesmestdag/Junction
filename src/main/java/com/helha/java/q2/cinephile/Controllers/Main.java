package com.helha.java.q2.cinephile.Controllers;

import com.helha.java.q2.cinephile.Models.FilmDb;
import com.helha.java.q2.cinephile.Views.FilmView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        FilmView view = new FilmView(primaryStage);
        FilmDb model = new FilmDb();
        FilmController controller = new FilmController(view, model);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
