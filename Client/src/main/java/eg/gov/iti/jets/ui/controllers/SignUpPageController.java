package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpPageController implements Initializable {
    @FXML
    Button LoginButton;
    @FXML
    Button SignUpButton;
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
    public SignUpPageController(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginButton.addEventHandler(ActionEvent.ACTION,e->{
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.goToLoginPage();
        });
        ObservableList<String> genders = FXCollections.observableArrayList("Male","Female");
        genderChoiceBox.setItems(genders);
        genderChoiceBox.setValue("Gender");
    }
}
