package eg.gov.iti.jets.ui.controllers;

import com.jfoenix.controls.JFXButton;
import domains.Message;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.MessageListCell;
import eg.gov.iti.jets.utilities.ModelsFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;
import services.SingleChatService;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
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

    ModelsFactory modelsFactory;

    SingleChatService singleChatService;

    ContactModel selectedContact;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modelsFactory = ModelsFactory.getInstance();

        selectedContact = modelsFactory.getSelectedContact();

        messagesObservableList =modelsFactory.getMessagesObservableList();
        chatListView.setItems(messagesObservableList);

        chatListView.setCellFactory(messageListView -> new MessageListCell());

    }

    public void sendMessage(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {

            // I need to store the currentContact inside models factory in order to specify the
            // receiver of the message
            Message newMessage = new Message(LocalDate.now().toString(),messgeTextField.getText(),
                  selectedContact.getContactPhoneNumber()  ,"01068867848");

            try {
                RMIManager.getSingleChatService().sendMessage(newMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            messagesObservableList.add(new Message(LocalDate.now().toString(), messgeTextField.getText()));
            chatListView.scrollTo(chatListView.getItems().size() - 1);
            // Use service to send it over RMI
            messgeTextField.clear();

        }
    }
}
