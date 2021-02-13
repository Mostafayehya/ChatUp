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
            Server.getInstance().addClient(phone,chatUpClient);
        }

        return user;
    }

    @Override
    public int signUp(User user) {
        if(userDao.getUserByPhone(user.getPhoneNumber())!=null){
            return -2;
        }
        return userDao.insertUser(user);
    }


}
