package eg.gov.iti.jets.services.interfaces;



import eg.gov.iti.jets.ui.models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationService extends Remote {
    User login(User user) throws RemoteException;
    int signUp(User user) throws RemoteException;
}
