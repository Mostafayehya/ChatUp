package eg.gov.iti.jets.ui.controllers;

import domains.Mode;
import domains.Status;
import eg.gov.iti.jets.ui.models.ContactModel;
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
    ListView<ContactModel> contactsListView;
    ObservableList<ContactModel> contactObservableList;
    @FXML
    Button addNewContact;

    public ContactsListPageController(){
        contactObservableList = FXCollections.observableArrayList(
                new ContactModel("019922","hadeer","this is my bio","hadeer@gmail.com","/photos/user.jpg", Status.OFFLINE, Mode.AVAILABLE),
                new ContactModel("8473992","yasmina","this is yasmina's bio","yasmina@gmail.com","/photos/user.jpg",Status.OFFLINE,Mode.AVAILABLE)
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