package clientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatUpClientInt extends Remote {
    //interface for functions in client that can be called from server
    void closeApp() throws RemoteException;
}
