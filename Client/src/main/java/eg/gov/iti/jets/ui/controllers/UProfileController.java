package eg.gov.iti.jets.ui.controllers;

import domains.Gender;
import domains.Mode;
import domains.Status;
import domains.User;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.ui.models.UserModel;
import eg.gov.iti.jets.utilities.Validation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import org.controlsfx.control.textfield.CustomTextField;
import org.kordamp.ikonli.javafx.FontIcon;
import services.UpdateService;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class UProfileController implements Initializable {
    @FXML
    CustomTextField nameTextField;
    @FXML
    Label userName;
   @FXML
   CustomTextField phoneTextField;
    @FXML
    CustomTextField emailTextField;
    @FXML
    CustomTextField bioTextField;
    @FXML
    Button saveBtn;
    @FXML
    CustomTextField countryTextField;
    @FXML
    Button editBtn;
    @FXML
    Button availableBtn;
    @FXML
    Button busyBtn;
    @FXML
    Button awayBtn;
    UpdateService updateService;
    Validation validation;
    UserModel userModel;
    User user=new User("01116058917","hagar","hagar@gmail.com","1234",null, Gender.FEMALE,"egypt",null,"hii", Status.ONLINE,Mode.AVAILABLE);
    public UProfileController(){validation = new Validation();
    userModel=new UserModel();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bind();

//        userName.setText(user.getName());
//        nameTextField.setText(user.getName());
//        phoneTextField.setText(user.getPhoneNumber());
//        countryTextField.setText(user.getCountry());
//        emailTextField.setText(user.getEmail());
//        bioTextField.setText(user.getBio());
//        updateService= RMIManager.getUpdateService();
        emailTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (emailTextField.getText().equals("") ||! validation.validateEmail(emailTextField.getText())) {
                    emailTextField.setStyle("-fx-border-color: red;");
                } else {
                    emailTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
        if(user.getMode().equals("BUSY"))
        {
            busyBtn.pressedProperty();
        }
        else if(user.getMode().equals("AWAY"))
        {
            awayBtn.pressedProperty();
        }
        else {
            availableBtn.pressedProperty();
        }

        editBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
        nameTextField.setEditable(true);
        countryTextField.setEditable(true);
        bioTextField.setEditable(true);
        emailTextField.setEditable(true);
        saveBtn.setVisible(true);
                });

        saveBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            if (validation.isempty(countryTextField)) {
                countryTextField.setStyle("-fx-border-color: red;");
            }
            else if (validation.isempty(countryTextField)) {
                bioTextField.setStyle("-fx-border-color: red;");
            }
           else  if (validation.isempty(emailTextField)) {
                emailTextField.setStyle("-fx-border-color: red;");
           }
           else  if (validation.isempty(nameTextField)) {
                nameTextField.setStyle("-fx-border-color: red;");
            }
//            user.setCountry(countryTextField.getText());
//            user.setBio(bioTextField.getText());
//            user.setEmail(emailTextField.getText());
//            user.setName(nameTextField.getText());
//            userName.setText(nameTextField.getText());
            try {
                updateService.EditUserData(user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            saveBtn.setVisible(false);
        });

        availableBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            user.setMode(Mode.AVAILABLE);
            try {
                updateService.EditUserMode(user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
        busyBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            user.setMode(Mode.BUSY);
            try {
                updateService.EditUserMode(user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
        awayBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            user.setMode(Mode.AWAY);
            try {
                updateService.EditUserMode(user);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });

    }
    private void bind(){
        phoneTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());
        nameTextField.textProperty().bindBidirectional(userModel.nameProperty());
        countryTextField.textProperty().bindBidirectional(userModel.countryProperty());
        emailTextField.textProperty().bindBidirectional(userModel.emailProperty());
        bioTextField.textProperty().bindBidirectional(userModel.bioProperty());

    }
}

