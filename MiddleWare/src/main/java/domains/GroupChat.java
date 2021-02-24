package domains;

import java.io.Serializable;

public class GroupChat implements Serializable {

    String id;
    String groupName;
    FileDomain groupImage;
    String about;
    String groupImagePath;

    public GroupChat() {
    }

    public GroupChat(String id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public GroupChat(String id, String groupName, FileDomain groupImage) {
        this.id = id;
        this.groupName = groupName;
        this.groupImage = groupImage;
    }

    public String getAbout() {
        return about;
    }

    public String getGroupImagePath() {
        return groupImagePath;
    }

    public void setGroupImagePath(String groupImagePath) {
        this.groupImagePath = groupImagePath;
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
