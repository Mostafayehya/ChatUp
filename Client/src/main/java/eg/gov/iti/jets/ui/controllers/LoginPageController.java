package eg.gov.iti.jets.ui.controllers;

import domains.User;
import eg.gov.iti.jets.io.ClientCallbacksImpl;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.io.UserProperties;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import eg.gov.iti.jets.utilities.Validation;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
import java.util.ResourceBundle;

import services.AuthenticationService;

public class LoginPageController implements Initializable {
    @FXML
    private CustomTextField phoneTextField;
    @FXML
    private CustomPasswordField passwordTextField;
    @FXML
    private Button SignUpButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label failed;
    AuthenticationService authenticationService;
    ModelsFactory modelsFactory;
    UserProperties userProperties;
    String phoneNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelsFactory = ModelsFactory.getInstance();
        authenticationService = RMIManager.getAuthenticationService();
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(this::login);
        SignUpButton.setOnAction(this::goToSignUp);
        userProperties = new UserProperties();
//        try {
//           phoneNum= userProperties.ReadUserPhone();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        phoneTextField.setText(phoneNum);
        phoneTextField.addEventFilter(KeyEvent.KEY_TYPED, (e) -> {
            if (!new Validation().validatePhoneNumber(e.getCharacter()) || phoneTextField.getText().length() >= 11) {
                e.consume();
            }
        });

    }

    private void login(Event e) {
        User user = null;
        failed.setText("");
        if (phoneTextField.getText().equals("") || phoneTextField.getText().length() != 11) {
            phoneTextField.setStyle("-fx-border-color: #D32F2F;");
        }
        if (passwordTextField.getText().equals("")) {
            passwordTextField.setStyle("-fx-border-color: #d32f2f;");
        }
        String phone = phoneTextField.getText();
        String password = passwordTextField.getText();
        try {
            user = authenticationService.login(phone, password,RMIManager.getClientCallBack());
            if (user == null) {
                failed.setText("Either phone or password is incorrect");
                return;
            }
            modelsFactory.setCurrentUser(user);
            System.out.println(phone);
            System.out.println(password);
            userProperties.saveUserProperties(phone, password);

            StageCoordinator.getInstance().goToUserProfilePage();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void goToSignUp(Event e) {
        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
        stageCoordinator.goToSignupPage();

    }


}
