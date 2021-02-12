package eg.gov.iti.jets.utilities;

import eg.gov.iti.jets.ui.controllers.ContactItemController;
import eg.gov.iti.jets.ui.controllers.MessageItemController;
import eg.gov.iti.jets.ui.models.Contact;
import eg.gov.iti.jets.ui.models.Message;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MessageListCell extends ListCell<Message> {

    FXMLLoader loader;
    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);
        if(empty || message == null){
            setText(null);
            setGraphic(null);
        }
        else{
            if(loader == null){
                loader = new FXMLLoader(getClass().getResource("/views/ChatMessage.fxml"));
                MessageItemController messageItemController = new MessageItemController(message);
                loader.setController(messageItemController);
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
