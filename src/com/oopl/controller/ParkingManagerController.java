package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.oopl.dao.UserDaoImpl;
import com.oopl.dao.UserHasVehicleDaoImpl;
import com.oopl.entity.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private TableColumn<UserHasVehicle, String> colName;
    @FXML
    private TableColumn<UserHasVehicle, String> colRole;
    @FXML
    private TableColumn<UserHasVehicle, String> colNRP;
    @FXML
    private TableView<UserHasVehicle> tableUser;
    @FXML
    private TableColumn<UserHasVehicle, String> colRegNo;
    @FXML
    private TableColumn<UserHasVehicle, String> colRegNo2;
    @FXML
    private JFXButton refreshBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXTextField searchField;
    private ObservableList<UserHasVehicle> userVehicles;
    private UserHasVehicleDaoImpl userVehiclesDao;
    private UserHasVehicle selectedItems;
    private final ObservableList<UserHasVehicle> dataList = FXCollections.observableArrayList();

    public ObservableList<UserHasVehicle> getUsersVehicles() {
        if (userVehicles == null) {
            userVehicles = FXCollections.observableArrayList();
            userVehicles.addAll(getUserVehicleDao().showAll());
        }
        return userVehicles;
    }

    public UserHasVehicleDaoImpl getUserVehicleDao() {
        if (userVehiclesDao == null) {
            userVehiclesDao = new UserHasVehicleDaoImpl();
        }
        return userVehiclesDao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox.setVgrow(tableUser, Priority.ALWAYS);
        tableUser.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        dataList.addAll(getUsersVehicles());
        FilteredList<UserHasVehicle> filteredData = new FilteredList<>(dataList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getUserNRP().getUserName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (user.getUserNRP().getNrp().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (user.getUserRegistrationNum().getRegistrationNum().trim().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (user.getUserNRP().getUserRole().getUserRole().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<UserHasVehicle> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableUser.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableUser.setItems(sortedData);
        colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUserNRP().getUserName()));
        colNRP.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUserNRP().getNrp()));
        colRegNo.setCellValueFactory(d -> new SimpleStringProperty(((d.getValue().getUserRegistrationNum().getVehicleType().equals("Mobil")) ? d.getValue().getUserRegistrationNum().getRegistrationNum() : "")));
        colRegNo2.setCellValueFactory(d -> new SimpleStringProperty(((d.getValue().getUserRegistrationNum().getVehicleType().equals("Motor")) ? d.getValue().getUserRegistrationNum().getRegistrationNum() : "")));
        colRole.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUserNRP().getUserRole().getUserRole()));
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        selectedItems = tableUser.getSelectionModel().getSelectedItem();
        txtName.setText(selectedItems.getUserNRP().getUserName());
        txtRegNo.setText(((selectedItems.getUserRegistrationNum().getVehicleType().equals("Mobil")) ? selectedItems.getUserRegistrationNum().getRegistrationNum() : ""));
        txtRegNo2.setText(((selectedItems.getUserRegistrationNum().getVehicleType().equals("Motor")) ? selectedItems.getUserRegistrationNum().getRegistrationNum() : ""));
    }
}
