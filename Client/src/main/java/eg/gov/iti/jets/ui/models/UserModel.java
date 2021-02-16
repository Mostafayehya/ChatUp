package eg.gov.iti.jets.ui.models;

import domains.Gender;
import domains.Mode;
import domains.Status;
import domains.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.time.LocalDate;


public class UserModel {
    StringProperty phoneNumber = new SimpleStringProperty();
    StringProperty name = new SimpleStringProperty();
    StringProperty email = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    StringProperty picture = new SimpleStringProperty();
    ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    StringProperty country = new SimpleStringProperty();
    ObjectProperty<LocalDate> dateOfBirth = new SimpleObjectProperty<>();
    StringProperty bio = new SimpleStringProperty();
    ObjectProperty<Status> status = new SimpleObjectProperty<>();
    ObjectProperty<Mode> mode = new SimpleObjectProperty<>();
    ObservableList<ContactModel> contacts;
    ObjectProperty<Image> userImage = new SimpleObjectProperty<>();

    public UserModel(String phoneNumber, String name, String email, String password, Gender gender, String country, LocalDate dateOfBirth, String bio, Status status, Mode mode, Image image) {
        this.phoneNumber.setValue(phoneNumber);
        this.name.setValue(name);
        this.email.setValue(email);
        this.password.setValue(password);
        this.gender.setValue(gender);
        this.country.setValue(country);
        this.dateOfBirth.setValue(dateOfBirth);
        this.bio.setValue(bio);
        this.status.setValue(status);
        this.mode.setValue(mode);
        this.userImage.setValue(image);
    }

    public ObservableList<ContactModel> getContacts() {
        return contacts;
    }

    public void setContacts(ObservableList<ContactModel> contacts) {
        this.contacts = contacts;
    }

    public UserModel(String phoneNumber, String name, String email, String password, String picture, Gender gender, String country, LocalDate dateOfBirth, String bio, Status status, Mode mode) {
        this.phoneNumber.setValue(phoneNumber);
        this.name.setValue(name);
        this.email.setValue(email);
        this.password.setValue(password);
        this.picture.setValue(picture);
        this.gender.setValue(gender);
        this.country.setValue(country);
        this.dateOfBirth.setValue(dateOfBirth);
        this.bio.setValue(bio);
        this.status.setValue(status);
        this.mode.setValue(mode);
    }

    public static User toUser(UserModel user){
        return new User(user.getPhoneNumber(),user.getName(),user.getEmail(),user.getPassword(),user.getPicture()
                ,user.getGender(),user.getCountry(),user.getDateOfBirth(),user.getBio(),user.getStatus(),user.getMode());
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getPicture() {
        return picture.get();
    }

    public StringProperty pictureProperty() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture.set(picture);
    }

    public Gender getGender() {
        return gender.get();
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public ObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getBio() {
        return bio.get();
    }

    public StringProperty bioProperty() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio.set(bio);
    }

    public Status getStatus() {
        return status.get();
    }

    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    public void setStatus(Status status) {
        this.status.set(status);
    }

    public Mode getMode() {
        return mode.get();
    }

    public ObjectProperty<Mode> modeProperty() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode.set(mode);
    }
}
