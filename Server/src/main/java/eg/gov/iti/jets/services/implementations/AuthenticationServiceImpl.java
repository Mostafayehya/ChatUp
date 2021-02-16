package eg.gov.iti.jets.services.implementations;

import clientInterface.ChatUpClientInt;
import domains.FileDomain;
import domains.User;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import eg.gov.iti.jets.io.Server;
import services.AuthenticationService;
import utilities.FilesUtilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService {
    UserDao userDao;
    public AuthenticationServiceImpl() throws RemoteException {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String phone , String password, ChatUpClientInt chatUpClient) {
        User user =  userDao.getUserByPhoneAndPassword(phone, password);
        if(user!=null){
            //add chatUpClient to the clients map in server
            Server.getInstance().addClient(phone,chatUpClient);
        }

        return user;
    }

    @Override
    public int signUp(User user) {
        if(userDao.getUserByPhone(user.getPhoneNumber())!=null){
            return -2;
        }
        FileDomain userProfilePhoto = user.getUserPhoto();
        Path target = Paths.get("C:\\Users\\Hadeer\\Desktop\\javaProject\\ChatUp\\Server\\src\\main\\resources\\UserPhotos\\"+userProfilePhoto.getFilename()+"."+userProfilePhoto.getFileExtension());

        InputStream is = new ByteArrayInputStream(userProfilePhoto.getFileBytes());
        try {
            BufferedImage bufferedImage = ImageIO.read(is);
            ImageIO.write(bufferedImage, userProfilePhoto.getFileExtension(), target.toFile());
            //Image image = new Image(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setUserPhotoPath(target.toFile().getPath());
        return userDao.insertUser(user);
    }


}
