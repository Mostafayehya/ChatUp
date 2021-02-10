package eg.gov.iti.jets.ui.controllers;

import domains.Gender;
import domains.Mode;
import domains.Status;
import domains.User;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.utilities.StageCoordinator;
import eg.gov.iti.jets.utilities.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;
import services.AuthenticationService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class SignUpPageController implements Initializable {
    @FXML
    Button loginButton;
    @FXML
    Button signUpButton;
    @FXML
    CustomTextField phoneTextField;
    @FXML
    CustomTextField nameTextField;
    @FXML
    CustomTextField emailTextField;
    @FXML
    CustomPasswordField passwordTextField;
    @FXML
    CustomPasswordField confirmPasswordField;
    @FXML
    CustomTextField bioTextField;
    @FXML
    CustomTextField countryTextField;
    @FXML
    ChoiceBox<String> genderChoiceBox;
    @FXML
    DatePicker birthDatePicker;
    Validation validation;
    AuthenticationService authenticationService;

    public SignUpPageController() {
        validation = new Validation();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authenticationService = RMIManager.getAuthenticationService();
        loginButton.addEventHandler(ActionEvent.ACTION, e -> {
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.goToLoginPage();
        });
        phoneTextField.addEventFilter(KeyEvent.KEY_TYPED, (e) -> {
            if (!new Validation().validatePhoneNumber(e.getCharacter()) || phoneTextField.getText().length() >= 11) {
                e.consume();
            }
        });
        ObservableList<String> genders = FXCollections.observableArrayList("MALE", "FEMALE");
        genderChoiceBox.setItems(genders);
        genderChoiceBox.setValue("Gender");
        signUpButton.addEventHandler(ActionEvent.ACTION, (e) -> {
            if (!validation.validateEmail(emailTextField.getText())) {
                emailTextField.setStyle("-fx-border-color: red;");
            }
            if (validation.isempty(phoneTextField)) {
                phoneTextField.setStyle("-fx-border-color: red;");
            }
            if (validation.isempty(emailTextField)) {
                emailTextField.setStyle("-fx-border-color: red;");
            }
            if (validation.isempty(nameTextField)) {
                nameTextField.setStyle("-fx-border-color: red;");
            }
            if (validation.isempty(passwordTextField)) {
                passwordTextField.setStyle("-fx-border-color: red;");
            }
            if (!validation.matchPasswords(passwordTextField.getText(), confirmPasswordField.getText())) {
                passwordTextField.setStyle("-fx-border-color: red;");
                confirmPasswordField.setStyle("-fx-border-color: red;");
            }

            User user = null;
            user = new User(phoneTextField.getText(), nameTextField.getText(), emailTextField.getText(), passwordTextField.getText(),null, Gender.FEMALE,countryTextField.getText(),birthDatePicker.getValue(),bioTextField.getText(), Status.OFFLINE, Mode.AVAILABLE);
            try {
                authenticationService.signUp(user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
    }
}
