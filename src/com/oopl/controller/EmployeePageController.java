package com.oopl.controller;

import com.oopl.Main;
import com.oopl.dao.EmployeeDaoImpl;
import com.oopl.dao.RoleDaoImpl;
import com.oopl.entity.Employee;
import com.oopl.entity.Role;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeePageController implements Initializable {
    @FXML
    private Pane contentArea;
    @FXML
    private Label labelName;
    private ObservableList<Role> roles;
//    private ObservableList<Vehicletype> vehicles;
    private ObservableList<Employee> employees;
    private RoleDaoImpl roleDao;
//    private VehicletypeDaoImpl vehicleDao;
    private EmployeeDaoImpl employeeDao;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private CategoryAxis C;
    @FXML
    private NumberAxis N;

    public ObservableList<Role> getRole() {
        if (roles == null) {
            roles = FXCollections.observableArrayList();
            roles.addAll(getRoleDao().showAll());
        }
        return roles;
    }

    public RoleDaoImpl getRoleDao() {
        if (roleDao == null) {
            roleDao = new RoleDaoImpl();
        }
        return roleDao;
    }

//    public ObservableList<Vehicletype> getVehicles() {
//        if (vehicles == null) {
//            vehicles = FXCollections.observableArrayList();
//            vehicles.addAll(getVehicleDao().showAll());
//        }
//        return vehicles;
//    }
//
//    public VehicletypeDaoImpl getVehicleDao() {
//        if (vehicleDao == null) {
//            vehicleDao = new VehicletypeDaoImpl();
//        }
//        return vehicleDao;
//    }

    public ObservableList<Employee> getEmployees() {
        if (employees == null) {
            employees = FXCollections.observableArrayList();
            employees.addAll(getEmployeeDao().showAll());
        }
        return employees;
    }

    public EmployeeDaoImpl getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    @FXML
    private void openHome(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/overviewPage.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void openUserManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/EmployeeManagement.fxml"));
        Parent node = loader.load();
        EmployeeManagementController pageController = loader.getController();
        pageController.setMainFormController(this);
//        node.setUserData(fxmlLocation);
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(node);
//        node.requestFocus();
    }

    @FXML
    private void openReport(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("view/reportManagement.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void onLogout(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contentArea.getScene().getWindow();
        stage.close();
        Parent root = null;
        Stage primaryStage = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource("../view/LoginEmployee.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("com/oopl/img/parking.png"));
    }

    @FXML
    private void exitApp(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series S = new XYChart.Series<>();
        S.setName("Chart 1");

        S.getData().add(new XYChart.Data<>("1", 300));
        S.getData().add(new XYChart.Data<>("2", 125));
        S.getData().add(new XYChart.Data<>("3", 293));
        S.getData().add(new XYChart.Data<>("4", 128));
        S.getData().add(new XYChart.Data<>("5", 223));
        S.getData().add(new XYChart.Data<>("6", 111));
        S.getData().add(new XYChart.Data<>("7", 194));

        lineChart.getData().setAll(S);
    }
}
