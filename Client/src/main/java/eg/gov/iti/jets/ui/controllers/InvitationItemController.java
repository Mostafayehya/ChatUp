package eg.gov.iti.jets.ui.controllers;

import domains.Invitation;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.utilities.ModelsFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import services.HandleContactsService;

import java.io.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class InvitationItemController implements Initializable {
    @FXML
    Circle imageCircle;
    @FXML
    Label senderNameLabel;
    @FXML
    Label phoneLabel;
    @FXML
    Button accBtn;
    @FXML
    Button rejBtn;
    Invitation invitationModel;
    HandleContactsService handleContactsService;


    public InvitationItemController(Invitation invitationModel) {

        this.invitationModel = invitationModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = null;
        System.out.println(invitationModel.getSenderrImage());
        InputStream inputStream = new ByteArrayInputStream(invitationModel.getSenderrImage());
        image = new Image(inputStream);
        imageCircle.setFill(new ImagePattern(image));
        senderNameLabel.setText(invitationModel.getSenderName());
        phoneLabel.setText(invitationModel.getSenderPhoneNumber());
        handleContactsService = RMIManager.getHandleContactsService();
        accBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            try {
                handleContactsService.acceptInvitation(invitationModel);
                ModelsFactory.getInstance().retrieveContacts();
                ModelsFactory.getInstance().removeInvitation(invitationModel);
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        });
        rejBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            try {
                handleContactsService.rejectInvitation(invitationModel);
                ModelsFactory.getInstance().removeInvitation(invitationModel);
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }

        });
    }


}
