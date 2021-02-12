package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.ui.models.Message;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
    private Circle senderCircleImage;

    @FXML
    private Text senderNameText;

    @FXML
    private Text timeText;

    @FXML
    private Label messageContent;

    Message message;

    public MessageItemController(Message message) {
        this.message = message;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File imgFile = new File("src/main/resources/images/img.png");
        try {
            Image image = new Image(new FileInputStream(imgFile.getAbsolutePath()));

            senderCircleImage.setFill(new ImagePattern(image));
            senderNameText.setText("Mostafa yehya");
            messageContent.setText(message.getContent());
            timeText.setText(message.getTime());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
