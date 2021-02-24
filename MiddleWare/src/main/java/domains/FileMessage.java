package domains;

public class FileMessage extends Message{
    FileDomain file;

    public FileMessage(){
    }

    public FileMessage(String time, String content, String receiverPhoneNumber, String senderPhoneNumber, String senderName, String receiverName, FileDomain file) {
        super(time, content, receiverPhoneNumber, senderPhoneNumber, senderName, receiverName);
        this.file = file;
    }

    public FileMessage(FileDomain file) {
        this.file = file;
    }

    public FileMessage(String time, String content, String receiverPhoneNumber, String senderPhoneNumber, FileDomain file) {
        super(time, content, receiverPhoneNumber, senderPhoneNumber);
        this.file = file;
    }

    public FileMessage(String time, String content, FileDomain file) {
        super(time, content);
        this.file = file;
    }

    public FileDomain getFile() {
        return file;
    }

    public void setFile(FileDomain file) {
        this.file = file;
    }
}
