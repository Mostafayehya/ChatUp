package eg.gov.iti.jets.utilities;

import eg.gov.iti.jets.ui.controllers.ChatContactItemController;
import eg.gov.iti.jets.ui.controllers.ContactItemController;
import eg.gov.iti.jets.ui.models.ContactModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ChatContactListCell extends ListCell<ContactModel> {
    FXMLLoader loader;
    @Override
    protected void updateItem(ContactModel contact, boolean empty) {
        super.updateItem(contact, empty);
        if(empty || contact == null){
            setText(null);
            setGraphic(null);
        }
        else{
            if(loader == null){
                loader = new FXMLLoader(getClass().getResource("/views/ChatContactItem.fxml"));
                ChatContactItemController chatContactItemController = new ChatContactItemController(contact);
                loader.setController(chatContactItemController);
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
