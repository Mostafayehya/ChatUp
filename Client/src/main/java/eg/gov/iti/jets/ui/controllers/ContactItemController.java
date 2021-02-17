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

public class ContactItemController implements Initializable {
    @FXML
    Circle imageCircle;
    @FXML
    Label contactNameLabel;
    @FXML
    Label bioLabel;
    @FXML
    FontIcon messageIcon;
    ContactModel contact;

    public ContactItemController(ContactModel contact) {
        this.contact = contact;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = null;
//        if (contact.getContactImage() == null) {
//            System.out.println("no image");
//            File imageFile = new File(getClass().getResource("/photos/user.jpg").getPath());
//            try {
//                image = new Image(new FileInputStream(imageFile.getAbsoluteFile()));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        else {
            image = contact.getContactImage();
//        }
        imageCircle.setFill(new ImagePattern(image));
        contactNameLabel.setText(contact.getName());
        bioLabel.setText(contact.getBio());

        messageIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            System.out.println(contact.getName());
        });
    }


}
