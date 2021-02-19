package eg.gov.iti.jets;

import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// todo) make the merge
public class Main extends Application {
    RMIManager rmiManager;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StageCoordinator stageCoordinator=StageCoordinator.getInstance();
        stageCoordinator.setStage(primaryStage);
        //Call first page function
        primaryStage.setTitle("ChatUp - Client");
        stageCoordinator.goToLoginPage();
        primaryStage.show();
    }

    @Override
    public void init() {
        // Initialize Database & Network Connections
        rmiManager=RMIManager.getInstance();
        rmiManager.startRMIServices();
    }

    @Override
    public void stop() {
        // Terminate Database & Network Connections
    }

}
