package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationbarControlller implements Initializable {

    @FXML
    private JFXButton profileButton;

    @FXML
    private JFXButton chatsButton;

    @FXML
    private JFXButton contactsButton;

    @FXML
    private JFXButton notificationButton;

    @FXML
    private JFXButton invitationsButton;

    StageCoordinator stageCoordinator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stageCoordinator = StageCoordinator.getInstance();
    }



    @FXML
    void switchToChatPage(MouseEvent event) {
        stageCoordinator.switchToChatScreen();

    }

    @FXML
    void switchToContactsPage(MouseEvent event) {
        stageCoordinator.gotoContactsListPage();
    }

    @FXML
    void switchToInvitationsPage(MouseEvent event) {

    }

    @FXML
    void switchToNotifications(MouseEvent event) {

    }

    @FXML
    void switchToUserProfilePage(MouseEvent event) {
        stageCoordinator.goToUserProfilePage();
    }

    @FXML
    void signout(MouseEvent event) {

    }

    @FXML
    void switchToGroupChat(MouseEvent event) {

    }
}