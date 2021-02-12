package eg.gov.iti.jets.ui.controllers;

import com.jfoenix.controls.JFXButton;
import eg.gov.iti.jets.ui.models.Message;
import eg.gov.iti.jets.utilities.MessageListCell;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ChatPageController implements Initializable {

    @FXML
    private BorderPane chatBoarderPane;

    @FXML
    private ListView<?> contactsListView;

    @FXML
    private FontIcon attachButton;

    @FXML
    private TextField messgeTextField;

    @FXML
    private FontIcon emojiIcon;

    @FXML
    public ListView<Message> chatListView;

    @FXML
    private HBox chatBarHBox;

    @FXML
    private Circle contactImage;

    @FXML
    private Text contactNameText;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton videoCallButton;

    @FXML
    private JFXButton voiceCallButton;

    @FXML
    private JFXButton blockButton;

    @FXML
    private FontIcon profileButton;

    @FXML
    private FontIcon chatsButton;

    List<Message> messageList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        messageList = new ArrayList<>();
        Collections.addAll(messageList, new Message("10:00 AM", "Good morning"),
                new Message("08:15 PM", "Good night"));

        chatListView.setItems(FXCollections.observableList(messageList));
        chatListView.setCellFactory(chatListView -> new MessageListCell());

    }

    public void sendMessage(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            chatListView.getItems().add(new Message(LocalDate.now().toString(), messgeTextField.getText()));
            chatListView.scrollTo(chatListView.getItems().size() - 1);

            // Use service to send it over RMI
            messgeTextField.clear();

        }
    }
}
