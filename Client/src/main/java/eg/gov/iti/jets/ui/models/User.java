package eg.gov.iti.jets.ui.models;

import javafx.scene.image.Image;

public class User {

    String phoneNumber;
    String name;
    Image image;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public User(String phoneNumber, String name, Image image) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
