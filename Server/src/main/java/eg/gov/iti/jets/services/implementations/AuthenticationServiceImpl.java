package eg.gov.iti.jets.services.implementations;

import domains.User;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import services.AuthenticationService;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService {
    UserDao userDao;
    public AuthenticationServiceImpl() throws RemoteException {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String phone , String password) {
        return userDao.getUserByPhoneAndPassword(phone, password);
    }

    @Override
    public int signUp(User user) {
         return userDao.insertUser(user);
    }
}
