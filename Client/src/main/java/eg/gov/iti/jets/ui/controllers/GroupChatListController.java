package eg.gov.iti.jets.ui.controllers;

import domains.GroupChat;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupChatListController implements Initializable {

    @FXML
    private Button createGroupChat;

    @FXML
    private ListView<GroupChat> groupChatListView;

    StageCoordinator stageCoordinator;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stageCoordinator = StageCoordinator.getInstance();
    }

    @FXML
    void groupChatListClicked(MouseEvent event) {

    }

    @FXML
    void createNewGroupChat(MouseEvent event) {

        stageCoordinator.navigateToGroupChatCreatePage();

    }


}
