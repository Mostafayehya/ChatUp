package eg.gov.iti.jets.ui.controllers;

import domains.Contact;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.ContactListCell;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactsListPageController implements Initializable {
    @FXML
    ListView<ContactModel> contactsListView;
    ObservableList<ContactModel> contactObservableList;
    @FXML
    Button addNewContact;

    public ContactsListPageController(){
        contactObservableList = ModelsFactory.getInstance().getCurrentUser().getContacts();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactObservableList = ModelsFactory.getInstance().getCurrentUser().getContacts();
        contactsListView.setItems(contactObservableList);
        contactsListView.setCellFactory(contactListView -> new ContactListCell());

        addNewContact.addEventHandler(ActionEvent.ACTION,(e)->{
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.getAddNewContactPopUp();
        });
    }
}
