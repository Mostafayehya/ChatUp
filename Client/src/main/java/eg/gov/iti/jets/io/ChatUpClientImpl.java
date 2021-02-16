package eg.gov.iti.jets.io;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import clientInterface.ChatUpClientInt;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.scene.control.Alert;

public class ChatUpClientImpl extends UnicastRemoteObject implements ChatUpClientInt {
    public ChatUpClientImpl() throws RemoteException {
    }

    @Override
    public void closeApp() throws RemoteException {
        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
        Alert alert = new Alert(Alert.AlertType.WARNING,"Server is stopped so the application must close");
        stageCoordinator.closeApp();
    }
}
