package domains;

import java.io.Serializable;

public class Invitation implements Serializable {
    int ID;
    String receiverPhoneNumber;
    String senderPhoneNumber;

    public Invitation(){

    }

    public Invitation( String senderPhoneNumber, String receiverPhoneNumber) {

        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public Invitation(int ID, String receiverPhoneNumber, String senderPhoneNumber) {
        this.ID = ID;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderPhoneNumber = senderPhoneNumber;
    }


    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
