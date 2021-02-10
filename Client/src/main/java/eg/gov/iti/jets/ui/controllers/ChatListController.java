package eg.gov.iti.jets.ui.controllers;
import eg.gov.iti.jets.ui.models.Chat;
import eg.gov.iti.jets.utilities.ChatListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

    public class ChatListController implements Initializable {
        @FXML
        ListView<Chat> contactsListView;
        ObservableList<Chat> contactObservableList;

        public ChatListController(){
            contactObservableList = FXCollections.observableArrayList(
                    new Chat("0654425652","Reem","the bio","hadeer@gmail.com","/photos/user.jpg",""),
                    new Chat("8473965492","Nada","the bio","yasmina@gmail.com","/photos/user.jpg","")
            );
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            contactsListView.setItems(contactObservableList);
            contactsListView.setCellFactory(contactListView -> new ChatListCell());
        }
    }

