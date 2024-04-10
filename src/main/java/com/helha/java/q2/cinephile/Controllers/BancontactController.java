package com.helha.java.q2.cinephile.Controllers;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BancontactController {

    @FXML
    private TextField montantField;

    @FXML
    private Label resultLabel;

    private String montant;

    public void setMontant(String montant) {
        this.montant = montant;
        montantField.setText(montant); // Afficher le montant passé depuis la page Checkout
    }

    @FXML
    private void handleAcceptButton() {
        resultLabel.setText("Le paiement de " + montant + " est accepté");
        // Attendez 10 secondes puis fermez la fenêtre Bancontact et retournez à la page de schedule
        PauseTransition delay = new PauseTransition(Duration.seconds(10));
        delay.setOnFinished(event -> {
            Stage stage = (Stage) resultLabel.getScene().getWindow();
            stage.close();
            // Retournez à la page de schedule ici
        });
        delay.play();
    }

    @FXML
    private void handleRefuseButton() {
        resultLabel.setText("Le paiement de " + montant + " est refusé");
        // Attendez 10 secondes puis fermez la fenêtre Bancontact et retournez à la page de schedule
        PauseTransition delay = new PauseTransition(Duration.seconds(10));
        delay.setOnFinished(event -> {
            Stage stage = (Stage) resultLabel.getScene().getWindow();
            stage.close();
            // Retournez à la page de schedule ici
        });
        delay.play();
    }
}


