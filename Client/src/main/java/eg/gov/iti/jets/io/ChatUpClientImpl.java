package eg.gov.iti.jets.io;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import clientInterface.ChatUpClientInt;

public class ChatUpClientImpl extends UnicastRemoteObject implements ChatUpClientInt {
    public ChatUpClientImpl() throws RemoteException {
    }
}
