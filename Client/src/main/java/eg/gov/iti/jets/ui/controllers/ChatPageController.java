package eg.gov.iti.jets.ui.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;

public class ChatPageController {

    @FXML
    private ListView<?> contactsListView;

    @FXML
    private FontIcon attachButton;

    @FXML
    private TextField messgeTextField;

    @FXML
    private FontIcon emojiIcon;

    @FXML
    private ListView<?> chatListView;

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
}
