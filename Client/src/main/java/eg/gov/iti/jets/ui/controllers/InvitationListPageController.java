package eg.gov.iti.jets.ui.controllers;

import domains.Invitation;
import eg.gov.iti.jets.utilities.InvitationListCell;
import eg.gov.iti.jets.utilities.ModelsFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;


public class InvitationListPageController implements Initializable {
    @FXML
    ListView<Invitation> invitationListView;
    ObservableList<Invitation> invitationObservableList;
    ModelsFactory modelsFactory;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelsFactory = ModelsFactory.getInstance();
        invitationObservableList =modelsFactory.getInvitationObservableList();
        invitationListView.setItems(invitationObservableList);
        invitationListView.setCellFactory(invitationListView -> new InvitationListCell());

    }
}
