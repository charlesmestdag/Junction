package com.helha.java.q2.cinephile.Controllers;

import com.helha.java.q2.cinephile.Models.Film;
import com.helha.java.q2.cinephile.Models.FilmDb;
import com.helha.java.q2.cinephile.Views.FilmViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public class FilmController {
    private FilmViewController filmView;
    private FilmDb filmDb;

    public FilmController() {
        this.filmDb = new FilmDb();
        this.filmView = filmView;

    }
    public void start(Stage primaryStage) throws IOException, URISyntaxException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/cinephile/FilmView.fxml"));
        Parent root = loader.load();
        filmView = loader.getController();
        filmView.setFilmController(this); // Passer une instance de FilmController au FilmViewController

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Cinephile");
        primaryStage.setWidth(1000); // Largeur en pixels
        primaryStage.setHeight(700); // Hauteur en pixels
        primaryStage.show();
        loadFilms();
    }
    private void loadFilms() throws MalformedURLException, URISyntaxException {
        List<Film> films = filmDb.getAllFilms();
        filmView.displayFilms(films);
    }




    /*
@FXML
    public FlowPane flowPane;

    private FilmDb filmDb;



    public FilmController() {
    }

    public FilmController(FilmView view, FilmDb model) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filmDb = new FilmDb();
        try {
            loadImages();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadImages() throws MalformedURLException, URISyntaxException {
        List<Film> films = filmDb.getAllFilms();
        for (Film film : films) {
            ImageView FrontImage = new ImageView(new Image(film.getImage()));
            FrontImage.setFitWidth(200);
            FrontImage.setFitHeight(300);
            Rectangle clip = new Rectangle(200, 300);
            Rectangle clip2 = new Rectangle(200, 300);
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            clip2.setArcWidth(20);
            clip2.setArcHeight(20);
            String imageURL = "https://imgs.search.brave.com/ihZ8YVmYBtj0IjiPcGafKKQDZvUEg6MeG3ZGoArDP0g/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/cGhvdG9zLXByZW1p/dW0vYWJzdHJhaXQt/ZGVncmFkZS1ncmlz/Xzk2MzUtMTUxOC5q/cGc_c2l6ZT02MjYm/ZXh0PWpwZw";
            Image image = new Image(imageURL);
            ImageView backImage = new ImageView(image);
            backImage.setFitWidth(200);
            backImage.setFitHeight(300);
            backImage.setOpacity(0);
            FrontImage.setClip(clip);
            backImage.setClip(clip2);

            Text texte = new Text(film.getTitre());
            texte.setStyle(" -fx-font-weight: bold;");
            texte.setOpacity(0);
            texte.setFont(Font.font(texte.getFont().getName(), 10));
            splitTextIfNeeded(texte, film.getTitre());

            Button button = new Button("Reserver");
            button.setOpacity(0);
            button.setStyle("-fx-background-color: #4CAF50; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-size: 14px; " +
                    "-fx-padding: 10px 20px; " +
                    "-fx-background-radius: 5px; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"); // Effet d'ombre
            button.setOnMouseClicked(event -> openSchedulePage(film));




            String texteActuel = texte.getText();
            String finaltexte = texteActuel + "\n" + "\n" + "Durée : " + film.getDuree() ;
            texte.setText(finaltexte);


            StackPane stackPane = new StackPane(FrontImage, backImage, texte, button);
            StackPane.setAlignment(button, Pos.BOTTOM_CENTER);
            stackPane.setOnMouseEntered(event -> flipImage(FrontImage, backImage, texte, button)); // Utilisation de la nouvelle fonction flipImage
            stackPane.setOnMouseExited(event -> flipBackImage(backImage, FrontImage, texte, button)); // Utilisation de la nouvelle fonction flipImageBack

            flowPane.getChildren().add(stackPane);
        }

    }

    private void openSchedulePage(Film film) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/cinephile/SchedulePage.fxml"));
            Parent root = loader.load();
            ScheduleController scheduleController = loader.getController();
            scheduleController.setFilm(film);

            // Obtenez la scène actuelle
            Scene scene = flowPane.getScene();
            Stage stage = (Stage) scene.getWindow();

            // Remplacez la scène actuelle par la nouvelle page chargée
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setWidth(1150);
            stage.setHeight(800);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void flipImage(ImageView fromImageView, ImageView toPane, Text texte, Button button) {
        // Transition de mise à l'échelle pour l'image sortante
        ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.25), fromImageView);
        scaleOut.setToX(0);
        scaleOut.setInterpolator(Interpolator.EASE_BOTH);

        // Transition de mise à l'échelle pour l'image entrante
        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.25), toPane);
        scaleIn.setToX(1);
        scaleIn.setInterpolator(Interpolator.EASE_BOTH);

        ScaleTransition scaleTextIn = new ScaleTransition(Duration.seconds(0.25), texte);
        scaleTextIn.setToX(1.5); // Ajustez la valeur pour modifier l'effet d'agrandissement
        scaleTextIn.setToY(1.5); // Ajustez la valeur pour modifier l'effet d'agrandissement
        scaleTextIn.setInterpolator(Interpolator.EASE_BOTH);

        ScaleTransition scaleButtonIn = new ScaleTransition(Duration.seconds(0.25), button);
        scaleButtonIn.setToX(1.5); // Ajustez la valeur pour modifier l'effet d'agrandissement
        scaleButtonIn.setToY(1.5); // Ajustez la valeur pour modifier l'effet d'agrandissement
        scaleButtonIn.setInterpolator(Interpolator.EASE_BOTH);

        scaleOut.setOnFinished(event -> {
            fromImageView.setOpacity(0);
            toPane.setOpacity(1);
            texte.setOpacity(1);
            button.setOpacity(1);
            scaleIn.play();
            scaleTextIn.play();
            scaleButtonIn.play();
        });

        scaleOut.play();
    }

    /**
     * Réalise une transition pour faire pivoter l'image lorsque la souris quitte la zone du film.
     *
     * @param fromImageView L'image actuelle.
     * @param toPane        L'image à afficher.
     * @param texte         Le texte à afficher.
     * @param button        Le bouton à afficher.
     */
    /*
    private void flipBackImage(ImageView fromImageView, ImageView toPane, Text texte, Button button) {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.25), fromImageView);
        scaleOut.setToX(0);
        scaleOut.setInterpolator(Interpolator.EASE_BOTH);

        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.25), toPane);
        scaleIn.setToX(1);
        scaleIn.setInterpolator(Interpolator.EASE_BOTH);

        ScaleTransition scaleTextIn = new ScaleTransition(Duration.seconds(0.25), texte);
        scaleTextIn.setToX(1);
        scaleTextIn.setInterpolator(Interpolator.EASE_BOTH);

        ScaleTransition scaleButtonIn = new ScaleTransition(Duration.seconds(0.25), button);
        scaleButtonIn.setToX(1.5); // Ajustez la valeur pour modifier l'effet d'agrandissement
        scaleButtonIn.setToY(1.5); // Ajustez la valeur pour modifier l'effet d'agrandissement
        scaleButtonIn.setInterpolator(Interpolator.EASE_BOTH);

        pause.setOnFinished(event1 -> {
            texte.setOpacity(0);
            button.setOpacity(0);
            scaleOut.setOnFinished(event -> {
                fromImageView.setOpacity(0);
                toPane.setOpacity(1);
                scaleIn.play();
                scaleTextIn.play();
            });
            scaleOut.play();
        });
        pause.play();
    }

    /**
     * Divise le texte du titre si celui-ci dépasse une certaine longueur.
     *
     * @param texte Le texte à traiter.
     * @param titre Le titre du film.
     */
    /*
    private void splitTextIfNeeded(Text texte, String titre) {
        if (titre.length() > 20) {
            int splitIndex = titre.lastIndexOf(" ", 20);
            if (splitIndex != -1) {
                String firstPart = titre.substring(0, splitIndex);
                String secondPart = titre.substring(splitIndex + 1); // Ignorer l'espace
                texte.setText(firstPart + "\n" + secondPart);
            }
        }
    }
    */
}



