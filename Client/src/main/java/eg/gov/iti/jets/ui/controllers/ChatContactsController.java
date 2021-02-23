package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.ChatContactListCell;
import eg.gov.iti.jets.utilities.ModelsFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatContactsController implements Initializable {
    @FXML
    ListView<ContactModel> contactsListView;
    ObservableList<ContactModel> contactObservableList;
    ModelsFactory modelsFactory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelsFactory = ModelsFactory.getInstance();
        contactObservableList = modelsFactory.getCurrentUser().getContacts();

        contactsListView.setItems(contactObservableList);
        contactsListView.setCellFactory(contactListView -> new ChatContactListCell());

        // Handling clicks over listView
        contactsListView.getSelectionModel().selectedItemProperty().addListener((observableValue, contactModel, t1) -> {
                        modelsFactory.setSelectedOnlineContactModel(t1);
                        modelsFactory.updateMessagesObservableList(t1.getContactPhoneNumber());

            System.out.println(t1.nameProperty().get()+ " was clicked");

                }
        );

    }
}
