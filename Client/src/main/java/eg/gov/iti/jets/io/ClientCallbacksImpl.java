package eg.gov.iti.jets.io;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import clientInterface.ClientCallbacks;
import domains.Message;
import eg.gov.iti.jets.utilities.ModelsFactory;

public class ClientCallbacksImpl extends UnicastRemoteObject implements ClientCallbacks {
    public ClientCallbacksImpl() throws RemoteException {
    }

    @Override
    public void receiveMessage(Message message) throws RemoteException {
        System.out.println("Message recevied back from server and should be deliverd to" + message.getReceiverPhoneNumber());
        ModelsFactory.getInstance().receiveMessage(message);
    }
}
