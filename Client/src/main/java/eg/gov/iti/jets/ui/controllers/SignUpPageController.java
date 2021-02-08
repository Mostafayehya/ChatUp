package eg.gov.iti.jets.ui.controllers;

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

import java.net.URL;
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
    public SignUpPageController(){
        validation = new Validation();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.addEventHandler(ActionEvent.ACTION, e->{
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.goToLoginPage();
        });
        phoneTextField.addEventFilter(KeyEvent.KEY_TYPED,(e)->{
            if(!new Validation().validatePhoneNumber(e.getCharacter()) || phoneTextField.getText().length()>11){
                e.consume();
            }
        });
        ObservableList<String> genders = FXCollections.observableArrayList("Male","Female");
        genderChoiceBox.setItems(genders);
        genderChoiceBox.setValue("Gender");
        signUpButton.addEventHandler(ActionEvent.ACTION,(e)->{
            System.out.println("clicked");
            if(validation.validateEmail(emailTextField.getText())){
                System.out.println("validated");
            }
        });
    }
}
