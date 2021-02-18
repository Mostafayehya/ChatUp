package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.ChatContactListCell;
import eg.gov.iti.jets.utilities.ContactListCell;
import eg.gov.iti.jets.utilities.ModelsFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatContactsController  implements Initializable {
    @FXML
    ListView<ContactModel> contactsListView;
    ObservableList<ContactModel> contactObservableList;

    public ChatContactsController(){

        contactObservableList = ModelsFactory.getInstance().getCurrentUser().getContacts();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactObservableList = ModelsFactory.getInstance().getCurrentUser().getContacts();
        contactsListView.setItems(contactObservableList);
        contactsListView.setCellFactory(contactListView -> new ChatContactListCell());

        contactsListView.onMouseClickedProperty();
    }
}
