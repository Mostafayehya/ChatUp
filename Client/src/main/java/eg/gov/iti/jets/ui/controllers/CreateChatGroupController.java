package eg.gov.iti.jets.ui.controllers;

import domains.*;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.CustomTextField;
import services.GroupChatService;
import utilities.FilesUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CreateChatGroupController implements Initializable {

    @FXML
    private HBox ImagePickerContainer;

    @FXML
    private Circle imageCircle;

    @FXML
    private Button choosePhoto;

    @FXML
    private CustomTextField groupNameTextField;

    @FXML
    private CustomTextField groupInfoTextField;

    @FXML
    private Button createGroupButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label errorLabel;

    StageCoordinator stageCoordinator;

    GroupChatService groupChatService;
    FileDomain userImageFile = null;


    public CreateChatGroupController() {
        File defaultGroupChatImage = new File(getClass().getResource("/photos/groupchat.jpg").getPath());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        errorLabel.setVisible(false);
        stageCoordinator = StageCoordinator.getInstance();
        groupChatService = RMIManager.getGroupChatService();

        choosePhoto.addEventHandler(ActionEvent.ACTION, (e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("image files", "*.png"));
            File userPhoto = fileChooser.showOpenDialog(choosePhoto.getScene().getWindow());
            String extension = FilesUtilities.getFileExtension(userPhoto);
            userImageFile = new FileDomain();
            userImageFile.setFileBytes(FilesUtilities.convertFileToByteArray(userPhoto, extension));
            userImageFile.setFileExtension(extension);
            try {
                imageCircle.setFill(new ImagePattern(new Image(new FileInputStream(userPhoto.getAbsoluteFile()))));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });


    }

    @FXML
    void createGroup(MouseEvent event) {

        String groupId = LocalDateTime.now().toString();
        String groupName = groupNameTextField.getText();
        String groupInfo = groupInfoTextField.getText();

        if (validateFields()) {
            GroupChat groupChat;
            if (userImageFile != null) {
                userImageFile.setFilename(groupName);
            }
            groupChat = new GroupChat(groupId, groupName, userImageFile);
            try {
                int result = groupChatService.createGroup(groupChat);
                if (result == -2) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Duplicate Group error");
                    alert.setHeaderText("gorup already exists");
                    alert.setContentText("please use other group info");
                    alert.showAndWait();
                } else {
                    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
                    stageCoordinator.navigateToGroupChatListPage();
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }

    }

    @FXML
    void cancel(MouseEvent event) {
        stageCoordinator.navigateToGroupChatListPage();
    }


    private boolean validateFields() {
        boolean validated = true;
        if (groupNameTextField.getText().equals("")) {
            groupNameTextField.setStyle("-fx-border-color: red;");
            validated = false;
        } else {
            groupNameTextField.setStyle("-fx-border-color: transparent;");
        }
        if (groupInfoTextField.getText().equals("")) {
            groupInfoTextField.setStyle("-fx-border-color: red;");
            validated = false;
        } else {
            groupInfoTextField.setStyle("-fx-border-color: transparent;");
        }

        return validated;
    }

}
