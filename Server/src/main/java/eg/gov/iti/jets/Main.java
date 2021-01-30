package eg.gov.iti.jets;

import eg.gov.iti.jets.utilities.StageCoordinator;
import javafx.application.Application;
<<<<<<< HEAD
import javafx.scene.Scene;
=======
>>>>>>> master
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StageCoordinator stageCoordinator=StageCoordinator.getInstance();
        stageCoordinator.setStage(primaryStage);
        //Call first page function
        primaryStage.show();
    }

    @Override
    public void init() {
        // Initialize Database & Network Connections
    }

    @Override
    public void stop() {
        // Terminate Database & Network Connections
    }

}
