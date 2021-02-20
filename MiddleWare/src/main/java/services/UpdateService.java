package services;

import domains.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpdateService extends Remote {
    int EditUserData(User user) throws RemoteException;
    int EditUserMode(User user) throws RemoteException;
    int EditUserPass(User user) throws RemoteException;
    int EditUserPhoto(User user) throws RemoteException;
}
