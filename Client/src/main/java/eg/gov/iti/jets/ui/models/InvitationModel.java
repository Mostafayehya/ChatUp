package eg.gov.iti.jets.ui.models;

import domains.Mode;
import domains.Status;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class InvitationModel {
    StringProperty senderPhoneNumber = new SimpleStringProperty();
    StringProperty senderName = new SimpleStringProperty();
    StringProperty image = new SimpleStringProperty();
    ObservableList<InvitationModel> invitations;


    ObjectProperty<Image> senderImage = new SimpleObjectProperty<>();

    public InvitationModel(String senderPhoneNumber, String name, String image){
        this.senderPhoneNumber.setValue(senderPhoneNumber);
        this.senderName.setValue(name);
        this.image.setValue(image);

    }

    public InvitationModel(String senderPhoneNumber, String name,  Image image){
        this.senderPhoneNumber.setValue(senderPhoneNumber);
        this.senderName.setValue(name);
        this.senderImage.setValue(image);
        this.image.setValue("");
    }


    public ObservableList<InvitationModel> getInvitations() {
        return invitations;
    }


    public InvitationModel() {
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber.get();
    }

    public StringProperty senderPhoneNumberProperty() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber.set(senderPhoneNumber);
    }

    public String getSenderName() {
        return senderName.get();
    }

    public StringProperty senderNameProperty() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName.set(senderName);
    }

    public String getImage() {
        return image.get();
    }

    public StringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }


    public void setInvitationModel(InvitationModel cm){
        this.senderName.set(cm.senderName.get());
        this.image.set(cm.getImage());
        this.senderPhoneNumber.set(cm.getSenderPhoneNumber());
    }


    public Image getSenderImage() {
        return senderImage.get();
    }

    public ObjectProperty<Image> senderImageProperty() {
        return senderImage;
    }

    public void setSenderImage(Image senderImage) {
        this.senderImage.set(senderImage);
    }
}
