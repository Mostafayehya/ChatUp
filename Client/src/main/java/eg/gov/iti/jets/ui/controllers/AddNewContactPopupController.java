package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.utilities.StageCoordinator;
import eg.gov.iti.jets.utilities.Validation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.CustomTextField;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewContactPopupController implements Initializable {
    @FXML
    Button addNewContactButton;
    @FXML
    Button cancelButton;
    @FXML
    Button addExtraPhone;
    @FXML
    CustomTextField contactNameTextField;
    @FXML
    CustomTextField contactNumberTextField;
    @FXML
    CustomTextField extraPhoneTextField;
    @FXML
    VBox extraPhoneVBox;
    Validation validation;
    List<StringProperty> extraPhones;
    public AddNewContactPopupController() {
        validation = new Validation();
        extraPhones = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactNameTextField.focusedProperty().addListener((observable,wasFocused,isFocused)->{
            if(!isFocused){
                if(validation.isempty(contactNameTextField)){
                    contactNameTextField.setStyle("-fx-border-color: red");
                }
                else {
                    contactNameTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        });
        contactNumberTextField.addEventFilter(KeyEvent.KEY_TYPED, (e) -> {
            if (!new Validation().validatePhoneNumber(e.getCharacter()) || contactNumberTextField.getText().length() >= 11) {
                e.consume();
            }
        });
        contactNumberTextField.focusedProperty().addListener((observable,wasFocused,isFocused)->{
            if(!isFocused){
                if(validation.isempty(contactNumberTextField)){
                    contactNumberTextField.setStyle("-fx-border-color: red");
                }
                else {
                    contactNumberTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        });
        addExtraPhone.addEventHandler(ActionEvent.ACTION,(e)->{
            CustomTextField extraPhone = new CustomTextField();
            FontIcon phoneIcon = new FontIcon("mdi2p-phone");
            phoneIcon.setIconColor(Color.GRAY);
            extraPhone.setLeft(phoneIcon);
            extraPhone.setPromptText("Extra phone number");
            extraPhone.setStyle(extraPhoneTextField.getStyle());
            StringProperty newExtraPhone = new SimpleStringProperty();
            extraPhone.textProperty().bindBidirectional(newExtraPhone);
            extraPhones.add(newExtraPhone);
            extraPhoneVBox.getChildren().add(extraPhone);
        });
        cancelButton.addEventHandler(ActionEvent.ACTION,(e)->{
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.hideNewContactPopup();
        });
        addNewContactButton.addEventHandler(ActionEvent.ACTION,(e)->{

        });
    }
}
