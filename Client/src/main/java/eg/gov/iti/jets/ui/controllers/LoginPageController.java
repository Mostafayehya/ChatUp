package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;
import org.kordamp.ikonli.javafx.FontIcon;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    @FXML
    private CustomTextField phoneTextField;
    @FXML
    private CustomPasswordField passwordTextField;
    @FXML
    private Button SignUpButton;
    @FXML
    private Button loginButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loginButton.setOnAction(this::login);
        SignUpButton.setOnAction(this::goToSignUp);

    }
    private void login(Event e){
        if(phoneTextField.getText().equals("") || phoneTextField.getText().length()!=11){
            phoneTextField.setStyle("-fx-border-color: #D32F2F;");
        }
        if(passwordTextField.getText().equals("")){
            passwordTextField.setStyle("-fx-border-color: #D32F2F;");
        }

    }
    private void goToSignUp(Event e){
        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
        stageCoordinator.goToSignupPage();

    }
}
