package eg.gov.iti.jets.io;

import eg.gov.iti.jets.services.implementations.AuthenticationServiceImpl;
import services.AuthenticationService;


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
    private Server(){
        try {
            authenticationService  = new AuthenticationServiceImpl();

            registry = LocateRegistry.createRegistry(8189);
            registry.bind("AuthenticationService",authenticationService);


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
            UnicastRemoteObject.unexportObject(authenticationService,true);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
