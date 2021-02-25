package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.io.UserProperties;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.kordamp.ikonli.javafx.FontIcon;
import services.AuthenticationService;

import java.net.URL;
import java.rmi.RemoteException;
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
    AuthenticationService authenticationService;
    ModelsFactory modelsFactory;

    UserProperties userProperties = new UserProperties();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stageCoordinator = StageCoordinator.getInstance();
        authenticationService = RMIManager.getAuthenticationService();
        modelsFactory = ModelsFactory.getInstance();
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

        try {
            authenticationService.signout(modelsFactory.getCurrentUser().getPhoneNumber());
            StageCoordinator.getInstance().goToLoginPage();
            userProperties.RemovePassFrmFile();
            ModelsFactory.getInstance().resetData();


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToGroupChat(MouseEvent event) {

        stageCoordinator.navigateToGroupChatListPage();

    }
}