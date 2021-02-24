package eg.gov.iti.jets.io;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import clientInterface.ClientCallbacks;
import domains.FileMessage;
import domains.Message;
import eg.gov.iti.jets.utilities.ModelsFactory;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


public class ClientCallbacksImpl extends UnicastRemoteObject implements ClientCallbacks {
    public ClientCallbacksImpl() throws RemoteException {
    }

    @Override
    public void receiveMessage(Message message) throws RemoteException {
        System.out.println("Message recevied back from server and should be deliverd to" + message.getReceiverPhoneNumber());
        Platform.runLater(()->{
            ModelsFactory.getInstance().receiveMessage(message);
        });

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

    @Override
    public void notifiySignout(String phoneNumber) {
        System.out.println("signout notification received in client side");

        ModelsFactory.getInstance().notifySignout(phoneNumber);
    }
}
