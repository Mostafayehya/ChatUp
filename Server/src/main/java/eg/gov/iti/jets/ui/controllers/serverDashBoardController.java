package eg.gov.iti.jets.ui.controllers;

import eg.gov.iti.jets.Main;
import eg.gov.iti.jets.data.DataBaseConnection;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
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
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class serverDashBoardController implements Initializable { //
    DataBaseConnection dataBaseConnection;
    Server server;
    UserDao d = new UserDaoImpl();
    @FXML
    private Button startbutton, stopbutton;
    private PieChart mypiechart = new PieChart();
    private PieChart mypiechart2 = new PieChart();
    XYChart.Series dataSeries1 = new XYChart.Series();
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    ObservableList<PieChart.Data> gender;
    ObservableList<PieChart.Data> onoff;
    private BarChart mychart = new BarChart(xAxis, yAxis);
    @FXML
    private Pane mychartpane;

    ResultSet rs1 = d.getAllByCountry();
    ResultSet rs2 = d.getAllByGender();
    ResultSet rs3 = d.getAllOnOff();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseConnection = DataBaseConnection.getInstance();
        server = Server.getInstance();
        getcountrydata();
        getgenderdata();
        getstatusdata();


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
        dataBaseConnection.closeConnection();
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
        dataBaseConnection.closeConnection();
        //server.stopServer();
        System.out.println("server has stopped");
    }

    public void showGender() {
       // getgenderdata();

        try {
            mychartpane.getChildren().clear();
        } catch (Exception e) {
        }

        mypiechart.setPrefWidth(300);
        mypiechart.setPrefHeight(400);
        mychartpane.getChildren().add(mypiechart);
    }

    public void showCountries() {
       // getcountrydata();
        try {
            mychartpane.getChildren().clear();
        } catch (Exception e) {
        }
        mychart.setPrefWidth(300);
        mychart.setPrefHeight(400);
        mychartpane.getChildren().add(mychart);
    }

    public void showOnOff() {
        //getstatusdata();
        try {
            mychartpane.getChildren().clear();
        } catch (Exception e) {
        }
        mypiechart2.setPrefWidth(300);
        mypiechart2.setPrefHeight(400);
        mychartpane.getChildren().add(mypiechart2);
    }

    private void getcountrydata() {

        try {
            while (rs1.next()) {
                dataSeries1.getData().add(new XYChart.Data<>(rs1.getString("country"), rs1.getInt("Number")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mychart.getData().add(dataSeries1);
    }

    private void getgenderdata() {

        int countmale = 0;
        int countfemale = 0;
        try {
            rs2.next();
            countmale = rs2.getInt("Number");
            rs2.next();
            countfemale = rs2.getInt("Number");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gender = FXCollections.observableArrayList(

                new PieChart.Data("Female", countmale),
                new PieChart.Data("Male", countfemale));

        mypiechart.setData(gender);

    }

    private void getstatusdata() {

        int counton = 0;
        int countoff = 0;
        try {
            rs3.next();
            countoff = rs3.getInt("Number");
            rs3.next();
            counton = rs3.getInt("Number");

        } catch (Exception e) {
            e.printStackTrace();
        }
        onoff = FXCollections.observableArrayList(

                new PieChart.Data("Offline", countoff),
                new PieChart.Data("Online", counton));

        mypiechart2.setData(onoff);

    }

}



