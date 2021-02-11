package services;

import domains.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationService extends Remote {
    User login(String phone , String Password) throws RemoteException;
    int signUp(User user) throws RemoteException;
}
