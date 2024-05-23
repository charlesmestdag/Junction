package com.helha.java.q2.cinephile.Views;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;


public class BancontactViewController {

    @FXML
    private TextField montantField;

    @FXML
    private Label resultLabel;
    @FXML
    private Button acceptButton;

    @FXML
    private Button rejectButton;

    private Socket clientSocket;
    private PrintWriter writer;
    private CountDownLatch latch;

    private Listener listener;
    @FXML
    private TextField promoCodeTextField;

    Double prix;

    @FXML
    private void initialize() {

        if (montantField == null) {
            System.out.println("montantLabel est null. Assurez-vous que le FXML est correctement chargé.");
        }
        promoCodeTextField.setOnAction(event -> {
            prix = Double.valueOf(montantField.getText().substring(0, montantField.getText().length() - 1));
            OnCodeEnter(promoCodeTextField.getText(), prix);
        });

        acceptButton.setOnAction(event -> {

            resultLabel.setText("Le paiement de " + prix + " € est accepté");
            // Attend 5 secondes puis ferme la fenêtre Bancontact et retourne à la page de schedule
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            sendResponseToClient("Accepter", prix);
        });

        rejectButton.setOnAction(event -> {
            resultLabel.setText("Le paiement de " + prix + " € est refusé");
            // Attend 5 secondes puis ferme la fenêtre Bancontact et retourne à la page de schedule
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            sendResponseToClient("Refuser",prix);
        });

    }


    public void setMontant(double montant) {
        Platform.runLater(() -> {
            if (montantField != null) {
                prix=montant;
                montantField.setText(String.valueOf(montant));
            } else {
                System.out.println("montantLabel est null. Assurez-vous que le FXML est correctement chargé.");
            }
        });
    }


    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    private void sendResponseToClient(String response, Double prix) {
        try {
            if (writer != null) {
                writer.println(response);
                writer.println(prix); // Envoyer le prix au client
                System.out.println("Réponse envoyée au client: " + response);
                System.out.println("Prix envoyé au client: " + prix);
            } else {
                System.out.println("Erreur: Le writer vers le socket client est null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (latch != null) {
                latch.countDown(); // Décrémente le CountDownLatch
            }
        }
    }
    private void OnCodeEnter(String code, Double prix) {
        if (listener != null){
            listener.OnCodeEnter(code, prix);
        }

    }



    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener {
        void OnCodeEnter(String code, Double prix);

    }
}



