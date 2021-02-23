package eg.gov.iti.jets.io;

import clientInterface.ClientCallbacks;
import services.AuthenticationService;
import services.HandleContactsService;
import services.SingleChatService;
import services.UpdateService;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class RMIManager extends UnicastRemoteObject {
    Registry reg;
    private static AuthenticationService authenticationService;
    private static UpdateService updateService;
    private static HandleContactsService handleContactsService;
    private static SingleChatService singleChatService;
    private static RMIManager rmiManager;
    private static ClientCallbacks clientCallbacks;

    private RMIManager() throws RemoteException {
        startRMIServices();
    }

    //todo: refactor looking up of services into individual methods
    public void startRMIServices() {
        try {
            //"192.168.8.101"
            reg = LocateRegistry.getRegistry("localhost", 8189);
            authenticationService = (AuthenticationService) reg.lookup("AuthenticationService");
            updateService = (UpdateService) reg.lookup("UpdateService");
            handleContactsService = (HandleContactsService) reg.lookup("HandleContactService");
            singleChatService = (SingleChatService) reg.lookup("SingleChatService");
            clientCallbacks = new ClientCallbacksImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void stopRMIService() {
        try {
            UnicastRemoteObject.unexportObject(clientCallbacks, true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    public synchronized static RMIManager getInstance() {
        if (rmiManager == null) {
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

    public static HandleContactsService getHandleContactsService() {
        return handleContactsService;
    }

    public static SingleChatService getSingleChatService() {
        return singleChatService;
    }

    public static UpdateService getUpdateService() {
        return updateService;
    }

    public static ClientCallbacks getClientCallBack() {
        return clientCallbacks;
    }

}
