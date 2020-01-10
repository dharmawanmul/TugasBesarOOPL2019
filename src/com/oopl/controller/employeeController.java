package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class employeeController {
    @FXML
    private JFXTextField txtNRP;
    @FXML
    private JFXTextField txtRegNo;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXComboBox cmbxRole;
    @FXML
    private JFXComboBox cmbxVehType;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colRole;
    @FXML
    private TableColumn colNRP;
    @FXML
    private TableView tableUser;
    @FXML
    private TableColumn colRegNo;
    @FXML
    private TableColumn colVehType;
    @FXML
    private JFXButton refreshBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton updateBtn;
}
