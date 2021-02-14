package clientInterface;

import domains.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallbacks extends Remote {
    //interface for functions in client that can be called from server

    void receiveMessage(Message message) throws RemoteException;
}
