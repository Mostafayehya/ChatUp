package eg.gov.iti.jets.utilities;

import domains.Message;
import eg.gov.iti.jets.ui.controllers.MessageItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MessageListCell extends ListCell<Message> {

    private  Node graphic;
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

            // todo Fix message model and update here
            messageItemController.setSenderName("Mostafa Yehya");
            messageItemController.setMessageContent(message.getContent());
            messageItemController.setTimeText(message.getTime());

            setText(null);
            setGraphic(graphic);

        }

    }


}
