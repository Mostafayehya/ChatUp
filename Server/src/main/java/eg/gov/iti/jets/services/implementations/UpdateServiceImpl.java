package eg.gov.iti.jets.services.implementations;

import domains.User;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import services.UpdateService;

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
}
