package eg.gov.iti.jets.ui.controllers;

import domains.FileMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import org.kordamp.ikonli.javafx.FontIcon;
import utilities.FilesUtilities;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FileMessageController implements Initializable {
    @FXML
    FontIcon downloadIcon;
    @FXML
    Label fileName;
    @FXML
    Text timeText;
    @FXML
    Text senderNameText;
    @FXML
    Circle senderCircleImage;

    FileMessage fileMessage;

    public FileMessageController(FileMessage fileMessage){
        this.fileMessage = fileMessage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileName.setText(fileMessage.getFile().getFilename());
        downloadIcon.addEventHandler(MouseEvent.MOUSE_CLICKED,(e)->{
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File chosenDirectory = directoryChooser.showDialog(downloadIcon.getScene().getWindow());
            File messageFile = new File(chosenDirectory.getPath()+"/"+fileMessage.getFile().getFilename()+"."+fileMessage.getFile().getFileExtension());
            FilesUtilities.writeByteArrayToFile(messageFile,fileMessage.getFile().getFileBytes());

        });
    }
}
