package eg.gov.iti.jets.ui.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;
import org.kordamp.ikonli.javafx.FontIcon;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    @FXML
    CustomTextField emailTextField;
    @FXML
    CustomPasswordField passwordTextField;
    @FXML
    Button signUpButton;
    @FXML
    Button loginButton;
    FontIcon lockIcon;
    FontIcon emailIcon;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lockIcon = new FontIcon("fas-lock");
        emailIcon = new FontIcon("mdi2e-email");
        passwordTextField.setLeft(lockIcon);
        lockIcon.setIconColor(Color.GRAY);
        emailIcon.setIconColor(Color.GRAY);
        emailTextField.setLeft(emailIcon);
        emailTextField.focusedProperty().addListener((e,r,t)->{
            handleTextField();
        });
        passwordTextField.focusedProperty().addListener((e,r,t)->{
            handlePasswordTextField();
        });
        loginButton.setOnAction(this::login);

    }
    public void login(Event e){
        if(emailTextField.getText().equals("")){
            emailTextField.setStyle("-fx-border-color: #D32F2F;");
        }
        if(passwordTextField.getText().equals("")){
            passwordTextField.setStyle("-fx-border-color: #D32F2F;");
        }

    }
    public void handlePasswordTextField(){
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
    public void handleTextField(){
        if(emailTextField.isFocused()){
            emailTextField.setLeft(null);
            emailTextField.setStyle("-fx-border-color: transparent;");
        }
        else {
            if(emailTextField.getText().equals("")){
                emailTextField.setStyle("-fx-border-color: #D32F2F;");
                emailTextField.setLeft(emailIcon);
            }

        }
    }
    public void goToSignUp(){

    }
}
