package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.ui.models.ContactModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatContactItemController implements Initializable {
    @FXML
    Circle imageCircle;
    @FXML
    Label contactNameLabel;
    @FXML
    Label bioLabel;
    ContactModel contact;

    public ChatContactItemController(ContactModel contact){
        this.contact = contact;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(contact.getImage() == null) {
            System.out.println("no image");
            contact.setImage("/photos/user.jpg");
        }
        URL imageUrl = getClass().getResource(contact.getName());
        if(imageUrl !=null) {
            System.out.println("not null");
            File imageFile = new File(imageUrl.getPath());
            Image image = null;
            try {
                image = new Image(new FileInputStream(imageFile.getAbsoluteFile()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            imageCircle.setFill(new ImagePattern(image));
        }

        contactNameLabel.setText(contact.getName());
        bioLabel.setText(contact.getContactPhoneNumber());

    }


}
