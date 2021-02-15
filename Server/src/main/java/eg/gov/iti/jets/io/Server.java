package eg.gov.iti.jets.io;

import clientInterface.ChatUpClientInt;
import eg.gov.iti.jets.services.implementations.AuthenticationServiceImpl;
import eg.gov.iti.jets.services.implementations.UpdateServiceImpl;
import eg.gov.iti.jets.services.implementations.HandleContactServiceImpl;
import services.AuthenticationService;
import services.UpdateService;
import services.HandleContactsService;


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
    //list of clients
    Map<String, ChatUpClientInt> clients;
    private Server(){
        clients = new HashMap<>();
        try {
            authenticationService  = new AuthenticationServiceImpl();
            updateService=new UpdateServiceImpl();
            handleContactsService = new HandleContactServiceImpl();

            registry = LocateRegistry.createRegistry(8189);
            registry.bind("AuthenticationService",authenticationService);
            registry.bind("UpdateService",updateService);

            registry.bind("HandleContactService",handleContactsService);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        catch(AlreadyBoundException e){
            e.printStackTrace();
        }
    }

    public synchronized static Server getInstance(){
        if(server==null)
            server=new Server();
        return server;
    }
    public void addClient(String phoneNumber,ChatUpClientInt clientImpl){
        clients.put(phoneNumber,clientImpl);
    }

    //use in signOut or exit
    public void removeClient(String phoneNumber){
        clients.remove(phoneNumber);
    }

    public void stopServer(){
        try {
            registry.unbind("AuthenticationService");
            registry.unbind("HandleContactService");
            UnicastRemoteObject.unexportObject(authenticationService,true);
            UnicastRemoteObject.unexportObject(handleContactsService,true);
            registry.unbind("UpdateService");
            UnicastRemoteObject.unexportObject(updateService,true);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
