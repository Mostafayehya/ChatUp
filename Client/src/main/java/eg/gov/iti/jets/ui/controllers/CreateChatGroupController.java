package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
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

    StageCoordinator stageCoordinator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stageCoordinator = StageCoordinator.getInstance();

    }

    @FXML
    void cancel(MouseEvent event) {
        stageCoordinator.navigateToGroupChatListPage();
    }

}
