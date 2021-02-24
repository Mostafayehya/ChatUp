package eg.gov.iti.jets.services.implementations;

import domains.FileDomain;
import domains.GroupChat;
import domains.Message;
import domains.User;
import eg.gov.iti.jets.data.dao.GroupchatDao;
import eg.gov.iti.jets.data.dao.GroupchatDaoImpl;
import eg.gov.iti.jets.data.dao.UserDao;
import services.GroupChatService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GroupChatServiceImpl extends UnicastRemoteObject implements GroupChatService {

    private GroupchatDao groupChatDao;

    public GroupChatServiceImpl() throws RemoteException{

        groupChatDao = new GroupchatDaoImpl();
    }

    @Override
    public void sendMessage(Message message) throws RemoteException {

    }

    @Override
    public int createGroup(GroupChat groupChat) throws RemoteException {
        if(groupChatDao.getGroupchatByName(groupChat.getGroupName())!=null){
            return -2;
        }
        groupChat.setGroupImagePath(saveGroupChatPhoto(groupChat));
        return groupChatDao.insertGroupchat(groupChat);
    }

    public String saveGroupChatPhoto(GroupChat groupChat) {
        FileDomain groupChatPhoto = groupChat.getGroupImage();
        String photoPath;
        if (groupChatPhoto != null) {
            File file = new File("src/main/resources/GroupPhotos/"+groupChatPhoto.getFilename() + "." + groupChatPhoto.getFileExtension());

            InputStream is = new ByteArrayInputStream(groupChatPhoto.getFileBytes());
            try {
                BufferedImage bufferedImage = ImageIO.read(is);
                ImageIO.write(bufferedImage, groupChatPhoto.getFileExtension(), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            photoPath = file.getPath();
        } else {
            //default photo
            photoPath = "src/main/resources/GroupPhotos/groupchat.jpg";
        }
        return photoPath;
    }

    @Override
    public List<GroupChat> getGroupChats() throws RemoteException {
        return null;
    }
}
