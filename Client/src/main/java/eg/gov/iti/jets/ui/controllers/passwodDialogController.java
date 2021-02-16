package eg.gov.iti.jets.ui.controllers;

import domains.User;
import eg.gov.iti.jets.ui.models.UserModel;
import eg.gov.iti.jets.utilities.StageCoordinator;
import eg.gov.iti.jets.utilities.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;
import services.UpdateService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class passwodDialogController {
    @FXML
    CustomPasswordField CurrentpassTextField;
    @FXML
    CustomPasswordField newPassTextField;
    @FXML
    CustomPasswordField newConfirmPassField;
    @FXML
    Button changeBtn;
    @FXML
    Button cancelBtn;
    Validation validation;
    UserModel userModel;
    UpdateService updateService;
    public passwodDialogController(){
        userModel=new UserModel();
        validation = new Validation();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CurrentpassTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (CurrentpassTextField.getText().equals("")) {
                    CurrentpassTextField.setStyle("-fx-border-color: red;");
                    CurrentpassTextField.requestFocus();
                } else if (!CurrentpassTextField.getText().equals(userModel.getPassword())) {
                    CurrentpassTextField.requestFocus();
                } else {
                    CurrentpassTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        newPassTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (newPassTextField.getText().equals("")) {
                    newPassTextField.setStyle("-fx-border-color: red;");
                    newPassTextField.requestFocus();
                } else {
                    newPassTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        newConfirmPassField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (newConfirmPassField.getText().equals("")) {
                    newConfirmPassField.setStyle("-fx-border-color: red;");
                    newConfirmPassField.requestFocus();
                } else if (!newConfirmPassField.getText().equals(newPassTextField.getText())) {
                    newConfirmPassField.requestFocus();
                } else {
                    newConfirmPassField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        cancelBtn.addEventHandler(ActionEvent.ACTION,(e)->{
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.hidePasswordPopup();
        });
        changeBtn.addEventHandler(ActionEvent.ACTION,(e)->{
            try {
                updateService.EditUserPass(new User(userModel.getPhoneNumber(),userModel.getName(),userModel.getEmail(),userModel.getPassword(),userModel.getPicture(),userModel.getGender(),userModel.getCountry(),userModel.getDateOfBirth(),userModel.getBio(),userModel.getStatus(),userModel.getMode()));
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });

    }






}
