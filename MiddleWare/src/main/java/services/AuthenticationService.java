package services;

import clientInterface.ChatUpClientInt;
import domains.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationService extends Remote {
    User login(String phone , String Password, ChatUpClientInt chatUpClient) throws RemoteException;
    int signUp(User user) throws RemoteException;
}
