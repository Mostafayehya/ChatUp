package eg.gov.iti.jets.services.implementations;

import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import eg.gov.iti.jets.domain.User;
import eg.gov.iti.jets.services.interfaces.AuthenticationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService {
    UserDao userDao;
    public AuthenticationServiceImpl() throws RemoteException {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(User user) throws RemoteException {
        return null;
    }

    @Override
    public int signUp(User user) throws RemoteException {
         return userDao.insertUser(user);
    }
}
