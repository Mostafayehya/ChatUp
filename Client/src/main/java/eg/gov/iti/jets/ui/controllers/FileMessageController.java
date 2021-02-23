package eg.gov.iti.jets.ui.controllers;

import domains.FileMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import org.kordamp.ikonli.javafx.FontIcon;
import utilities.FilesUtilities;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FileMessageController implements Initializable {
    @FXML
    BorderPane messageLayout;
    @FXML
    FontIcon downloadIcon;
    @FXML
    Label fileName;
    @FXML
    Text timeText;
    @FXML
    Text senderNameText;
    @FXML
    ImageView senderImage;

    FileMessage fileMessage;

    public FileMessageController(FileMessage fileMessage){
        this.fileMessage = fileMessage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final Rectangle clip = new Rectangle(60, 60);
        clip.setArcWidth(180);
        clip.setArcHeight(180);
        senderImage.setClip(clip);

        fileName.setText(fileMessage.getFile().getFilename());
        downloadIcon.addEventHandler(MouseEvent.MOUSE_CLICKED,(e)->{
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File chosenDirectory = directoryChooser.showDialog(downloadIcon.getScene().getWindow());
            File messageFile = new File(chosenDirectory.getPath()+"/"+fileMessage.getFile().getFilename()+"."+fileMessage.getFile().getFileExtension());
            FilesUtilities.writeByteArrayToFile(messageFile,fileMessage.getFile().getFileBytes());

        });
    }

    public void setSenderName(String senderName){
        this.senderNameText.setText(senderName);
    }

    public void setTimeText(String timeText){
        this.timeText.setText(timeText);
    }

    public void setMessageOrientation(NodeOrientation orientation) {
        messageLayout.setNodeOrientation(orientation);
    }

    public void setSenderCircleImage(Image image){
        senderImage.setImage(image);
    }
}
