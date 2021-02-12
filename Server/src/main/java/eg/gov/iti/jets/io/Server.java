package eg.gov.iti.jets.io;

import eg.gov.iti.jets.services.implementations.AuthenticationServiceImpl;
import eg.gov.iti.jets.services.implementations.HandleContactServiceImpl;
import services.AuthenticationService;
import services.HandleContactsService;


import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private static Server server;
    Registry registry;
    AuthenticationService authenticationService;
    HandleContactsService handleContactsService;
    private Server(){
        try {
            authenticationService  = new AuthenticationServiceImpl();
            handleContactsService = new HandleContactServiceImpl();

            registry = LocateRegistry.createRegistry(8189);
            registry.bind("AuthenticationService",authenticationService);
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

    public void stopServer(){
        try {
            registry.unbind("AuthenticationService");
            registry.unbind("HandleContactService");
            UnicastRemoteObject.unexportObject(authenticationService,true);
            UnicastRemoteObject.unexportObject(handleContactsService,true);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
