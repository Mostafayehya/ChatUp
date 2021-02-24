package eg.gov.iti.jets.ui.controllers;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import domains.Status;
import eg.gov.iti.jets.Main;
import eg.gov.iti.jets.data.DataBaseConnection;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import eg.gov.iti.jets.io.Server;
import eg.gov.iti.jets.services.implementations.AuthenticationServiceImpl;
import eg.gov.iti.jets.utilities.statistics;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.AuthenticationService;

import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class serverDashBoardController implements Initializable {
    DataBaseConnection dataBaseConnection;
    Server server;

    @FXML
    private Button startbutton, stopbutton;
    @FXML
    private Pane mychartpane;
    statistics statistics = new statistics();
    ResultSet rs;
    UserDaoImpl d;
    @FXML
    private ListView userslist;
    ArrayList<String> names;
    ObservableList<String> usersOlist;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseConnection = DataBaseConnection.getInstance();
        server = Server.getInstance();
        statistics.getcountrydata();
        statistics.getgenderdata();
        statistics.getstatusdata();
        mychartpane.setPrefHeight(500);
        mychartpane.setPrefWidth(400);
        stopbutton.setDisable(true);
        fillUserList();
    }

    @FXML
    public void startService() throws Exception {
        //w.startRMIServices();
        try {
            startbutton.setDisable(true);
            stopbutton.setDisable(false);
        } catch (Exception e) {
        }
        server.startServer();
        //dataBaseConnection.closeConnection();
        System.out.println("server started");
    }

    @FXML
    public void stopService() {
        try {
            startbutton.setDisable(false);
            stopbutton.setDisable(true);
        } catch (Exception e) {
        }
        server.stopServer();
        //dataBaseConnection.closeConnection();
        //server.stopServer();
        System.out.println("server has stopped");
    }

    public void showGender() {
        /*try {
            statistics.rs2.first();
            statistics.rs2.previous();
        } catch (Exception e) {
        }
        statistics.getgenderdata();*/
        try {
            mychartpane.getChildren().clear();
        } catch (Exception e) {
        }

        statistics.mypiechart.setPrefWidth(300);
        statistics.mypiechart.setPrefHeight(400);
        mychartpane.getChildren().add(statistics.mypiechart);
    }

    public void showCountries() {

       /* try {
            statistics.rs1.first();
            statistics.rs1.previous();
        } catch (Exception e) {
        }
        statistics.getcountrydata();*/
        try {
            mychartpane.getChildren().clear();
        } catch (Exception e) {
        }
        statistics.mychart.setPrefWidth(300);
        statistics.mychart.setPrefHeight(400);
        mychartpane.getChildren().add(statistics.mychart);
    }

    public void showOnOff() {
        /*try {
            statistics.rs3.first();
            statistics.rs3.previous();
        } catch (Exception e) {
        }
        statistics.getstatusdata();*/
        try {
            mychartpane.getChildren().clear();
        } catch (Exception e) {
        }
        statistics.mypiechart2.setPrefWidth(300);
        statistics.mypiechart2.setPrefHeight(400);
        mychartpane.getChildren().add(statistics.mypiechart2);
    }

    void fillUserList() {
       /* int i = 0;
        try {
            while (rs.next()) {

                names.add(rs.getString("name"));
                System.out.println(names.get(i));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //usersOlist = FXCollections.observableArrayList(names);
        // rs.getString("phoneNumber");
        //rs = d.getAllUsers();
        //userslist.setItems(usersOlist);
*/

    }

}



