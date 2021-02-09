package eg.gov.iti.jets;

import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.application.Application;
import javafx.stage.Stage;

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
        stageCoordinator.goToLoginPage();
        primaryStage.setTitle("ChatUp - Client");
        primaryStage.show();
    }

    @Override
    public void init() {
        // Initialize Database & Network Connections
        //rmiManager.startRMIServices();
    }

    @Override
    public void stop() {
        // Terminate Database & Network Connections
    }

}
