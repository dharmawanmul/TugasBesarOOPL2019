package com.oopl.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.oopl.entity.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ParkingAttendantsController implements Initializable {
    @FXML
    private JFXTextField txtNRP;
    @FXML
    private JFXTextField txtRegNo;
    @FXML
    private JFXTextField txtVoucher;
    @FXML
    private JFXTextField txtPermission;
    @FXML
    private JFXTextArea txtPrice;
    @FXML
    private ImageView imgEmp;
    @FXML
    private Label lblEmpName;
    private Employee loginEmployee;
    @FXML
    private Label lblEmpId;
    private LoginEmployeeController controller;

    public void setController(LoginEmployeeController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLoginEmployee(Employee employee) {
        this.loginEmployee = employee;
    }
}
