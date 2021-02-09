package eg.gov.iti.jets.io;

import eg.gov.iti.jets.services.interfaces.AuthenticationService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIManager {
    Registry reg;
    AuthenticationService authenticationService;
    private static RMIManager rmiManager;
    private RMIManager(){
        startRMIServices();
    }
    //todo: refactor looking up of services into individual methods
    public void startRMIServices() {
        try {
            reg = LocateRegistry.getRegistry("localhost",8189);
            authenticationService= (AuthenticationService) reg.lookup("AuthenticationService");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized static RMIManager getInstance(){
        if(rmiManager==null)
            rmiManager = new RMIManager();
        return rmiManager;
    }
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
