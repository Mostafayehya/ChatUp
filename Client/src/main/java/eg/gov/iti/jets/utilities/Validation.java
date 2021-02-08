package eg.gov.iti.jets.utilities;

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
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public boolean matchPasswords(String password,String confirmPassword){
        return password.equals(confirmPassword);
    }
}
