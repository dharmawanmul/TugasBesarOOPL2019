package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ParkingManagerController implements Initializable {
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
    @FXML
    private JFXTextField searchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        FilteredList<Object> filteredList = new FilteredList<>(Object, b -> true);
//        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredList.setPredicate(users -> {
//                if (newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCase = newValue.toLowerCase();
//                else if (users) {
//
//                }
//                else {
//                    return false;
//                }
//            });
//        });
    }
}
