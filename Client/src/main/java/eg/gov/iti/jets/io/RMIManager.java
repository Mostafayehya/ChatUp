package eg.gov.iti.jets.io;

import services.AuthenticationService;
import services.UpdateService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIManager extends UnicastRemoteObject {
    Registry reg;
    private static AuthenticationService authenticationService;
    private static UpdateService updateService;
    private static RMIManager rmiManager;
    private RMIManager() throws RemoteException{
        startRMIServices();
    }
    //todo: refactor looking up of services into individual methods
    public void startRMIServices() {
        try {
            reg = LocateRegistry.getRegistry("localhost",8189);
            authenticationService= (AuthenticationService) reg.lookup("AuthenticationService");
            updateService=(UpdateService) reg.lookup("UpdateService") ;
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized static RMIManager getInstance(){
        if(rmiManager==null) {
            try {
                rmiManager = new RMIManager();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return rmiManager;
    }
    public static AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
    public static UpdateService getUpdateService(){return updateService;}

}
