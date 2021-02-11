package eg.gov.iti.jets.ui.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContactModel {
    StringProperty userPhoneNumber = new SimpleStringProperty();
    StringProperty contactPhoneNumber = new SimpleStringProperty();
    StringProperty name = new SimpleStringProperty();
    StringProperty bio = new SimpleStringProperty();
    StringProperty email = new SimpleStringProperty();
    StringProperty image = new SimpleStringProperty();

    public ContactModel(String userPhoneNumber,String contactPhoneNumber,String name,String bio,String email,String image){
        this.userPhoneNumber.setValue(userPhoneNumber);
        this.contactPhoneNumber.setValue(contactPhoneNumber);
        this.name.setValue(name);
        this.bio.setValue(bio);
        this.email.setValue(email);
        this.image.setValue(image);
    }
}
