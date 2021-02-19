package eg.gov.iti.jets.ui.controllers;

import domains.Gender;
import domains.Mode;
import domains.Status;
import domains.User;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.ui.models.UserModel;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import eg.gov.iti.jets.utilities.Validation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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
    @FXML
    Button setting;
    @FXML
    Circle userImage;
    UpdateService updateService;
    Validation validation;
    UserModel userModel;
    ModelsFactory modelsFactory;
   // User user=new User("01116058917","hagar","hagar@gmail.com","1234",null, Gender.FEMALE,"egypt",null,"hii", Status.ONLINE,Mode.AVAILABLE);
    public UProfileController(){validation = new Validation();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModel=modelsFactory.getInstance().getCurrentUser();
        System.out.println("user "+userModel.getName());
        bind();
        userImage.setFill(new ImagePattern(userModel.getUserImage()));
        updateService= RMIManager.getUpdateService();
        emailTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (emailTextField.getText().equals("") ||! validation.validateEmail(emailTextField.getText())) {
                    emailTextField.setStyle("-fx-border-color: red;");
                } else {
                    emailTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        }));
//        if(userModel.getMode().equals("BUSY"))
//        {
//            busyBtn.pressedProperty();
//        }
//        else if(userModel.getMode().equals("AWAY"))
//        {
//            awayBtn.pressedProperty();
//        }
//        else {
//            availableBtn.pressedProperty();
//        }

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

                String s=userModel.getBio()+" "+userModel.getEmail()+" "+userModel.getPhoneNumber()+userModel.getCountry();
                System.out.println(s);
                System.out.println(updateService);
               updateService.EditUserData(new User(userModel.getPhoneNumber(),userModel.getName(),userModel.getEmail(),userModel.getPassword(),userModel.getPicture(),userModel.getGender(),userModel.getCountry(),userModel.getDateOfBirth(),userModel.getBio(),userModel.getStatus(),userModel.getMode()));

            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            saveBtn.setVisible(false);
        });

        availableBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            userModel.setMode(Mode.AVAILABLE);
            try {
                updateService.EditUserMode(new User(userModel.getPhoneNumber(),userModel.getName(),userModel.getEmail(),userModel.getPassword(),userModel.getPicture(),userModel.getGender(),userModel.getCountry(),userModel.getDateOfBirth(),userModel.getBio(),userModel.getStatus(),userModel.getMode()));
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
        busyBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            userModel.setMode(Mode.BUSY);
            try {
                updateService.EditUserMode(new User(userModel.getPhoneNumber(),userModel.getName(),userModel.getEmail(),userModel.getPassword(),userModel.getPicture(),userModel.getGender(),userModel.getCountry(),userModel.getDateOfBirth(),userModel.getBio(),userModel.getStatus(),userModel.getMode()));
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
        awayBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            userModel.setMode(Mode.AWAY);
            try {
                updateService.EditUserMode(new User(userModel.getPhoneNumber(),userModel.getName(),userModel.getEmail(),userModel.getPassword(),userModel.getPicture(),userModel.getGender(),userModel.getCountry(),userModel.getDateOfBirth(),userModel.getBio(),userModel.getStatus(),userModel.getMode()));
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
        setting.addEventHandler(ActionEvent.ACTION,(e)->{
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.ChangeUserPassword();
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

