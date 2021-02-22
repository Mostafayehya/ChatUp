package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.ui.models.ContactModel;
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
    Label contactNameLabel;
    @FXML
    Label phoneLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File imgFile = new File("src/main/resources/images/img.png");
        try {
            Image image = new Image(new FileInputStream(imgFile.getAbsolutePath()));

            imageCircle.setFill(new ImagePattern(image));
            contactNameLabel.setText("Mostafa yehya");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void setSenderName(String senderName){
        this.contactNameLabel.setText(senderName);
    }

}
