package eg.gov.iti.jets.ui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.TextField;
import javafx.scene.control.FocusModel;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    @FXML
    CustomTextField emailTextField;
    @FXML
    VBox formVBox;
    @FXML
    CustomPasswordField passwordTextField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontIcon lock = new FontIcon("fas-lock");
        FontIcon email = new FontIcon("mdi2e-email");
        passwordTextField.setLeft(lock);
        lock.setIconColor(Color.GRAY);
        email.setIconColor(Color.GRAY);
        emailTextField.setLeft(email);
        emailTextField.focusedProperty().addListener((e,r,t)->{
            if(emailTextField.isFocused()){
                emailTextField.setLeft(null);
            }
            else {
                emailTextField.setLeft(email);
            }
        });
        passwordTextField.focusedProperty().addListener((e,r,t)->{
            if(passwordTextField.isFocused()){
                passwordTextField.setLeft(null);
            }
            else {
                passwordTextField.setLeft(lock);
            }
        });
    }
}
