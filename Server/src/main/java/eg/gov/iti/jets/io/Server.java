package eg.gov.iti.jets.io;

import clientInterface.ChatUpClientInt;
import eg.gov.iti.jets.services.implementations.AuthenticationServiceImpl;
import eg.gov.iti.jets.services.implementations.UpdateServiceImpl;
import eg.gov.iti.jets.services.implementations.HandleContactServiceImpl;
import eg.gov.iti.jets.services.implementations.SingleChatServiceImpl;
import services.AuthenticationService;
import services.UpdateService;
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
    UpdateService updateService;
    HandleContactsService handleContactsService;
    SingleChatService singleChatService;
    //list of clients
    Map<String, ChatUpClientInt> clients;
    DataBaseConnection databaseConnection ;

    private Server(){
        clients = new HashMap<>();
        try {

            registry = LocateRegistry.createRegistry(8189);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public synchronized static Server getInstance() {
        if (server == null)
            server = new Server();
        return server;
    }

    public AuthenticationService getnewAuthService() {
        try {
            authenticationService = new AuthenticationServiceImpl();
            return authenticationService;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //use in login
    public void addClient(String phoneNumber,ChatUpClientInt clientImpl){
        clients.put(phoneNumber,clientImpl);
        System.out.println("User: " +phoneNumber+" was added to online ");

    }

    //use in signOut or exit
    public void removeClient(String phoneNumber){
        clients.remove(phoneNumber);
    }

    public void stopServer(){
        for (Map.Entry<String,ChatUpClientInt> entry : clients.entrySet()) {
            try {
                entry.getValue().closeApp();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        databaseConnection.closeConnection();
        try {
            registry.unbind("AuthenticationService");
            registry.unbind("HandleContactService");
            registry.unbind("SingleChatService");
            registry.unbind("UpdateService");
            UnicastRemoteObject.unexportObject(updateService,true);
            UnicastRemoteObject.unexportObject(authenticationService,true);
            UnicastRemoteObject.unexportObject(handleContactsService,true);
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

    public void startServer() {
        clients = new HashMap<>();
        databaseConnection = DataBaseConnection.getInstance();
        try {
            authenticationService  = new AuthenticationServiceImpl();
            handleContactsService = new HandleContactServiceImpl();
            updateService=new UpdateServiceImpl();
            registry = LocateRegistry.getRegistry(8189);
            registry.bind("SingleChatService", singleChatService);
            registry.bind("AuthenticationService",authenticationService);
            registry.bind("HandleContactService",handleContactsService);
            registry.bind("UpdateService",updateService);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        catch(AlreadyBoundException e){
            e.printStackTrace();
        }
    }
}
