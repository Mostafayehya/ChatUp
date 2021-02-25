package eg.gov.iti.jets.services.implementations;

import clientInterface.ClientCallbacks;
import domains.Contact;
import domains.FileDomain;
import domains.User;
import eg.gov.iti.jets.data.dao.ContactDao;
import eg.gov.iti.jets.data.dao.ContactDaoImpl;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import eg.gov.iti.jets.io.Server;
import services.AuthenticationService;
import utilities.FilesUtilities;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService {
    UserDao userDao;
    ContactDao contactDao;
    Map<String,
            ClientCallbacks> onlineUsers;
    public AuthenticationServiceImpl() throws RemoteException {
        userDao = new UserDaoImpl();
        contactDao = new ContactDaoImpl();
        onlineUsers = Server.getInstance().getOnlineClients();

    }

    @Override
    public User login(String phone , String password, ClientCallbacks chatUpClient) {
        User user =  userDao.getUserByPhoneAndPassword(phone, password);
        if(user!=null){
            //add chatUpClient to the clients map in server
            String userPhotoPath = user.getUserPhotoPath();
            if (!userPhotoPath.equals("")) {
                System.out.println("User has photo");
                File file = new File(userPhotoPath);
                String extension = FilesUtilities.getFileExtension(file);
                byte[] imageBytes = FilesUtilities.convertFileToByteArray(file, extension);
                FileDomain imageFileDomain = new FileDomain(imageBytes, extension, file.getName());
                user.setUserPhoto(imageFileDomain);
            }
            Server.getInstance().addClient(phone, chatUpClient);

        }

        return user;
    }

    @Override
    public int signUp(User user) {
        if (userDao.getUserByPhone(user.getPhoneNumber()) != null) {
            return -2;
        }
        user.setUserPhotoPath(saveUserprofilePhoto(user));
        return userDao.insertUser(user);
    }

    @Override
    public void signout(String phoneNumber)  throws RemoteException{

       List<Contact> allContacts =contactDao.getContacts(phoneNumber);

        for (int i = 0; i <allContacts.size() ; i++) {
            String phone=allContacts.get(i).getContactPhoneNumber();
            if(onlineUsers.containsKey(phone)){
                onlineUsers.get(phone).notifiySignout(phoneNumber);
            }
        }
        Server.getInstance().removeClient(phoneNumber);

    }

    public String saveUserprofilePhoto(User user) {
        FileDomain userProfilePhoto = user.getUserPhoto();
        String photoPath;
        if (userProfilePhoto != null) {
            File file = new File("src/main/resources/UserPhotos/"+userProfilePhoto.getFilename() + "." + userProfilePhoto.getFileExtension());

            InputStream is = new ByteArrayInputStream(userProfilePhoto.getFileBytes());
            try {
                BufferedImage bufferedImage = ImageIO.read(is);
                ImageIO.write(bufferedImage, userProfilePhoto.getFileExtension(), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            photoPath = file.getPath();
        } else {
            //default photo
            photoPath = "src/main/resources/UserPhotos/user.jpg";
        }
        return photoPath;
    }
}
