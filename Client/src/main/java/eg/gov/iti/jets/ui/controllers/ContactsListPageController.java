package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.ui.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactsListPageController implements Initializable {
    @FXML
    ListView<Contact> contactsListView;
    ObservableList<Contact> contactObservableList;

    public ContactsListPageController(){
        contactObservableList = FXCollections.observableArrayList(
                new Contact("019922","hadeer","this is my bio","hadeer@gmail.com","/photos/user.jpg"),
                new Contact("8473992","yasmina","this is yasmina's bio","yasmina@gmail.com","/photos/user.jpg")
        );

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
