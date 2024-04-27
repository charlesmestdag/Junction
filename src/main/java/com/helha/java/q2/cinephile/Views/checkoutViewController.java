package com.helha.java.q2.cinephile.Views;

import com.helha.java.q2.cinephile.patternFactory.BancontactPaymentMethod;
import com.helha.java.q2.cinephile.patternFactory.CreditCardPaymentMethod;
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
public class checkoutViewController {

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
        checkoutbtn.setOnAction(event -> openBancontactPage());
        backButton.setOnAction(event -> goBack());

        paymentMethodComboBox.getItems().addAll("Credit Card", "Bancontact");
        paymentMethodComboBox.setValue("Credit Card"); // Set a default value

        // Ajout d'écouteurs aux TextField pour mettre à jour le prix total
        adultTextField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
        childTextField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
        seniorTextField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
        pmrTextField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
    }

    /**
     * Sets the payment method to be used for the transaction.
     *
     * @param paymentMethod The payment method to be set.
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    /**
     * Opens the Bancontact payment page based on the selected payment method.
     * Retrieves the total amount displayed, sets the payment strategy based on the user's choice,
     * and initiates the payment transaction. If the amount is empty, displays an error message.
     */
    private void openBancontactPage() {
        try {
            String montant = ticketPriceLabel.getText().replace(",", ".").replace(" €", ""); // Récupére le montant total affiché
            if (!montant.isEmpty()) {
                // Set the payment strategy based on the user's choice
                String paymentMethodChoice = paymentMethodComboBox.getValue();
                if (paymentMethodChoice.equals("Credit Card")) {
                    paymentMethod = new CreditCardPaymentMethod();
                } else if (paymentMethodChoice.equals("Bancontact")) {
                    paymentMethod = new BancontactPaymentMethod();
                }

                paymentMethod.pay(Double.parseDouble(montant));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/cinephile/bancontact.fxml"));
                Parent root = loader.load();
                BancontactViewController bancontactViewController = loader.getController();
                bancontactViewController.setMontant(montant); // Passe le montant à la page Bancontact
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setResizable(false);
            } else {
                // handle the case where montant is empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Payment Error");
                alert.setContentText("Please enter a valid amount!");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Réinitialise les TextField de sélection.
     *
     * @param event L'événement de bouton.
     */
    @FXML
    private void handleResetButton(ActionEvent event) {
        adultTextField.clear();
        childTextField.clear();
        seniorTextField.clear();
        pmrTextField.clear();
    }


    /**
     * Décrémente le nombre d'adultes.
     */
    @FXML
    private void decrementAdult() {
        int count = parseTextFieldValue(adultTextField.getText());
        if (count > 0) {
            adultTextField.setText(String.valueOf(count - 1));
            updateTotalPrice();
        }
    }

    /**
     * Incrémente le nombre d'adultes.
     */
    @FXML
    private void incrementAdult() {
        int count = parseTextFieldValue(adultTextField.getText());
        adultTextField.setText(String.valueOf(count + 1));
        updateTotalPrice();
    }

    /**
     * Décrémente le nombre d'enfants.
     */
    @FXML
    private void decrementChild() {
        int count = parseTextFieldValue(childTextField.getText());
        if (count > 0) {
            childTextField.setText(String.valueOf(count - 1));
            updateTotalPrice();
        }
    }

    /**
     * Incrémente le nombre d'enfants.
     */
    @FXML
    private void incrementChild() {
        int count = parseTextFieldValue(childTextField.getText());
        childTextField.setText(String.valueOf(count + 1));
        updateTotalPrice();
    }

    /**
     * Décrémente le nombre de seniors.
     */
    @FXML
    private void decrementSenior() {
        int count = parseTextFieldValue(seniorTextField.getText());
        if (count > 0) {
            seniorTextField.setText(String.valueOf(count - 1));
            updateTotalPrice();
        }
    }

    /**
     * Incrémente le nombre de seniors.
     */
    @FXML
    private void incrementSenior() {
        int count = parseTextFieldValue(seniorTextField.getText());
        seniorTextField.setText(String.valueOf(count + 1));
        updateTotalPrice();
    }

    /**
     * Décrémente le nombre de PMR.
     */
    @FXML
    private void decrementPmr() {
        int count = parseTextFieldValue(pmrTextField.getText());
        if (count > 0) {
            pmrTextField.setText(String.valueOf(count - 1));
            updateTotalPrice();
        }
    }

    /**
     * Incrémente le nombre de PMR.
     */
    @FXML
    private void incrementPmr() {
        int count = parseTextFieldValue(pmrTextField.getText());
        pmrTextField.setText(String.valueOf(count + 1));
        updateTotalPrice();
    }



    /**
     * Met à jour le prix total en fonction des valeurs dans les TextField.
     */
    private void updateTotalPrice() {
        int adultCount = parseTextFieldValue(adultTextField.getText());
        int childCount = parseTextFieldValue(childTextField.getText());
        int seniorCount = parseTextFieldValue(seniorTextField.getText());
        int pmrCount = parseTextFieldValue(pmrTextField.getText());

        double totalPrice = (adultCount * adultPrice) + (childCount * childPrice) + (seniorCount * seniorPrice) + (pmrCount * pmrPrice);
        ticketPriceLabel.setText(String.format("%.2f €", totalPrice));
    }


    /**
     * Parse la valeur d'un TextField en tant qu'entier, en cas d'erreur renvoie 0.
     * @param text La valeur du TextField à parser.
     * @return L'entier parsé ou 0 en cas d'erreur.
     */
    private int parseTextFieldValue(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Gère l'action de retour en arrière vers la vue précédente.
     */
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
