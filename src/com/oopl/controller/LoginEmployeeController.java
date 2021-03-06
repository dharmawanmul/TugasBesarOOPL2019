package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.oopl.Main;
import com.oopl.dao.EmployeeDaoImpl;
import com.oopl.entity.Employee;
import javafx.application.Platform;
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
import javafx.stage.StageStyle;

import java.io.*;
import java.sql.SQLException;

public class LoginEmployeeController {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private EmployeeDaoImpl employeeDao;
    private Employee temp;
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
        temp = employeeDao.loginEmployee(e);
        switch (temp.getRoleByRoleIdRole().getIdRole()) {
            case 1 : loadStage(1);
                break;
            case 2 : loadStage(2);
                break;
            case 3 : {
                loadParkingAttendantsStage(temp);
                break;
            }
        }
    }

    private void loadStage(int param) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        switch (param) {
            case 1 : loader.setLocation(Main.class.getResource("view/EmployeePage.fxml"));
            stage.setTitle("Admin Page");
            stage.setMaximized(true);
                break;
            case 2 : loader.setLocation(Main.class.getResource("view/ParkingManagerPage.fxml"));
            stage.setTitle("Parking Management");
            stage.setMaximized(true);
                break;
        }
        loader.setRoot(null);
        Pane root = loader.load();

        Stage loginStage = (Stage) btnLogin.getScene().getWindow();
        loginStage.close();

        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("com/oopl/icon/parking.png"));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    public void loadParkingAttendantsStage(Employee temp) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();

        loader.setLocation(Main.class.getResource("view/ParkingAttendants.fxml"));
        loader.setRoot(null);

        Pane root = loader.load();
        ParkingAttendantsController attendantsController = loader.getController();
        attendantsController.setController(this);
        attendantsController.setLoginEmployee(temp);

        Stage loginStage = (Stage) btnLogin.getScene().getWindow();
        loginStage.close();

        stage.setTitle("Parking Attendants");
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("com/oopl/icon/parking.png"));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
}
