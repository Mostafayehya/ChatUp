package domains;


import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    String phoneNumber;
    String name;
    String email;
    String password;
    String userPhotoPath;
    Gender gender;
    String country;
    LocalDate dateOfBirth;
    String bio;
    Status status;
    Mode mode;
    FileDomain userPhoto;

    public User() {
        gender = Gender.FEMALE;
        dateOfBirth = LocalDate.now();
    }

    public User(String phoneNumber, String name, String email, String password){
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String phoneNumber, String name, String email, String password, String userPhotoPath, Gender gender, String country, LocalDate dateOfBirth, String bio, Status status, Mode mode) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userPhotoPath = userPhotoPath;
        this.gender = gender;
        this.country = country;
        if(dateOfBirth==null){
            this.dateOfBirth = LocalDate.now();
        }
        else
            this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.status = status;
        this.mode = mode;
    }

    public User(String phoneNumber, String name, String email, String password, FileDomain userPhoto, Gender gender, String country, LocalDate dateOfBirth, String bio, Status status, Mode mode) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userPhoto = userPhoto;
        this.gender = gender;
        this.country = country;
        if(dateOfBirth==null){
            this.dateOfBirth = LocalDate.now();
        }
        else
            this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.status = status;
        this.mode = mode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhotoPath() {
        return userPhotoPath;
    }

    public void setUserPhotoPath(String userPhotoPath) {
        this.userPhotoPath = userPhotoPath;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public FileDomain getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(FileDomain userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public String toString() {
        return "User{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", picture='" + userPhotoPath + '\'' +
                ", gender=" + gender +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bio='" + bio + '\'' +
                ", status=" + status +
                ", mode=" + mode +
                '}';
    }
}

