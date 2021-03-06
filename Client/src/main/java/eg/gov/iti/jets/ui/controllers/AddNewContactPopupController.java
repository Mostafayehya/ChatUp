package eg.gov.iti.jets.ui.controllers;

import domains.Contact;
import domains.Invitation;
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
        addNewContactButton.setDefaultButton(true);
        contactNumberTextField.setText("");
        extraPhoneTextField.setText("");
        handleContactsService = RMIManager.getHandleContactsService();
        contactNumberTextField.addEventFilter(KeyEvent.KEY_TYPED, (e) -> {
            if (!validation.validatePhoneNumber(e.getCharacter()) || contactNumberTextField.getText().length() >= 11) {
                e.consume();
            }
        });
        extraPhoneTextField.addEventFilter(KeyEvent.KEY_TYPED, (e) -> {
            if (!validation.validatePhoneNumber(e.getCharacter()) || extraPhoneTextField.getText().length() >= 11) {
                e.consume();
            }
        });
        contactNumberTextField.focusedProperty().addListener((observable, wasFocused, isFocused) -> {
            if (!isFocused) {
                if (validation.isempty(contactNumberTextField)) {
                    contactNumberTextField.setStyle("-fx-border-color: red");
                } else {
                    contactNumberTextField.setStyle("-fx-border-color: transparent;");
                }
            }
        });
        addExtraPhone.addEventHandler(ActionEvent.ACTION, (e) -> {
            CustomTextField extraPhone = new CustomTextField();
            FontIcon phoneIcon = new FontIcon("mdi2p-phone");
            phoneIcon.setIconColor(Color.GRAY);
            extraPhone.setText("");
            extraPhone.setLeft(phoneIcon);
            extraPhone.setPromptText("Extra phone number");
            extraPhone.setStyle(extraPhoneTextField.getStyle());
            StringProperty newExtraPhone = new SimpleStringProperty("");
            extraPhone.textProperty().bindBidirectional(newExtraPhone);
            extraPhone.addEventFilter(KeyEvent.KEY_TYPED, (keyEvent) -> {
                if (!validation.validatePhoneNumber(keyEvent.getCharacter()) || extraPhone.getText().length() >= 11) {
                    keyEvent.consume();
                }
            });
            extraPhones.add(newExtraPhone);
            extraPhoneVBox.getChildren().add(extraPhone);
        });
        cancelButton.addEventHandler(ActionEvent.ACTION, (e) -> {
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.hideNewContactPopup();
        });
        addNewContactButton.addEventHandler(ActionEvent.ACTION, (e) -> {
            try {
                ModelsFactory modelsFactory = ModelsFactory.getInstance();
                String senderPhone = modelsFactory.getCurrentUser().getPhoneNumber();
                Contact contact = new Contact(senderPhone, contactModel.getContactPhoneNumber());
                Invitation invitation=new Invitation(senderPhone,contactModel.getContactPhoneNumber());
                for (int i = 0; i < extraPhones.size(); i++) {
                    contact.getExtraNumbers().add(extraPhones.get(i).getValue());
                }
                int result = handleContactsService.addNewContact(contact,invitation);
                if (result == -2) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("User not Found");
                    alert.setHeaderText("User Not Found");
                    alert.setContentText("The contact you added has no user account");
                    StageCoordinator.getInstance().hideNewContactPopup();
                    alert.showAndWait();
                }
                if (result == -3) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Contact already exists");
                    alert.setHeaderText("Contact already exists");
                    alert.setContentText("The contact you added already exists in your contacts list");
                    StageCoordinator.getInstance().hideNewContactPopup();
                    alert.showAndWait();
                }
                //else
//                    ModelsFactory.getInstance().retrieveContacts();
                StageCoordinator.getInstance().hideNewContactPopup();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void bind() {
        contactNumberTextField.textProperty().bindBidirectional(contactModel.contactPhoneNumberProperty());
        StringProperty newExtraPhone = new SimpleStringProperty();
        extraPhoneTextField.textProperty().bindBidirectional(newExtraPhone);
        extraPhones.add(newExtraPhone);
    }
}
