package eg.gov.iti.jets.ui.controllers;

import domains.Invitation;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.ui.models.InvitationModel;
import eg.gov.iti.jets.ui.models.UserModel;
import eg.gov.iti.jets.utilities.ModelsFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class InvitationItemController implements Initializable {
    @FXML
    Circle imageCircle;
    @FXML
    Label senderNameLabel;
    @FXML
    Label phoneLabel;
    Invitation invitationModel;


    public InvitationItemController(Invitation invitationModel) {

        this.invitationModel = invitationModel;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = null;
       // image = invitationModel.getSenderImage();
        imageCircle.setFill(new ImagePattern(image));
        senderNameLabel.setText(invitationModel.getSenderName());
    phoneLabel.setText(invitationModel.getSenderPhoneNumber());
    }

}
