package eg.gov.iti.jets.ui.models;

public class Contact {
    String phoneNumber;
    String name;
    String bio;
    String email;
    String image;

    public Contact() {
        this.image = "src/main/resources/photos/user.jpg";
    }

    public Contact(String phoneNumber, String name, String bio, String email, String image) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.image = "src/main/resources/photos/user.jpg";
    }

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
