package domains;

public class FileDomain {
    byte[] fileBytes;
    String fileExtension;
    String filename;

    public FileDomain() {
        fileBytes = new byte[1024];
        fileExtension = "";
        filename = "";
    }

    public FileDomain(byte[] fileBytes, String fileExtension, String filename) {
        this.fileBytes = fileBytes;
        this.fileExtension = fileExtension;
        this.filename = filename;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
