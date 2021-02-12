package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.Main;
import eg.gov.iti.jets.data.DataBaseConnection;
import eg.gov.iti.jets.io.Server;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class serverDashBoardController {
    DataBaseConnection dataBaseConnection;
    Server server;
    Main m;

    @FXML
    public void startService() {
        Stage primaryStage = new Stage();
        try {
            m.start(primaryStage);
            m.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //dataBaseConnection = DataBaseConnection.getInstance();
        //server = Server.getInstance();
    }

    @FXML
    public void stopService() {
        try {
            dataBaseConnection.closeConncetion();
            server.stopServer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
