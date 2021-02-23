package eg.gov.iti.jets.ui.controllers;

import domains.Contact;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.ContactListCell;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
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
    ModelsFactory modelsFactory = ModelsFactory.getInstance();
    @FXML
    Button addNewContact;
    ContactModel contactModel;
    StageCoordinator stageCoordinator;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stageCoordinator= StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        contactObservableList = modelsFactory.getCurrentUser().getContacts();
        contactObservableList = ModelsFactory.getInstance().getCurrentUser().getContacts();
        contactsListView.setItems(contactObservableList);
        contactsListView.setCellFactory(contactListView -> new ContactListCell());

        addNewContact.addEventHandler(ActionEvent.ACTION,(e)->{
            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
            stageCoordinator.getAddNewContactPopUp();
        });

    }
    @FXML
    void listViewSelectedContact(){

        contactModel = contactsListView.getSelectionModel().getSelectedItem();
        modelsFactory.setSelectedOnlineContactModel(contactModel);
        //System.out.println(modelsFactory.getCurrentSelectedOnlineContact().getName());
        stageCoordinator.goToContactProfilePage();
    }
}
