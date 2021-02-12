package eg.gov.iti.jets.utilities;

import eg.gov.iti.jets.ui.controllers.ContactItemController;
import eg.gov.iti.jets.ui.models.Contact;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ContactListCell extends ListCell<Contact> {
    FXMLLoader loader;
    @Override
    protected void updateItem(Contact contact, boolean empty) {
        super.updateItem(contact, empty);
        if(empty || contact == null){
            setText(null);
            setGraphic(null);
        }
        else{
            if(loader == null){
                loader = new FXMLLoader(getClass().getResource("/views/ContactItem.fxml"));
                ContactItemController contactItemController = new ContactItemController(contact);
                loader.setController(contactItemController);
                try {
                    setText(null);
                    setGraphic(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
