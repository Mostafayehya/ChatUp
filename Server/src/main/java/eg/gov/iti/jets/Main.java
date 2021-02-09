package eg.gov.iti.jets;

import eg.gov.iti.jets.data.DataBaseConnection;
import eg.gov.iti.jets.io.Server;
import eg.gov.iti.jets.ui.StageCoordinator;
import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {
    DataBaseConnection dataBaseConnection;
    Server server;
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
        dataBaseConnection = DataBaseConnection.getInstance();
        server = Server.getInstance();
    }

    @Override
    public void stop() {
        // Terminate Database & Network Connections
        dataBaseConnection.closeConncetion();
        server.stopServer();
    }

}
