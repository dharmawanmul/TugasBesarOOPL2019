package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.oopl.Main;
import com.oopl.dao.EmployeeDaoImpl;
import com.oopl.entity.Employee;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginEmployeeController {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private EmployeeDaoImpl employeeDao;
    private ObservableList<Employee> employees;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;

    @FXML
    private void onClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public ObservableList<Employee> getEmployees() {
        if (employees == null) {
            employees = FXCollections.observableArrayList();
            employees.addAll(employeeDao.showAll());
        }
        return employees;
    }

    public EmployeeDaoImpl employeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    @FXML
    private void loginClicked(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        login();
    }

    @FXML
    private void keyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    public void login() throws IOException {
        Employee e = new Employee();
        e.setUsername(txtUsername.getText());
        e.setPassword(txtPassword.getText());

        employeeDao = new EmployeeDaoImpl();
        Employee res = employeeDao.loginEmployee(e);
        FXMLLoader loader = new FXMLLoader();
        if (res.getRoleByRoleIdRole().getIdRole() == 1) {
            loader.setLocation(Main.class.getResource("view/EmployeePage.fxml"));
            loader.setRoot(null);
            Pane root = loader.load();

            Stage loginStage = (Stage) btnLogin.getScene().getWindow();
            loginStage.close();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } else if (res.getRoleByRoleIdRole().getIdRole() == 2){
            loader.setLocation(Main.class.getResource("view/ParkingManagerPage.fxml"));
            loader.setRoot(null);
            Pane root = loader.load();

            Stage loginStage = (Stage) btnLogin.getScene().getWindow();
            loginStage.close();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } else if (res.getRoleByRoleIdRole().getIdRole() == 3) {
            loader.setLocation(Main.class.getResource("view/ParkingAttendants.fxml"));
            loader.setRoot(null);
            Pane root = loader.load();

            Stage loginStage = (Stage) btnLogin.getScene().getWindow();
            loginStage.close();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        }
    }
}
