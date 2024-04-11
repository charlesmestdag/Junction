package com.helha.java.q2.cinephile.Controllers;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Contrôleur pour la page Bancontact.
 */
public class BancontactController {
    /**
     * Champ de texte pour afficher le montant.
     */
    @FXML
    private TextField montantField;

    /**
     * Étiquette pour afficher le résultat du paiement.
     */
    @FXML
    private Label resultLabel;

    /**
     * Montant de la transaction.
     */
    private String montant;

    /**
     * Méthode pour définir le montant de la transaction.
     *
     * @param montant Le montant de la transaction à afficher dans la page Bancontact.
     */
    public void setMontant(String montant) {
        this.montant = montant;
        montantField.setText(montant); // Afficher le montant passé depuis la page Checkout
    }

    /**
     * Méthode exécutée lors du clic sur le bouton Accepter.
     * Affiche un message indiquant que le paiement est accepté pendant 5 secondes
     * et retourne à la page de schedule.
     */
    @FXML
    private void handleAcceptButton() {
        resultLabel.setText("Le paiement de " + montant + " est accepté");
        // Attend 5 secondes puis ferme la fenêtre Bancontact et retourne à la page de schedule
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> {
            Stage stage = (Stage) resultLabel.getScene().getWindow();
            stage.close();
            // Retourne à la page de schedule ici
        });
        delay.play();
    }

    /**
     * Méthode exécutée lors du clic sur le bouton Refuser.
     * Affiche un message d'annulation pendant 5 secondes
     * et retourne à la page de schedule.
     */
    @FXML
    private void handleRefuseButton() {
        resultLabel.setText("Le paiement de " + montant + " est refusé");
        // Attend 5 secondes puis ferme la fenêtre Bancontact et retourne à la page de schedule
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> {
            Stage stage = (Stage) resultLabel.getScene().getWindow();
            stage.close();
            // Retourne à la page de schedule ici
        });
        delay.play();
    }
}


