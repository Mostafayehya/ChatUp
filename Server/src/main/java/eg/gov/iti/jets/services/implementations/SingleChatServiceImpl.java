package eg.gov.iti.jets.services.implementations;

import clientInterface.ClientCallbacks;
import domains.Message;
import eg.gov.iti.jets.io.Server;
import services.SingleChatService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class SingleChatServiceImpl extends UnicastRemoteObject implements SingleChatService {

    Map<String, ClientCallbacks> onlineUsers;

    public SingleChatServiceImpl() throws RemoteException {
        onlineUsers = Server.getOnlineClients();
    }

    @Override
    public void sendMessage(Message message) throws RemoteException {
        System.out.println("Message deliverd to chatService from " + message.getSenderPhoneNumber());
        ClientCallbacks client = onlineUsers.get(message.getReceiverPhoneNumber());
        if (client != null)
            client.receiveMessage(message);
    }
}
