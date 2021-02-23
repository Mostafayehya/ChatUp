package domains;

import java.io.Serializable;

public class Invitation implements Serializable {
    int ID;
    String receiverPhoneNumber;
    String senderPhoneNumber;
    String senderimage;
    String senderName;
    byte[] SenderrImage;

    public Invitation(){

    }

    public Invitation( String senderPhoneNumber, String receiverPhoneNumber,String senderimage,String senderName) {

        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderPhoneNumber = senderPhoneNumber;
        this.senderimage=senderimage;
        this.senderName=senderName;
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

    public String getSenderimage() {
        return senderimage;
    }

    public void setSenderimage(String senderimage) {
        this.senderimage = senderimage;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public byte[] getSenderrImage() {
        return SenderrImage;
    }

    public void setSenderrImage(byte[] SenderrImage) {
        this.SenderrImage = SenderrImage;
    }
}
