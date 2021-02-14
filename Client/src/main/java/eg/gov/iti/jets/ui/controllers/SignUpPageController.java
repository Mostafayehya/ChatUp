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
    ChoiceBox<Gender> genderChoiceBox;
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
        phoneTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (phoneTextField.getText().equals("") || phoneTextField.getText().length() < 11) {
                    phoneTextField.setStyle("-fx-border-color: red;");
                } else {
                    phoneTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        emailTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (emailTextField.getText().equals("") || !validation.validateEmail(emailTextField.getText())) {
                    emailTextField.setStyle("-fx-border-color: red;");
                } else {
                    emailTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        nameTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (nameTextField.getText().equals("")) {
                    nameTextField.setStyle("-fx-border-color: red;");
                } else {
                    nameTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        passwordTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (passwordTextField.getText().equals("")) {
                    passwordTextField.setStyle("-fx-border-color: red;");
                } else {
                    passwordTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        confirmPasswordField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (confirmPasswordField.getText().equals("") || !confirmPasswordField.getText().equals(passwordTextField.getText())) {
                    confirmPasswordField.setStyle("-fx-border-color: red;");
                } else {
                    confirmPasswordField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.FEMALE,Gender.MALE);
        genderChoiceBox.setItems(genders);
        genderChoiceBox.setValue(Gender.FEMALE);
        signUpButton.addEventHandler(ActionEvent.ACTION, (e) -> {
            if (validation.isempty(passwordTextField)) {
                passwordTextField.setStyle("-fx-border-color: red;");
            }
            if (!validation.matchPasswords(passwordTextField.getText(), confirmPasswordField.getText())) {
                passwordTextField.setStyle("-fx-border-color: red;");
                confirmPasswordField.setStyle("-fx-border-color: red;");
            }
            User user = null;
            user = new User(phoneTextField.getText(), nameTextField.getText(), emailTextField.getText(), passwordTextField.getText(), null, genderChoiceBox.getValue(), countryTextField.getText(), birthDatePicker.getValue(), bioTextField.getText(), Status.ONLINE, Mode.AVAILABLE);
            try {
                authenticationService.signUp(user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
    }
}
