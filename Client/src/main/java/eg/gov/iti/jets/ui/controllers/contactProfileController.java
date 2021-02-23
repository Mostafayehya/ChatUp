package eg.gov.iti.jets.ui.controllers;

import domains.User;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import eg.gov.iti.jets.utilities.ModelsFactory;
import java.net.URL;
import java.util.ResourceBundle;


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

    ModelsFactory modelsFactory= ModelsFactory.getInstance();

    ContactModel contactModel = new ContactModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        contactModel = modelsFactory.getCurrentSelectedOnlineContact();

        name.setText(contactModel.getName()) ;

        Bio.setText(contactModel.getBio());
///////////////////////////////////////////////////////////////////////////////////////////
        Image image = null;
        image = contactModel.getContactImage();
        photo.setFill(new ImagePattern(image));
        photo.setFill(new ImagePattern(contactModel.getContactImage()));
       if(contactModel.getMode().equals(" BUSY") ){mode.setFill(Color.RED);}
          else if(contactModel.getMode().equals(" AVAILABLE")){mode.setFill(Color.GREEN);}
            else{mode.setFill(Color.WHITE);}
///////////////////////////////////////////////////////////////////////////////////////////

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
