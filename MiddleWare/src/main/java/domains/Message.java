package domains;

import java.io.Serializable;

public class Message implements Serializable {

    String time;
    String content;
    String receiverPhoneNumber;
    String senderPhoneNumber;
    String senderName;
    String receiverName;



    public Message() {
    }

    public Message(String time, String content, String receiverPhoneNumber, String senderPhoneNumber) {
        this.time = time;
        this.content = content;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public Message(String time, String content) {
        this.time = time;
        this.content = content;
    }

    public Message(String time, String content, String receiverPhoneNumber, String senderPhoneNumber, String senderName, String receiverName) {
        this.time = time;
        this.content = content;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderPhoneNumber = senderPhoneNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
