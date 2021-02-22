package eg.gov.iti.jets.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageItemController implements Initializable {

    @FXML
    private HBox messageLayout;

    @FXML
    private ImageView senderCircleImage;

    @FXML
    private Text senderNameText;

    @FXML
    private Text timeText;

    @FXML
    private Label messageContent;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        final Rectangle clip = new Rectangle(60, 60);
        clip.setArcWidth(180);
        clip.setArcHeight(180);
        senderCircleImage.setClip(clip);
    }

    public void setSenderName(String senderName){
        this.senderNameText.setText(senderName);
    }

    public void setMessageContent(String messageContent){
        this.messageContent.setText(messageContent);
    }

    public void setTimeText(String timeText){
        this.timeText.setText(timeText);
    }

    public void setMessageOrientation(NodeOrientation orientation) {
        messageLayout.setNodeOrientation(orientation);
    }

    public void setSenderCircleImage(Image image){
        senderCircleImage.setImage(image);
    }
}
