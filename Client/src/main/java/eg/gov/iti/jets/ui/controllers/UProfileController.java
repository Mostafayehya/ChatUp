package eg.gov.iti.jets.ui.controllers;

import domains.*;
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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.CustomTextField;
import org.kordamp.ikonli.javafx.FontIcon;
import services.UpdateService;
import utilities.FilesUtilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    @FXML
    Circle userMode;
    @FXML
    Button choosePhoto;
    UpdateService updateService;
    Validation validation;
    UserModel userModel;
    ModelsFactory modelsFactory;
    FileDomain userImageFile = null;

    public UProfileController() {
        validation = new Validation();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModel = modelsFactory.getInstance().getCurrentUser();
        System.out.println(userModel.getMode().name());
        if (userModel.getMode().name().equals("AVAILABLE")) {
            userMode.setFill(Color.GREEN);
            availableBtn.setDisable(true);
        } else if (userModel.getMode().name().equals("BUSY")) {
            userMode.setFill(Color.RED);
            busyBtn.setDisable(true);
        } else {
            userMode.setFill(Color.WHITE);
            awayBtn.setDisable(true);

        }


        bind();
        userImage.setFill(new ImagePattern(userModel.getUserImage()));

        updateService = RMIManager.getUpdateService();
        emailTextField.focusedProperty().addListener(((observable, wasFocused, isNowFocused) -> {
            if (!isNowFocused && emailTextField.isEditable()) {
                if (emailTextField.getText().equals("") || !validation.validateEmail(emailTextField.getText())) {
                    emailTextField.setStyle("-fx-border-color: red;");
                } else {
                    emailTextField.setStyle("-fx-border-color: transparent;");
                }
            }

        }));

        choosePhoto.addEventHandler(ActionEvent.ACTION, (e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image files", "*.png"));
            File userPhoto = fileChooser.showOpenDialog(choosePhoto.getScene().getWindow());
            String extension = FilesUtilities.getFileExtension(userPhoto);
            userImageFile = new FileDomain();
            userImageFile.setFileBytes(FilesUtilities.convertFileToByteArray(userPhoto, extension));
            userImageFile.setFileExtension(extension);
            userImageFile.setFilename(phoneTextField.getText());
            try {
                userImage.setFill(new ImagePattern(new Image(new FileInputStream(userPhoto.getAbsoluteFile()))));
                updateService.EditUserPhoto(new User(userModel.getPhoneNumber(), userModel.getName(), userModel.getEmail(), userModel.getPassword(), userImageFile, userModel.getGender(), userModel.getCountry(), userModel.getDateOfBirth(), userModel.getBio(), userModel.getStatus(), userModel.getMode()));
            } catch (FileNotFoundException | RemoteException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

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
            } else if (validation.isempty(countryTextField)) {
                bioTextField.setStyle("-fx-border-color: red;");
            } else if (validation.isempty(emailTextField)) {
                emailTextField.setStyle("-fx-border-color: red;");
            } else if (validation.isempty(nameTextField)) {
                nameTextField.setStyle("-fx-border-color: red;");
            }

            try {
                updateService.EditUserData(new User(userModel.getPhoneNumber(), userModel.getName(), userModel.getEmail(), userModel.getPassword(), userModel.getPicture(), userModel.getGender(), userModel.getCountry(), userModel.getDateOfBirth(), userModel.getBio(), userModel.getStatus(), userModel.getMode()));

            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            saveBtn.setVisible(false);
            nameTextField.setEditable(false);
            countryTextField.setEditable(false);
            bioTextField.setEditable(false);
            emailTextField.setEditable(false);

        });

        availableBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            userModel.setMode(Mode.AVAILABLE);
            try {
                updateService.EditUserMode(new User(userModel.getPhoneNumber(), userModel.getName(), userModel.getEmail(), userModel.getPassword(), userModel.getPicture(), userModel.getGender(), userModel.getCountry(), userModel.getDateOfBirth(), userModel.getBio(), userModel.getStatus(), userModel.getMode()));
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            availableBtn.setDisable(true);
            busyBtn.setDisable(false);
            awayBtn.setDisable(false);
            userMode.setFill(Color.GREEN);
        });
        busyBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            userModel.setMode(Mode.BUSY);
            try {
                updateService.EditUserMode(new User(userModel.getPhoneNumber(), userModel.getName(), userModel.getEmail(), userModel.getPassword(), userModel.getPicture(), userModel.getGender(), userModel.getCountry(), userModel.getDateOfBirth(), userModel.getBio(), userModel.getStatus(), userModel.getMode()));
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            availableBtn.setDisable(false);
            busyBtn.setDisable(true);
            awayBtn.setDisable(false);
            userMode.setFill(Color.RED);
        });
        awayBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            userModel.setMode(Mode.AWAY);
            try {
                updateService.EditUserMode(new User(userModel.getPhoneNumber(), userModel.getName(), userModel.getEmail(), userModel.getPassword(), userModel.getPicture(), userModel.getGender(), userModel.getCountry(), userModel.getDateOfBirth(), userModel.getBio(), userModel.getStatus(), userModel.getMode()));
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            availableBtn.setDisable(false);
            busyBtn.setDisable(false);
            awayBtn.setDisable(true);
            userMode.setFill(Color.WHITE);
        });
        setting.addEventHandler(ActionEvent.ACTION, (e) -> {
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.ChangeUserPassword();
        });

    }

    private void bind() {
        phoneTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());
        nameTextField.textProperty().bindBidirectional(userModel.nameProperty());
        countryTextField.textProperty().bindBidirectional(userModel.countryProperty());
        emailTextField.textProperty().bindBidirectional(userModel.emailProperty());
        bioTextField.textProperty().bindBidirectional(userModel.bioProperty());
        userName.textProperty().bind(nameTextField.textProperty());

    }
}

