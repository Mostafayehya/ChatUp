package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.Main;
import eg.gov.iti.jets.data.DataBaseConnection;
import eg.gov.iti.jets.io.Server;
import eg.gov.iti.jets.services.implementations.AuthenticationServiceImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
    @FXML
    private Button startbutton, stopbutton;
    private PieChart mypiechart = new PieChart();
    private PieChart mypiechart2 = new PieChart();
    XYChart.Series dataSeries1 = new XYChart.Series();
    XYChart.Series dataSeries2 = new XYChart.Series();
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    private BarChart mychart = new BarChart(xAxis, yAxis);
    @FXML
    private Pane mychartpane;
    //Dummy data
    int Countereg = 100000;
    int CounterTun = 5000;
    int CountSA = 30000;
    int CountSudan = 7000;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseConnection = DataBaseConnection.getInstance();
        server = Server.getInstance();

        //Dummy data
        dataSeries1.getData().add(new XYChart.Data<>("Egypt", Countereg));
        dataSeries1.getData().add(new XYChart.Data<>("Tunisia", CounterTun));
        dataSeries1.getData().add(new XYChart.Data<>("Saudi Arabia", CountSA));
        dataSeries1.getData().add(new XYChart.Data<>("Sudan", CountSudan));

        dataSeries2.getData().add(new XYChart.Data<>("Egypt", Countereg + 100000));
        dataSeries2.getData().add(new XYChart.Data<>("Tunisia", CounterTun + 2000));
        dataSeries2.getData().add(new XYChart.Data<>("Saudi Arabia", CountSA - 100));
        dataSeries2.getData().add(new XYChart.Data<>("Sudan", CountSudan + 500));

        mychart.getData().addAll(dataSeries1, dataSeries2);

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Female", 100000),
                new PieChart.Data("Male", 500000)

        );

        mypiechart.setData(data);


        ObservableList<PieChart.Data> onoff = FXCollections.observableArrayList(
                new PieChart.Data("Online", 100000),
                new PieChart.Data("Offline", 500000)

        );

        mypiechart2.setData(onoff);


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
        dataBaseConnection.closeConncetion();
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
        dataBaseConnection.closeConncetion();
        //server.stopServer();
        System.out.println("server has stopped");
    }

    public void showGender() {

        try {
            mychartpane.getChildren().clear();
        } catch (Exception e) {}

        mypiechart2.setPrefWidth(300);
        mypiechart2.setPrefHeight(400);
        mychartpane.getChildren().add(mypiechart2);
    }

    public void showCountries() {

        try {
            mychartpane.getChildren().clear();
        } catch (Exception e) {
        }
        mychart.setPrefWidth(300);
        mychart.setPrefHeight(400);
        mychartpane.getChildren().add(mychart);
    }

    public void showOnOff() {
        try {
            mychartpane.getChildren().clear();
        } catch (Exception e) {
        }
        mypiechart.setPrefWidth(300);
        mypiechart.setPrefHeight(400);
        mychartpane.getChildren().add(mypiechart);
    }

}



