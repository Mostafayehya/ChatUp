package eg.gov.iti.jets.utilities;

import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public boolean validatePhoneNumber(String text){
        try{
            Integer.parseInt(text);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public boolean validateEmail(String text){
        String regex = "[a-z].+@[a-z].+\\.[a-z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public boolean isempty(TextField textField){
        return textField.textProperty().getValue().equals("");
    }

    public boolean matchPasswords(String password,String confirmPassword){
        return password.equals(confirmPassword);
    }
}
