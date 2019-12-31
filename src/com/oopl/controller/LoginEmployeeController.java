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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginEmployeeController {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private EmployeeDaoImpl employeeDao;
    private Employee worker;
//    private ObservableList<Employee> employees;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    private ParkingAttendantsController attendantsController;
    @FXML
    private void onClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

//    public ObservableList<Employee> getEmployees() {
//        if (employees == null) {
//            employees = FXCollections.observableArrayList();
//            employees.addAll(employeeDao.showAll());
//        }
//        return employees;
//    }
//
//    public EmployeeDaoImpl employeeDao() {
//        if (employeeDao == null) {
//            employeeDao = new EmployeeDaoImpl();
//        }
//        return employeeDao;
//    }

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
        switch (res.getEmployeeRole().getIdRole()) {
            case 1 : loadStage(1);
                break;
            case 2 : loadStage(2);
                break;
            case 3 :
                loadStage(3);
                break;
        }
    }

    private void loadStage(int param) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        switch (param) {
            case 1 : loader.setLocation(Main.class.getResource("view/EmployeePage.fxml"));
                break;
            case 2 : loader.setLocation(Main.class.getResource("view/ParkingManagerPage.fxml"));
                break;
            case 3 : loader.setLocation(Main.class.getResource("view/ParkingAttendants.fxml"));
                break;
        }
        loader.setRoot(null);
        Pane root = loader.load();

        Stage loginStage = (Stage) btnLogin.getScene().getWindow();
        loginStage.close();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("com/oopl/img/parking.png"));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

//    private void loadAttendantPage(Employee e) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(Main.class.getResource("view/ParkingAttendants.fxml"));
//        loader.setRoot(null);
//        Pane root = loader.load();
//        attendantsController.setEmployee(e);
//
//        Stage loginStage = (Stage) btnLogin.getScene().getWindow();
//        loginStage.close();
//
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.getIcons().add(new Image("com/oopl/img/parking.png"));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.show();
//    }
}
