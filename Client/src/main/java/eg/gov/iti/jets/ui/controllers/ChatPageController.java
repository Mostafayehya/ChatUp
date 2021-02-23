package eg.gov.iti.jets.ui.controllers;

import com.jfoenix.controls.JFXButton;
import domains.FileDomain;
import domains.FileMessage;
import domains.Message;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.MessageListCell;
import eg.gov.iti.jets.utilities.ModelsFactory;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.javafx.FontIcon;
import services.SingleChatService;
import utilities.FilesUtilities;

import java.io.File;
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
    private ImageView contactImage;

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

    ContactModel selectedOnlineContact;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Rectangle clip = new Rectangle(50, 50);
        clip.setArcWidth(180);
        clip.setArcHeight(180);
        contactImage.setClip(clip);

        modelsFactory = ModelsFactory.getInstance();
        selectedOnlineContact = modelsFactory.getCurrentSelectedOnlineContact();

        contactNameText.textProperty().bindBidirectional(selectedOnlineContact.nameProperty());


        // Todo 1 I should bind the messagelistView to the messages Map inside ModelsFactory Map<contactId,List<message>

        contactImage.imageProperty().bindBidirectional(selectedOnlineContact.contactImageProperty());

        messagesObservableList = modelsFactory.updateMessagesObservableList(selectedOnlineContact.getContactPhoneNumber());


        chatListView.setItems(messagesObservableList);

        chatListView.setCellFactory(messageListView -> new MessageListCell());

        attachButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(e)->{
            sendFile();
        });

    }

    public void sendMessage(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {

            // I need to store the currentContact inside models factory in order to specify the
            // receiver of the message
            Message newMessage = new Message(LocalDate.now().toString(), messgeTextField.getText(),
                    selectedOnlineContact.getContactPhoneNumber(),
                    modelsFactory.getCurrentUser().getPhoneNumber(),
                    modelsFactory.getCurrentUser().getName(),
                    selectedOnlineContact.getName()
            );

            try {
                RMIManager.getSingleChatService().sendMessage(newMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            messagesObservableList.add(newMessage);
            modelsFactory.getCurrentUser().receiveMessage(selectedOnlineContact.getContactPhoneNumber(),newMessage);
            chatListView.scrollTo(chatListView.getItems().size() - 1);
            // Use service to send it over RMI
            messgeTextField.clear();

        }
    }

    public void sendFile(){
        FileChooser fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(attachButton.getScene().getWindow());
        if(chosenFile!=null){
            double fileSize = (double)chosenFile.length() / (1024 * 1024);
            System.out.println(fileSize);
            if(fileSize>=14.5){
                Alert alert = new Alert(Alert.AlertType.WARNING,"File too big to send");
                alert.show();
                return;
            }
            // Sending message in other thread
            Thread thread = new Thread(()->{
                String extension = FilesUtilities.getFileExtension(chosenFile);
                byte [] messageBytes = FilesUtilities.convertFileToByteArray(chosenFile,extension);
                FileDomain fileDomain = new FileDomain(messageBytes,extension,chosenFile.getName());
                FileMessage fileMessage = new FileMessage(LocalDate.now().toString(),"",
                        selectedOnlineContact.getContactPhoneNumber(), modelsFactory.getCurrentUser().getPhoneNumber(),fileDomain);
                try {
                    RMIManager.getSingleChatService().sendMessage(fileMessage);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    System.out.println("run later");
                    messagesObservableList.add(fileMessage);
                    modelsFactory.getCurrentUser().receiveMessage(selectedOnlineContact.getContactPhoneNumber(),fileMessage);
                    chatListView.scrollTo(chatListView.getItems().size() - 1);
                });


            });
            thread.start();

        }

    }
}
