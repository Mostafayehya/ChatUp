package eg.gov.iti.jets.ui.controllers;

import domains.Contact;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import eg.gov.iti.jets.utilities.Validation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.CustomTextField;
import org.kordamp.ikonli.javafx.FontIcon;
import services.HandleContactsService;
import java.net.URL;
import java.rmi.RemoteException;
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
    ContactModel contactModel;
    HandleContactsService handleContactsService;
    public AddNewContactPopupController() {
        validation = new Validation();
        extraPhones = new ArrayList<>();
        contactModel = new ContactModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validation = new Validation();
        bind();
        contactNameTextField.setText("");
        contactNumberTextField.setText("");
        extraPhoneTextField.setText("");
        handleContactsService = RMIManager.getHandleContactsService();
        contactNameTextField.focusedProperty().addListener((observable,wasFocused,isFocused)->{
            if(!isFocused){
                System.out.println(contactModel);
                System.out.println(contactModel.getName());
                if(contactModel.getName().equals("")){
                    contactNameTextField.setStyle("-fx-border-color: red");
                }
                else {
                    contactNameTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        });
        contactNumberTextField.addEventFilter(KeyEvent.KEY_TYPED, (e) -> {
            if (!validation.validatePhoneNumber(e.getCharacter()) || contactNumberTextField.getText().length() >= 11) {
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
            try {
                ModelsFactory modelsFactory = ModelsFactory.getInstance();
                String phone = modelsFactory.getCurrentUser().getPhoneNumber();
                int result = handleContactsService.addNewContact(new Contact(phone,contactModel.getContactPhoneNumber(),contactModel.getName(),contactModel.getBio(),contactModel.getEmail(),contactModel.getImage(),contactModel.getStatus(),contactModel.getMode()));
                if(result == -2){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("User not Found");
                    alert.setHeaderText("User Not Found");
                    alert.setContentText("The contact you added has no user account");
                    StageCoordinator.getInstance().hideNewContactPopup();
                    alert.showAndWait();
                }
                if(result == -3){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Contact already exists");
                    alert.setHeaderText("Contact already exists");
                    alert.setContentText("The contact you added already exists in your contacts list");
                    StageCoordinator.getInstance().hideNewContactPopup();
                    alert.showAndWait();
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void bind(){
        contactNumberTextField.textProperty().bindBidirectional(contactModel.contactPhoneNumberProperty());
        contactNameTextField.textProperty().bindBidirectional(contactModel.nameProperty());
        StringProperty newExtraPhone = new SimpleStringProperty();
        extraPhoneTextField.textProperty().bindBidirectional(newExtraPhone);
        extraPhones.add(newExtraPhone);
    }
}
