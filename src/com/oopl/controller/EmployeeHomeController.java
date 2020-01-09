package com.oopl.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
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
    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    XYChart.Series<String, Integer> series2 = new XYChart.Series<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        LineChart lineChart = new LineChart(
//                new NumberAxis(), new NumberAxis(),
//                FXCollections.observableArrayList(
//                        new XYChart.Series(
//                                "Cars",
//                                FXCollections.observableArrayList(
//                                        plot(23, 14, 15, 24, 34, 36, 22, 45, 43, 17, 29, 25)
//                                )
//                        )
//                )
//        );
        generateChart();
        lineChart.setCursor(Cursor.CROSSHAIR);

        lineChart.setTitle("Stock Monitoring, 2013");

//        XYChart.Series S = new XYChart.Series<>();
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
//        lineChart.getData().setAll(S);
    }
//        series.getData().add(new XYChart.Data<String, Integer>("Monday", 125));
//        series.getData().add(new XYChart.Data<String, Integer>("Tuesday", 220));
//        series.getData().add(new XYChart.Data<String, Integer>("Wednesday", 240));
//        series.getData().add(new XYChart.Data<String, Integer>("Thursday", 205));
//        series.getData().add(new XYChart.Data<String, Integer>("Friday", 320));
//        series.getData().add(new XYChart.Data<String, Integer>("Saturday", 220));
//        series.getData().add(new XYChart.Data<String, Integer>("Sunday", 120));
//
//        series2.getData().add(new XYChart.Data<String, Integer>("Monday", 432));
//        series2.getData().add(new XYChart.Data<String, Integer>("Tuesday", 132));
//        series2.getData().add(new XYChart.Data<String, Integer>("Wednesday", 441));
//        series2.getData().add(new XYChart.Data<String, Integer>("Thursday", 426));
//        series2.getData().add(new XYChart.Data<String, Integer>("Friday", 622));
//        series2.getData().add(new XYChart.Data<String, Integer>("Saturday", 674));
//        series2.getData().add(new XYChart.Data<String, Integer>("Sunday", 734));

    private void addVehiclesData() {
        vehicleData[0] = "Cars";
        vehicleData[1] = "Bike";
        vehicleData[2] = "Both";
    }

//    private void addDateData() {
//        int min = 1;
//        for (int i = 0; i < 8; i++) {
//            date[i] = LocalDate.now().minusDays(min);
//            min += 1;
//        }
//    }

    public void generateChart() {
        int x = 1;
        int y = 20;
        addVehiclesData();
        for (int i = 0; i < 1 ; i++) {
            XYChart.Series series = new XYChart.Series();
            series.setName(vehicleData[0]);
            for (int j = 0; j < 13; j++) {
                final XYChart.Data<String, Integer> data = new XYChart.Data<>(String.valueOf(x), 100);
                data.setNode(new HoveredThresholdNode(5, y));
                series.getData().add(data);
                y+=20;
                x+=1;
            }
            lineChart.getData().add(series);
        }
    }

//    public ObservableList<XYChart.Data<Integer, Integer>> plot(int... y) {
//        final ObservableList<XYChart.Data<Integer, Integer>> dataset = FXCollections.observableArrayList();
//        int i = 0;
//        while (i < y.length) {
//            final XYChart.Data<Integer, Integer> data = new XYChart.Data<>(i + 1, y[i]);
//            data.setNode(
//                    new HoveredThresholdNode(
//                            (i == 0) ? 0 : y[i-1],
//                            y[i]
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
