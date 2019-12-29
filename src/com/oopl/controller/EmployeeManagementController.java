package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.oopl.entity.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagementController implements Initializable {
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton refreshBtn;
    @FXML
    private TableView<Employee> tableEmployee;
    @FXML
    private TableColumn<Employee, String> col01;
    @FXML
    private TableColumn<Employee, String> col02;
    @FXML
    private TableColumn<Employee, String> col03;
    @FXML
    private JFXComboBox cmbxRole;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXPasswordField txtRePass;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton deleteBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col01.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        col02.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPassword()));
        col03.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoleByRoleIdRole().getRole()));
    }
}
