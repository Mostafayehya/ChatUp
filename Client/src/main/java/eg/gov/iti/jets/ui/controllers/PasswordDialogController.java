package eg.gov.iti.jets.ui.controllers;

import domains.User;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.UserModel;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import eg.gov.iti.jets.utilities.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.controlsfx.control.textfield.CustomPasswordField;

import services.UpdateService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PasswordDialogController implements Initializable {
    @FXML
    CustomPasswordField CurrentPassTextField;
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
    public PasswordDialogController(){
        userModel= ModelsFactory.getInstance().getCurrentUser();
        validation = new Validation();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("password change controller");
        updateService= RMIManager.getUpdateService();
        CurrentPassTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (CurrentPassTextField.getText().equals("")) {
                    CurrentPassTextField.setStyle("-fx-border-color: red;");
                    CurrentPassTextField.requestFocus();
                } else if (!CurrentPassTextField.getText().equals(userModel.getPassword())) {
                    CurrentPassTextField.setStyle("-fx-border-color: red;");
                    CurrentPassTextField.requestFocus();
                } else {
                    CurrentPassTextField.setStyle("-fx-border-color: transparent;");
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
                    newConfirmPassField.setStyle("-fx-border-color: red;");
                } else {
                    newConfirmPassField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        cancelBtn.addEventHandler(ActionEvent.ACTION,(e)->{
            System.out.println("cancel");
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.hidePasswordPopup();

        });
        changeBtn.addEventHandler(ActionEvent.ACTION,(e)->{
            if (!newConfirmPassField.getText().equals(newPassTextField.getText())) {
                newConfirmPassField.setStyle("-fx-border-color: red;");
                return;
            }
            try {
                System.out.println("change");
                System.out.println(updateService.EditUserPass(new User(userModel.getPhoneNumber(), userModel.getName(), userModel.getEmail(), newPassTextField.getText(), userModel.getPicture(), userModel.getGender(), userModel.getCountry(), userModel.getDateOfBirth(), userModel.getBio(), userModel.getStatus(), userModel.getMode())));
                StageCoordinator.getInstance().hidePasswordPopup();

            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });

    }






}
