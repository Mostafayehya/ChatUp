package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.Main;
import eg.gov.iti.jets.data.DataBaseConnection;
import eg.gov.iti.jets.io.Server;
import eg.gov.iti.jets.services.implementations.AuthenticationServiceImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import services.AuthenticationService;

import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class serverDashBoardController implements Initializable { //
    DataBaseConnection dataBaseConnection;
    Server server;
    Main m;
    ResourceBundle resourceBundle;
    URL url;
    //RMIManager w = new RMIManager();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseConnection = DataBaseConnection.getInstance();
        server = Server.getInstance();

    }

    @FXML
    public void startService() throws Exception{
        //w.startRMIServices();
        server.startServer();
        dataBaseConnection.closeConncetion();
        System.out.println("server started");
    }

    @FXML
    public void stopService() {
        server.stopServer();
        dataBaseConnection.closeConncetion();
        //server.stopServer();
        System.out.println("server has stopped");
    }


}



