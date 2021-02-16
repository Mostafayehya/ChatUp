package eg.gov.iti.jets.ui.controllers;

import domains.*;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.utilities.StageCoordinator;
import eg.gov.iti.jets.utilities.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;
import services.AuthenticationService;
import utilities.FilesUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    @FXML
    Button choosePhoto;
    @FXML
    Circle imageCircle;
    Validation validation;
    AuthenticationService authenticationService;
    FileDomain userImageFile = null;

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
                    phoneTextField.requestFocus();
                } else {
                    phoneTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        emailTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (emailTextField.getText().equals("") || !validation.validateEmail(emailTextField.getText())) {
                    emailTextField.setStyle("-fx-border-color: red;");
                    emailTextField.requestFocus();
                } else {
                    emailTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        nameTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (nameTextField.getText().equals("")) {
                    nameTextField.setStyle("-fx-border-color: red;");
                    nameTextField.requestFocus();
                } else {
                    nameTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        passwordTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (passwordTextField.getText().equals("")) {
                    passwordTextField.setStyle("-fx-border-color: red;");
                    passwordTextField.requestFocus();
                } else {
                    passwordTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        confirmPasswordField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (confirmPasswordField.getText().equals("")) {
                    confirmPasswordField.setStyle("-fx-border-color: red;");
                    confirmPasswordField.requestFocus();
                } else if (!confirmPasswordField.getText().equals(passwordTextField.getText())) {
                    confirmPasswordField.requestFocus();
                } else {
                    confirmPasswordField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.FEMALE, Gender.MALE);
        genderChoiceBox.setItems(genders);
        genderChoiceBox.setValue(Gender.FEMALE);
        signUpButton.addEventHandler(ActionEvent.ACTION, (e) -> {
            if (validateFields()) {
                User user;
                userImageFile.setFilename(phoneTextField.getText());
                user = new User(phoneTextField.getText(), nameTextField.getText(), emailTextField.getText(), passwordTextField.getText(), userImageFile, genderChoiceBox.getValue(), countryTextField.getText(), birthDatePicker.getValue(), bioTextField.getText(), Status.ONLINE, Mode.AVAILABLE);
                try {
                    int result = authenticationService.signUp(user);
                    if (result == -2) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Duplicate user error");
                        alert.setHeaderText("User already exists");
                        alert.setContentText("please login or use another phone number");
                        alert.showAndWait();
                    } else {
                        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
                        stageCoordinator.goToLoginPage();
                    }
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
        choosePhoto.addEventHandler(ActionEvent.ACTION,(e)->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image files","*.png"));
            File userPhoto = fileChooser.showOpenDialog(choosePhoto.getScene().getWindow());
            String extension = FilesUtilities.getFileExtension(userPhoto);
            userImageFile = new FileDomain();
            userImageFile.setFileBytes(FilesUtilities.convertImageFileToByteArray(userPhoto,extension));
            userImageFile.setFileExtension(extension);
            try {
                imageCircle.setFill(new ImagePattern(new Image(new FileInputStream(userPhoto.getAbsoluteFile()))));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

    }

    private boolean validateFields() {
        boolean validated = true;
        if (phoneTextField.getText().equals("") || phoneTextField.getText().length() < 11) {
            phoneTextField.setStyle("-fx-border-color: red;");
            validated = false;
        } else {
            phoneTextField.setStyle("-fx-border-color: transparent;");
        }
        if (emailTextField.getText().equals("") || !validation.validateEmail(emailTextField.getText())) {
            emailTextField.setStyle("-fx-border-color: red;");
            validated = false;
        } else {
            emailTextField.setStyle("-fx-border-color: transparent;");
        }
        if (nameTextField.getText().equals("")) {
            nameTextField.setStyle("-fx-border-color: red;");
            validated = false;
        } else {
            nameTextField.setStyle("-fx-border-color: transparent;");
        }
        if (passwordTextField.getText().equals("")) {
            passwordTextField.setStyle("-fx-border-color: red;");
            validated = false;
        } else {
            passwordTextField.setStyle("-fx-border-color: transparent;");
        }
        if (confirmPasswordField.getText().equals("")) {
            confirmPasswordField.setStyle("-fx-border-color: red;");
            validated = false;
        } else if (!confirmPasswordField.getText().equals(passwordTextField.getText())) {
            validated = false;
        } else {
            confirmPasswordField.setStyle("-fx-border-color: transparent;");
        }
        return validated;
    }
}
