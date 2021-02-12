package domains;


import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable {
    String userPhoneNumber;
    String contactPhoneNumber;
    String name;
    String bio;
    String email;
    String image;
    Status status;
    Mode mode;
    List<String> extraNumbers;

    public Contact() {
        this.image = "src/main/resources/photos/user.jpg";
    }

    public Contact(String userPhoneNumber, String contactPhoneNumber, String name, String bio, String email, String image,Status status,Mode mode) {
        this.userPhoneNumber = userPhoneNumber;
        this.contactPhoneNumber = contactPhoneNumber;
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.image = "src/main/resources/photos/user.jpg";
        this.status = status;
        this.mode = mode;
    }

    public Contact(String userPhoneNumber,String contactPhoneNumber){
        this.contactPhoneNumber = contactPhoneNumber;
        this.userPhoneNumber = userPhoneNumber;
    }

    public List<String> getExtraNumbers() {
        return extraNumbers;
    }

    public void setExtraNumbers(List<String> extraNumbers) {
        this.extraNumbers = extraNumbers;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
