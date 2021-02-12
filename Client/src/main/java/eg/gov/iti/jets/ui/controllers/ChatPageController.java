package eg.gov.iti.jets.ui.controllers;

import com.jfoenix.controls.JFXButton;
import eg.gov.iti.jets.ui.models.Message;
import eg.gov.iti.jets.utilities.MessageListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    ObservableList<Message> messagesObservableList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        messagesObservableList = FXCollections.observableArrayList(new Message("10:00 AM", "Good morning"),
                new Message("08:15 PM", "Good night"));

        chatListView.setItems(messagesObservableList);

        messagesObservableList.addAll(new Message(LocalDate.now().toString(), "After setting cellFactory 2"),
                new Message(LocalDate.now().toString(), "After setting cellFactory3"),
                new Message(LocalDate.now().toString(), "After setting cellFactory4"),
                new Message(LocalDate.now().toString(), "After setting cellFactory5"),
                new Message(LocalDate.now().toString(), "After setting cellFactory6"),
                new Message(LocalDate.now().toString(), "After setting cellFactory7"),
                new Message(LocalDate.now().toString(), "After setting cellFactory8"),
                new Message(LocalDate.now().toString(), "After setting cellFactory9"),
                new Message(LocalDate.now().toString(), "After setting cellFactory10"),
                new Message(LocalDate.now().toString(), "After setting cellFactory11"),
                new Message(LocalDate.now().toString(), "After setting cellFactory12"),
                new Message(LocalDate.now().toString(), "After setting cellFactory13"),
                new Message(LocalDate.now().toString(), "After setting cellFactory14"),
                new Message(LocalDate.now().toString(), "After setting cellFactory15"),
                new Message(LocalDate.now().toString(), "After setting cellFactory16"));

        chatListView.setCellFactory(messageListView -> new MessageListCell());

    }

    public void sendMessage(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {

            messagesObservableList.add(new Message(LocalDate.now().toString(), messgeTextField.getText()));
            // Use service to send it over RMI
            messgeTextField.clear();

        }
    }
}
