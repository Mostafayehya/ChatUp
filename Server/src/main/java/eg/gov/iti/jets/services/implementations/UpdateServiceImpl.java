package eg.gov.iti.jets.services.implementations;

import domains.FileDomain;
import domains.User;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import services.UpdateService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UpdateServiceImpl extends UnicastRemoteObject implements UpdateService {
    UserDao userDao;
    public UpdateServiceImpl() throws RemoteException {
        userDao = new UserDaoImpl();
    }
    @Override
    public int EditUserData(User user) throws RemoteException {
        return userDao.updateUserData(user);
    }


    @Override
    public int EditUserMode(User user) throws RemoteException {
        return userDao.updateUserMode(user);
    }

    @Override
    public int EditUserPass(User user) throws RemoteException {
        System.out.println("EditUserPass");
        return  userDao.updateUserPass(user);
    }

    @Override
    public int EditUserPhoto(User user) throws RemoteException {
        user.setUserPhotoPath(saveUserprofilePhoto(user));
        return userDao.updateUserPhoto(user);
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
