package eg.gov.iti.jets.ui.models;

public class Message {

    String time;
    String content;

    public Message() {
    }

    public Message(String time, String content) {
        this.time = time;
        this.content = content;
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
}
