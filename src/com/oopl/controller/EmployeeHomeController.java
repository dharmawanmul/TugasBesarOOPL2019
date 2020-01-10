package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.oopl.dao.TicketDaoImpl;
import com.oopl.entity.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class EmployeeHomeController implements Initializable {
    private LoginEmployeeController controller;
    @FXML
    private LineChart<String, Integer> lineChart;
    @FXML
    private CategoryAxis C;
    @FXML
    private NumberAxis N;
    @FXML
    private Label chartTitle;
    private String[] vehicleData = new String[3];
    private String[] days = new String[7];
    private TicketDaoImpl ticketDao = new TicketDaoImpl();
    @FXML
    private JFXButton btnFilter;
    @FXML
    private ComboBox<String> cmbxFltr;
    private ObservableList<String> year;
    private ObservableList<String> vehicles;
    private ObservableList<String> filterchart;
    @FXML
    private ComboBox<String> cmbxFnce;
    @FXML
    private ComboBox<String> cmbxAsal;

    public ObservableList<String> getYear() {
        if(year == null){
            year = FXCollections.observableArrayList();
            year.addAll(ticketDao.getAllYear());
        }
        return year;
    }

    public ObservableList<String> getVehicle() {
        if(vehicles == null){
            vehicles = FXCollections.observableArrayList();
            vehicles.add("Motor");
            vehicles.add("Mobil");
        }
        return vehicles;
    }

    public ObservableList<String> getFilter() {
        if(filterchart == null){
            filterchart = FXCollections.observableArrayList();
            filterchart.add("Profit");
            filterchart.add("Count");
        }
        return filterchart;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        generateChart();

        cmbxFltr.setItems(getYear());
        cmbxFnce.setItems(getVehicle());
        cmbxAsal.setItems(getFilter());

//        series.setNode(new HoveredThresholdNode(15,15));
//
//        series2.getData().add(new XYChart.Data<String, Integer>("Monday", 432));
//        series2.getData().add(new XYChart.Data<String, Integer>("Tuesday", 132));
//        series2.getData().add(new XYChart.Data<String, Integer>("Wednesday", 441));
//        series2.getData().add(new XYChart.Data<String, Integer>("Thursday", 426));
//        series2.getData().add(new XYChart.Data<String, Integer>("Friday", 622));
//        series2.getData().add(new XYChart.Data<String, Integer>("Saturday", 674));
//        series2.getData().add(new XYChart.Data<String, Integer>("Sunday", 734));
//        series2.setNode(new HoveredThresholdNode(15,15));

//        S.setName("Chart 1");
//
//        S.getData().add(new XYChart.Data<>("1", 300));
//        S.getData().add(new XYChart.Data<>("2", 125));
//        S.getData().add(new XYChart.Data<>("3", 293));
//        S.getData().add(new XYChart.Data<>("4", 128));
//        S.getData().add(new XYChart.Data<>("5", 223));
//        S.getData().add(new XYChart.Data<>("6", 111));
//        S.getData().add(new XYChart.Data<>("7", 194));
//

//        lineChart.getData().setAll(series2);
    }

    @FXML
    private void filterAct(ActionEvent actionEvent) {
        XYChart.Series series = new XYChart.Series<>();
        String tahun = cmbxFltr.getSelectionModel().getSelectedItem();
        tahun = tahun.substring(tahun.length()-2);
        String vehicle = cmbxFnce.getSelectionModel().getSelectedItem();
        int vehicleId = vehicle.equals("Mobil") ? 1 : 2;
        String asal = cmbxAsal.getSelectionModel().getSelectedItem();
        if (asal.equals("Count")) {
            series.setName("Numbers of Vehicle");
            series.getData().add(new XYChart.Data<String, Integer>("Jan", ticketDao.getVehicleNum("01"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Feb", ticketDao.getVehicleNum("02"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Mar", ticketDao.getVehicleNum("03"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Apr", ticketDao.getVehicleNum("04"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("May", ticketDao.getVehicleNum("05"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("June", ticketDao.getVehicleNum("06"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("July", ticketDao.getVehicleNum("07"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Aug", ticketDao.getVehicleNum("08"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Sept", ticketDao.getVehicleNum("09"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Oct", ticketDao.getVehicleNum("10"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Nov", ticketDao.getVehicleNum("11"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Dec", ticketDao.getVehicleNum("12"+tahun,vehicleId)));
        } else if (asal.equals("Profit")) {
            series.setName("Profits");
            series.getData().add(new XYChart.Data<String, Integer>("Jan", ticketDao.getProfit("01"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Feb", ticketDao.getProfit("02"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Mar", ticketDao.getProfit("03"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Apr", ticketDao.getProfit("04"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("May", ticketDao.getProfit("05"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("June", ticketDao.getProfit("06"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("July", ticketDao.getProfit("07"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Aug", ticketDao.getProfit("08"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Sept", ticketDao.getProfit("09"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Oct", ticketDao.getProfit("10"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Nov", ticketDao.getProfit("11"+tahun,vehicleId)));
            series.getData().add(new XYChart.Data<String, Integer>("Dec", ticketDao.getProfit("12"+tahun,vehicleId)));
        }


        lineChart.getData().setAll(series);
    }


//    public void generateChart() {
//        int x = 1;
//        int y = 20;
//            XYChart.Series series = new XYChart.Series();
//            XYChart.Series series2 = new XYChart.Series();
//            XYChart.Series series3 = new XYChart.Series();
//            series.setName("Cars");
//            series2.setName("Motorcycles");
//            series3.setName("Both");
//            for (int j = 0; j < 13; j++) {
//                final XYChart.Data<String, Integer> data = new XYChart.Data<String, Integer>(String.valueOf(days), 20); //yValue replace
//                final XYChart.Data<String, Integer> data2 = new XYChart.Data<String, Integer>(String.valueOf(days), 30);
//                final XYChart.Data<String, Integer> data3 = new XYChart.Data<>(String.valueOf(x), 50);
//                data.setNode(new HoveredThresholdNode(x, y));
//                data2.setNode(new HoveredThresholdNode(x, y));
//                y+=12;
//                x+=1;
//                series.getData().add(data);
//                series2.getData().add(data2);
//            }
//        lineChart.getData().add(series);
//        lineChart.getData().add(series2);
//    }

//    public ObservableList<XYChart.Data<Integer, Integer>> plot(int... y) {
//        final ObservableList<XYChart.Data<Integer, Integer>> dataset = FXCollections.observableArrayList();
//        int i = 0;
//        while (i < y.length) {
//            final XYChart.Data<Integer, Integer> data = new XYChart.Data<>(i + 1, y[i]);
//            data.setNode(
//                    new HoveredThresholdNode(15, 15
//                    )
//            );
//            dataset.add(data);
//            i++;
//        }
//        return dataset;
//    }

    class HoveredThresholdNode extends StackPane {
        HoveredThresholdNode(int priorValue, int value) {
            setPrefSize(15, 15);

            final Label label = createDataThresholdLabel(priorValue, value);

            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getChildren().setAll(label);
                    setCursor(Cursor.NONE);
                    toFront();
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getChildren().clear();
                    setCursor(Cursor.CROSSHAIR);
                }
            });
        }
    }

    private Label createDataThresholdLabel(int priorValue, int value) {
        final Label label = new Label(value + "");
        label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        if (priorValue == 0) {
            label.setTextFill(Color.DARKGRAY);
        } else if (value > priorValue) {
            label.setTextFill(Color.FORESTGREEN);
        } else {
            label.setTextFill(Color.FIREBRICK);
        }
        label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        return label;
    }
}
