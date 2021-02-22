package eg.gov.iti.jets.utilities;

import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;

import java.sql.ResultSet;

public class statistics {
    public PieChart mypiechart = new PieChart();
    public PieChart mypiechart2 = new PieChart();
    XYChart.Series dataSeries1 = new XYChart.Series();
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    public ObservableList<PieChart.Data> gender;
    public ObservableList<PieChart.Data> onoff;
    public BarChart mychart = new BarChart(xAxis, yAxis);
    UserDao d = new UserDaoImpl();
    public ResultSet rs1 = d.getAllByCountry();
    public ResultSet rs2 = d.getAllByGender();
    public ResultSet rs3 = d.getAllOnOff();

    public void getgenderdata() {
        Tooltip t = new Tooltip();

        mypiechart.getData().clear();
        int count[] = {0, 0};
        int i = 0;
        String gen[] = {null, null};
        try {
            while (rs2.next()) {

                count[i] = rs2.getInt("Number");
                gen[i] = (rs2.getObject("gender")).toString();
                i++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PieChart.Data data0 = new PieChart.Data(gen[0], count[0]);
        gender = FXCollections.observableArrayList(data0);
        Tooltip tooltip0 = new Tooltip(count[0]+"Users");
        Tooltip.install(data0.getNode(), tooltip0);

        if (count[0] != 0) {
            PieChart.Data data = new PieChart.Data(gen[1], count[1]);
            gender.add(data);
            Tooltip tooltip = new Tooltip(count[1]+"Users");
            Tooltip.install(data.getNode(), tooltip);

        }
        mypiechart.setData(gender);

    }

    public void getstatusdata() {
        mypiechart2.getData().clear();
        int count[] = {0, 0};
        int i = 0;
        String st[] = {null, null};
        try {
            while (rs3.next()) {

                count[i] = rs3.getInt("Number");
                st[i] = (rs3.getObject("status")).toString();
                i++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PieChart.Data data0 = new PieChart.Data(st[0], count[0]);
        onoff = FXCollections.observableArrayList(data0);
        Tooltip tooltip0 = new Tooltip(count[0]+"Users");
        Tooltip.install(data0.getNode(), tooltip0);

        if (count[0] != 0) {
            PieChart.Data data = new PieChart.Data(st[1], count[1]);
            onoff.add(data);
            Tooltip tooltip = new Tooltip(count[1]+"Users");
            Tooltip.install(data.getNode(), tooltip);
        }
        mypiechart2.setData(onoff);

    }
    public void getcountrydata() {
        dataSeries1.getData().clear();
        try {
            while (rs1.next()) {

                dataSeries1.getData().add(new XYChart.Data<>(rs1.getString("country"), rs1.getInt("Number")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mychart.getData().clear();
        mychart.getData().add(dataSeries1);
    }


}

