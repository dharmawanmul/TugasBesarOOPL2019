package com.oopl.controller;

import com.oopl.dao.RoleDaoImpl;
import com.oopl.dao.UserDaoImpl;
import com.oopl.dao.VehicletypeDaoImpl;
import com.oopl.entity.Role;
import com.oopl.entity.User;
import com.oopl.entity.Vehicletype;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private ObservableList<Vehicletype> vehicles;

    private RoleDaoImpl roleDao;
    private VehicletypeDaoImpl vehicleDao;

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

    public ObservableList<Vehicletype> getVehicles() {
        if (vehicles == null) {
            vehicles = FXCollections.observableArrayList();
            vehicles.addAll(getVehicleDao().showAll());
        }
        return vehicles;
    }

    public VehicletypeDaoImpl getVehicleDao() {
        if (vehicleDao == null) {
            vehicleDao = new VehicletypeDaoImpl();
        }
        return vehicleDao;
    }

    @FXML
    private void openHome(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/overviewPage.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void openUserManagement(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/EmployeeManagement.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
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

    }


}
