package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.oopl.dao.UserDaoImpl;
import com.oopl.dao.UserHasVehicletypeDaoImpl;
import com.oopl.entity.Role;
import com.oopl.entity.User;
import com.oopl.entity.UserHasVehicletype;
import com.oopl.entity.Vehicletype;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ParkingManagerController implements Initializable {
    @FXML
    private JFXTextField txtRegNo;
    @FXML
    private JFXTextField txtRegNo2;
    @FXML
    private JFXTextField txtName;
    @FXML
    private TableColumn<UserHasVehicletype, String> colName;
    @FXML
    private TableColumn<UserHasVehicletype, String> colRole;
    @FXML
    private TableColumn<UserHasVehicletype, String> colNRP;
    @FXML
    private TableView<UserHasVehicletype> tableUser;
    @FXML
    private TableColumn<UserHasVehicletype, String> colRegNo;
    @FXML
    private TableColumn<UserHasVehicletype, String> colRegNo2;
    @FXML
    private JFXButton refreshBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXTextField searchField;
    private ObservableList<UserHasVehicletype> userVehicles;
    private UserHasVehicletypeDaoImpl userVehiclesDao;
    private UserHasVehicletype selectedItems;

    public ObservableList<UserHasVehicletype> getUsersVehicles() {
        if (userVehicles == null) {
            userVehicles = FXCollections.observableArrayList();
            userVehicles.addAll(getUserVehicleDao().showAll());
        }
        return userVehicles;
    }

    public UserHasVehicletypeDaoImpl getUserVehicleDao() {
        if (userVehiclesDao == null) {
            userVehiclesDao = new UserHasVehicletypeDaoImpl();
        }
        return userVehiclesDao;
    }

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
        tableUser.setItems(getUsersVehicles());
        colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUser().getName()));
        colNRP.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUser().getNrp()));
        colRegNo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRegistrationNo()));
        colRegNo2.setCellValueFactory(d -> new SimpleStringProperty());
        colRole.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUser().getUserRole().getUserRole()));
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        selectedItems = tableUser.getSelectionModel().getSelectedItem();
        txtName.setText(selectedItems.getUser().getName());
        txtNRP.setText(selectedItems.getUser().getNrp());
        txtRegNo.setText(selectedItems.getRegistrationNo());
        txtRegNo2.setText(selectedItems.getRegistrationNo());
    }
}
