package eg.gov.iti.jets.services.interfaces;

import eg.gov.iti.jets.domain.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationService extends Remote {
    User login(User user) throws RemoteException;
    boolean signUp(User user) throws RemoteException;
}
