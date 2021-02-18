package eg.gov.iti.jets.utilities;

import eg.gov.iti.jets.ui.controllers.ChatListcellController;
import eg.gov.iti.jets.ui.models.Chat;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ChatListCell extends ListCell<Chat> {
    FXMLLoader loader;
    @Override
    protected void updateItem(Chat chat, boolean empty) {
        super.updateItem(chat, empty);
        if(empty || chat == null){
            setText(null);
            setGraphic(null);
        }
        else{
            if(loader == null){
                loader = new FXMLLoader(getClass().getResource("/views/ChatListcell.fxml"));
                ChatListcellController chatListcellController  = new ChatListcellController(chat);
                loader.setController(chatListcellController);
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