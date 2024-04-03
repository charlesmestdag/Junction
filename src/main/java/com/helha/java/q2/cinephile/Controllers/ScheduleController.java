package com.helha.java.q2.cinephile.Controllers;
import com.helha.java.q2.cinephile.Models.Film;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleController {

    @FXML
    private AnchorPane SCREEN1;

    @FXML
    private AnchorPane SCREEN2;

    @FXML
    private AnchorPane SCREEN3;

    @FXML
    private Button buyticketbtn;

    @FXML
    private Button detailsbtn;

    @FXML
    private AnchorPane mainmoviespane;

    @FXML
    private Label movieduration;

    @FXML
    private ImageView movieimage;

    @FXML
    private AnchorPane moviepane;

    @FXML
    private Label movierating;

    @FXML
    private Label movierepeats;

    @FXML
    private Label movieticketsold;

    @FXML
    private Label movietitle;

    @FXML
    private Button otherschedulebtn;

    @FXML
    private AnchorPane tablepane;

    @FXML
    private AnchorPane toppane;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        buyticketbtn.setOnAction(event -> openCheckoutPage());
        backButton.setOnAction(event -> goBack());
    }

    public void setFilm(Film film) {
        movietitle.setText(film.getTitre());
        movieduration.setText(film.getDuree());
    }
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/cinephile/FilmView.fxml"));
            Parent root = loader.load();

            // Obtenez la scène actuelle
            Scene scene = backButton.getScene();
            Stage stage = (Stage) scene.getWindow();

            // Remplacez la scène actuelle par la nouvelle page chargée
            scene.setRoot(root);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openCheckoutPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/cinephile/checkout.fxml"));
            Parent root = loader.load();

            //Stage stage = new Stage();
            //stage.setScene(new Scene(root));
            //stage.show();


            // Obtenez la scène actuelle
            Scene scene = mainmoviespane.getScene();
            Stage stage = (Stage) scene.getWindow();

            // Remplacez la scène actuelle par la nouvelle page chargée
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setWidth(875);
            stage.setHeight(800);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
