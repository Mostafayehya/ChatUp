package domains;

public class GroupChat {

    String id;
    String groupName;
    FileDomain groupImage;
    String about;

    public GroupChat() {
    }

    public GroupChat(String id, String groupName, FileDomain groupImage) {
        this.id = id;
        this.groupName = groupName;
        this.groupImage = groupImage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public FileDomain getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(FileDomain groupImage) {
        this.groupImage = groupImage;
    }
}
