package eg.gov.iti.jets.utilities;

import domains.Message;
import eg.gov.iti.jets.ui.controllers.MessageItemController;
import eg.gov.iti.jets.ui.models.UserModel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MessageListCell extends ListCell<Message> {

    private  Node graphic;
    private final UserModel currentUser = ModelsFactory.getInstance().getCurrentUser();
    private final MessageItemController messageItemController;

    public MessageListCell()   {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ChatMessage.fxml"));
        try {
            graphic = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.messageItemController = loader.getController();
    }

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);
        if (empty || message == null) {
            setText(null);
            setGraphic(null);
        } else {

            // Client Message

            if (currentUser.getPhoneNumber().equals(message.getSenderPhoneNumber())){
                messageItemController.setSenderName(currentUser.getName());
                messageItemController.setMessageContent(message.getContent());
                messageItemController.setTimeText(message.getTime());
                messageItemController.setMessageOrientation(NodeOrientation.LEFT_TO_RIGHT);


            }else{
                messageItemController.setSenderName(ModelsFactory.getInstance().selectedContact.getName());
                messageItemController.setMessageContent(message.getContent());
                messageItemController.setTimeText(message.getTime());
                messageItemController.setMessageOrientation(NodeOrientation.RIGHT_TO_LEFT);

            }

            //received Message

            setText(null);
            setGraphic(graphic);

        }

    }


}
