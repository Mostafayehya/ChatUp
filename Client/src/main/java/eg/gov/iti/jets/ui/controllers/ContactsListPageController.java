package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.ui.models.Contact;
import eg.gov.iti.jets.utilities.ContactListCell;
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
    ListView<Contact> contactsListView;
    ObservableList<Contact> contactObservableList;
    @FXML
    Button addNewContact;

    public ContactsListPageController(){
        contactObservableList = FXCollections.observableArrayList(
                new Contact("019922","hadeer","this is my bio","hadeer@gmail.com","/photos/user.jpg"),
                new Contact("8473992","yasmina","this is yasmina's bio","yasmina@gmail.com","/photos/user.jpg")
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactsListView.setItems(contactObservableList);
        contactsListView.setCellFactory(contactListView -> new ContactListCell());

        addNewContact.addEventHandler(ActionEvent.ACTION,(e)->{
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.getAddNewContactPopUp();
        });
    }
}
