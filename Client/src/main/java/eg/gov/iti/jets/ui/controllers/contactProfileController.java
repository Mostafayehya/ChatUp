package eg.gov.iti.jets.ui.controllers;

import domains.User;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import eg.gov.iti.jets.utilities.ModelsFactory;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ResourceBundle;

import domains.Mode;


public class contactProfileController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label Bio;
    @FXML
    private Label category;
    @FXML
    private Circle photo;
    @FXML
    private Circle mode;
    @FXML
    private ListView extranumber;
    @FXML
    private TextField firstname;
    @FXML
    private TextField email;

    ModelsFactory modelsFactory = ModelsFactory.getInstance();

    ContactModel contactModel = new ContactModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        contactModel = modelsFactory.getCurrentSelectedOnlineContact();

        name.setText(contactModel.getName());

        Bio.setText(contactModel.getBio());

        firstname.setText(contactModel.getName());

        email.setText(contactModel.getEmail());

        Image image;
        image = contactModel.getContactImage();
        if (image != null) {

            photo.setFill(new ImagePattern(image));
            photo.setFill(new ImagePattern(contactModel.getContactImage()));
        }
        System.out.println(image + "this is the image");
        if (contactModel.getMode().name().equalsIgnoreCase("BUSY")) {
            mode.setFill(Color.RED);
        } else if (contactModel.getMode().name().equalsIgnoreCase("AVAILABLE")) {
            mode.setFill(Color.GREEN);
        } else {
            mode.setFill(Color.WHITE);
        }

    }

    public void changeFirstName() {
    }


    public void changeEmail() {
    }

    public void changePhoto() {
    }

    public void changeCategory() {
    }

    public void addPhone() {
    }

    public void sendMsg() {
    }

    public void voiceCall() {
    }

    public void videoCall() {
    }

    public void Block() {
    }

    public void save() {
    }


}
