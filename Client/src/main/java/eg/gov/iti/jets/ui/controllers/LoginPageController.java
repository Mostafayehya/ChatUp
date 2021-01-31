package eg.gov.iti.jets.ui.controllers;

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
    private FontIcon lockIcon;
    private FontIcon phoneIcon;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        lockIcon = new FontIcon("fas-lock");
        phoneIcon = new FontIcon("mdi2p-phone");
        passwordTextField.setLeft(lockIcon);
        lockIcon.setIconColor(Color.GRAY);
        phoneIcon.setIconColor(Color.GRAY);
        phoneTextField.setLeft(phoneIcon);
        phoneTextField.focusedProperty().addListener((e, r, t)-> handlePhoneTextField());
        passwordTextField.focusedProperty().addListener((e,r,t)-> handlePasswordTextField());
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
    private void handlePasswordTextField(){
        if(passwordTextField.isFocused()){
            passwordTextField.setLeft(null);
            passwordTextField.setStyle("-fx-border-color: transparent;");
        }
        else {
            if(passwordTextField.getText().equals("")){
                passwordTextField.setStyle("-fx-border-color: #D32F2F;");
                passwordTextField.setLeft(lockIcon);
            }

        }
    }
    private void handlePhoneTextField(){
        if(phoneTextField.isFocused()){
            phoneTextField.setLeft(null);
            phoneTextField.setStyle("-fx-border-color: transparent;");
        }
        else {
            if(phoneTextField.getText().equals("") || phoneTextField.getText().length()!=11){
                phoneTextField.setStyle("-fx-border-color: #D32F2F;");
                phoneTextField.setLeft(phoneIcon);
            }

        }
    }
    private void goToSignUp(Event e){
        //StageCoordinator stageCoordinator = StageCoordinator.getInstance();
        //stageCoordinator.goToSignUpPage();

    }
}
