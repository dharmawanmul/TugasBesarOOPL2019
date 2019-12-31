package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.oopl.entity.Employee;
import com.oopl.entity.Role;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

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
    private JFXComboBox<Role> cmbxRole;
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
    private EmployeePageController mainFormController;
    private final ObservableList<Employee> dataList = FXCollections.observableArrayList();
//    @FXML
//    private VBox vbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        vbox.setVgrow(tableEmployee, Priority.ALWAYS);
//        FilteredList<Employee> filteredData = new FilteredList<>(dataList, b -> true);
//
//        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(employee -> {
//
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (employee.getName().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches first name.
//                } else if (employee.getUsername().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                } else if (employee.getRoleByRoleIdRole().getRole().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                }
//                else
//                    return false; // Does not match.
//            });
//        });
//
//        SortedList<Employee> sortedData = new SortedList<>(filteredData);
//
//        sortedData.comparatorProperty().bind(tableEmployee.comparatorProperty());
//
//        tableEmployee.setItems(sortedData);
//        cmbxRole.setItems(mainFormController.getRole());

        col01.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        col02.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUsername()));
        col03.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEmployeeRole().getRole()));
    }

    public void setMainFormController(EmployeePageController mainFormController) {
        this.mainFormController = mainFormController;
//        dataList.addAll(mainFormController.getEmployees());
        tableEmployee.setItems(mainFormController.getEmployees());
    }


}
