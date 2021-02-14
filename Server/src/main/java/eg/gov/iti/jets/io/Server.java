package eg.gov.iti.jets.io;

import clientInterface.ClientCallbacks;
import eg.gov.iti.jets.services.implementations.AuthenticationServiceImpl;
import eg.gov.iti.jets.services.implementations.HandleContactServiceImpl;
import eg.gov.iti.jets.services.implementations.SingleChatServiceImpl;
import services.AuthenticationService;
import services.HandleContactsService;
import services.SingleChatService;


import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Server server;
    Registry registry;
    AuthenticationService authenticationService;
    HandleContactsService handleContactsService;
    SingleChatService singleChatService;
    //list of clients
    static Map<String, ClientCallbacks> onlineClients;

    private Server() {
        onlineClients = new HashMap<>();
        try {
            authenticationService = new AuthenticationServiceImpl();
            handleContactsService = new HandleContactServiceImpl();
            singleChatService = new SingleChatServiceImpl();

            registry = LocateRegistry.createRegistry(8189);
            registry.bind("AuthenticationService", authenticationService);
            registry.bind("HandleContactService", handleContactsService);
            registry.bind("SingleChatService", singleChatService);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized static Server getInstance() {
        if (server == null)
            server = new Server();
        return server;
    }

    //use in login
    public void addClient(String phoneNumber, ClientCallbacks clientImpl) {
        onlineClients.put(phoneNumber, clientImpl);
        System.out.println("User: " +phoneNumber+" was added to online ");
    }

    //use in signOut or exit
    public void removeClient(String phoneNumber) {
        onlineClients.remove(phoneNumber);
    }

    public void stopServer() {
        try {
            registry.unbind("AuthenticationService");
            registry.unbind("HandleContactService");
            registry.unbind("SingleChatService");
            UnicastRemoteObject.unexportObject(authenticationService, true);
            UnicastRemoteObject.unexportObject(handleContactsService, true);
            UnicastRemoteObject.unexportObject(singleChatService, true);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, ClientCallbacks> getOnlineClients() {
        return onlineClients;
    }
}
