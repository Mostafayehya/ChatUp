package eg.gov.iti.jets.ui.controllers;

import domains.Invitation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.*;
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
        //File imageFile = new File(invitationModel.getSenderimage());
        Image image = null;
        System.out.println(invitationModel.getSenderrImage());
        InputStream inputStream = new ByteArrayInputStream(invitationModel.getSenderrImage());
        image =new Image(inputStream);
        imageCircle.setFill(new ImagePattern(image));
        senderNameLabel.setText(invitationModel.getSenderName());
    phoneLabel.setText(invitationModel.getSenderPhoneNumber());
    }

}
