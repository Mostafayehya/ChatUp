package services;

import clientInterface.ClientCallbacks;
import domains.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationService extends Remote {
    User login(String phone , String Password, ClientCallbacks chatUpClient) throws RemoteException;
    int signUp(User user) throws RemoteException;
    void signout(String phoneNumber) throws  RemoteException;
}
