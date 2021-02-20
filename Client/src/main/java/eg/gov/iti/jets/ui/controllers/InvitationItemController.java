package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.ui.models.ContactModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class InvitationItemController implements Initializable {
    @FXML
    Circle imageCircle;
    @FXML
    Label contactNameLabel;
    @FXML
    Label phoneLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
