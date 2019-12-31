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

public class ParkingAttendantsController {
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
    private Employee empWorking;
    @FXML
    private ImageView imgEmp;
    @FXML
    private Label lblEmpName;

}
