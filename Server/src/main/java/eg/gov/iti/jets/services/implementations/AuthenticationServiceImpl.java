package eg.gov.iti.jets.services.implementations;

import eg.gov.iti.jets.domain.User;
import eg.gov.iti.jets.services.interfaces.AuthenticationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService {
    protected AuthenticationServiceImpl() throws RemoteException {
    }

    @Override
    public User login(User user) throws RemoteException {
        return null;
    }

    @Override
    public boolean signUp(User user) throws RemoteException {
        return false;
    }
}
