package eg.gov.iti.jets.io;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import clientInterface.ClientCallbacks;
import domains.Message;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.application.Platform;
import javafx.scene.control.Alert;


public class ClientCallbacksImpl extends UnicastRemoteObject implements ClientCallbacks {
    public ClientCallbacksImpl() throws RemoteException {
    }

    @Override
    public void receiveMessage(Message message) throws RemoteException {
        System.out.println("Message recevied back from server and should be deliverd to" + message.getReceiverPhoneNumber());
        ModelsFactory.getInstance().receiveMessage(message);
    }
    @Override
    public void closeApp() throws RemoteException {
        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.WARNING,"Server is stopped so the application must close");
            alert.show();
            stageCoordinator.closeApp();
        });

    }
}
