package eg.gov.iti.jets.ui.controllers;

import domains.User;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import eg.gov.iti.jets.utilities.ModelsFactory;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ResourceBundle;

import domains.Mode;
import javafx.scene.shape.Rectangle;


public class contactProfileController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label Bio;
    @FXML
    private TextField category;
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
    @FXML
    private Button editfirstname;
    @FXML
    private Button editemail;
    @FXML
    private Button editcategory;
    @FXML
    private Button addextranumber;

    ModelsFactory modelsFactory = ModelsFactory.getInstance();

    ContactModel contactModel = new ContactModel();

    @FXML
    private Button changephoto, savechanges;
    @FXML
    private ImageView myimageview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        category.setDisable(true);
        extranumber.setDisable(true);
        changephoto.setDisable(true);
        editemail.setVisible(false);
        editcategory.setDisable(true);
        addextranumber.setDisable(true);
        editfirstname.setDisable(true);
        savechanges.setDisable(true);


        final Rectangle clip = new Rectangle(102,102);
        clip.setArcHeight(180);
        clip.setArcWidth(180);
        //clip.setLayoutX(170);
        //clip.setLayoutY(75);
        myimageview.setClip(clip);
        contactModel = modelsFactory.getCurrentSelectedOnlineContact();
        name.textProperty().bindBidirectional(contactModel.nameProperty());
        myimageview.imageProperty().bindBidirectional(contactModel.contactImageProperty());
        Bio.textProperty().bindBidirectional(contactModel.bioProperty());
        firstname.textProperty().bindBidirectional(contactModel.nameProperty());
        email.textProperty().bindBidirectional(contactModel.emailProperty());

        Image image;
        image = contactModel.getContactImage();
        if (myimageview.getImage() != null) {

            // photo.setFill(new ImagePattern(hiddenimage.getImage()));
            //photo.setFill(new ImagePattern(contactModel.getContactImage()));
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
