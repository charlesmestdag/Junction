package com.helha.java.q2.cinephile.Views;

import com.helha.java.q2.cinephile.patternFactory.PaymentMethod;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur pour la vue de la page de paiement.
 */
public class CheckoutViewController {

    @FXML
    private TextField adultTextField;

    @FXML
    private TextField childTextField;

    @FXML
    private TextField seniorTextField;

    @FXML
    private TextField pmrTextField;

    @FXML
    private Button checkoutbtn;

    @FXML
    private Button resetbtn;

    @FXML
    private Button backButton;

    @FXML
    private Label ticketPriceLabel;
    private NavListener listener;


    private double adultPrice = 8.50;
    private double childPrice = 5.00;
    private double seniorPrice = 7.50;
    private double pmrPrice = 7.50; // Prix pour les personnes à mobilité réduite


    private PaymentMethod paymentMethod;

    @FXML
    private ComboBox<String> paymentMethodComboBox;


    /**
     * Initialise le contrôleur.
     */
    @FXML
    private void initialize() {
        backButton.setOnAction(event -> goBack());

        paymentMethodComboBox.getItems().addAll("Credit Card", "Bancontact");
        paymentMethodComboBox.setValue("Credit Card"); // Set a default value

        // Ajout d'écouteurs aux TextField pour mettre à jour le prix total
        adultTextField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
        childTextField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
        seniorTextField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
        pmrTextField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());

        // Mettre à jour le prix total au démarrage
        updateTotalPrice();

        checkoutbtn.setOnAction(event -> {
            // Récupérer le prix à nouveau au moment du clic
            Double prix = Double.valueOf( ticketPriceLabel.getText().substring(0, ticketPriceLabel.getText().length() - 1));
            openBancontactPage(prix);
        });

    }

    /**
     * Sets the payment method to be used for the transaction.
     *
     * @param paymentMethod The payment method to be set.
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }



    private void openBancontactPage(Double prix) {
        if (listener != null){
            listener.sendToTerminal(prix);
        }

    }


    public void setListener(NavListener listener) {
        this.listener = listener;
    }
    public interface NavListener {
        void sendToTerminal(Double prix);
    }

    @FXML
    private void handleResetButton(ActionEvent event) {
        adultTextField.clear();
        childTextField.clear();
        seniorTextField.clear();
        pmrTextField.clear();
    }



    @FXML
    private void decrementAdult() {
        int count = parseTextFieldValue(adultTextField.getText());
        if (count > 0) {
            adultTextField.setText(String.valueOf(count - 1));
            updateTotalPrice();
        }
    }


    @FXML
    private void incrementAdult() {
        int count = parseTextFieldValue(adultTextField.getText());
        adultTextField.setText(String.valueOf(count + 1));
        updateTotalPrice();
    }


    @FXML
    private void decrementChild() {
        int count = parseTextFieldValue(childTextField.getText());
        if (count > 0) {
            childTextField.setText(String.valueOf(count - 1));
            updateTotalPrice();
        }
    }


    @FXML
    private void incrementChild() {
        int count = parseTextFieldValue(childTextField.getText());
        childTextField.setText(String.valueOf(count + 1));
        updateTotalPrice();
    }


    @FXML
    private void decrementSenior() {
        int count = parseTextFieldValue(seniorTextField.getText());
        if (count > 0) {
            seniorTextField.setText(String.valueOf(count - 1));
            updateTotalPrice();
        }
    }


    @FXML
    private void incrementSenior() {
        int count = parseTextFieldValue(seniorTextField.getText());
        seniorTextField.setText(String.valueOf(count + 1));
        updateTotalPrice();
    }


    @FXML
    private void decrementPmr() {
        int count = parseTextFieldValue(pmrTextField.getText());
        if (count > 0) {
            pmrTextField.setText(String.valueOf(count - 1));
            updateTotalPrice();
        }
    }


    @FXML
    private void incrementPmr() {
        int count = parseTextFieldValue(pmrTextField.getText());
        pmrTextField.setText(String.valueOf(count + 1));
        updateTotalPrice();
    }

    public int getTotalTicketsChosen() {
        int adultCount = parseTextFieldValue(adultTextField.getText());
        int childCount = parseTextFieldValue(childTextField.getText());
        int seniorCount = parseTextFieldValue(seniorTextField.getText());
        int pmrCount = parseTextFieldValue(pmrTextField.getText());

        return adultCount + childCount + seniorCount + pmrCount;
    }


    private void updateTotalPrice() {
        int adultCount = parseTextFieldValue(adultTextField.getText());
        int childCount = parseTextFieldValue(childTextField.getText());
        int seniorCount = parseTextFieldValue(seniorTextField.getText());
        int pmrCount = parseTextFieldValue(pmrTextField.getText());

        double totalPrice = (adultCount * adultPrice) + (childCount * childPrice) + (seniorCount * seniorPrice) + (pmrCount * pmrPrice);
        ticketPriceLabel.setText(String.valueOf(totalPrice));
    }
    public void updateTotalPrice(Double prix) {
        ticketPriceLabel.setText(String.valueOf(prix));
    }



    private int parseTextFieldValue(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/cinephile/SchedulePage.fxml"));
            Parent root = loader.load();

            // Obtient la scène actuelle
            Scene scene = backButton.getScene();
            Stage stage = (Stage) scene.getWindow();

            // Remplace la scène actuelle par la nouvelle page chargée
            scene.setRoot(root);
            stage.show();
            stage.setWidth(1150);
            stage.setHeight(800);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
