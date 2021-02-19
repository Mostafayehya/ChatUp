package eg.gov.iti.jets.services.implementations;

import clientInterface.ChatUpClientInt;
import domains.User;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import eg.gov.iti.jets.io.Server;
import services.AuthenticationService;


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
        if(userDao.getUserByPhone(user.getPhoneNumber())!=null){
            return -2;
        }
        user.setUserPhotoPath(saveUserprofilePhoto(user));
        return userDao.insertUser(user);
    }

    public String saveUserprofilePhoto(User user) {
        FileDomain userProfilePhoto = user.getUserPhoto();
        String photoPath;
        if (userProfilePhoto != null) {
            Path target = Paths.get(getClass().getResource("/UserPhotos/"+userProfilePhoto.getFilename() + "." + userProfilePhoto.getFileExtension()) .getPath());

            InputStream is = new ByteArrayInputStream(userProfilePhoto.getFileBytes());
            try {
                BufferedImage bufferedImage = ImageIO.read(is);
                ImageIO.write(bufferedImage, userProfilePhoto.getFileExtension(), target.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            photoPath = target.toFile().getPath();
        } else {
            //default photo
            photoPath = "src/main/resources/UserPhotos/user.jpg";
        }
        return photoPath;
    }
}
