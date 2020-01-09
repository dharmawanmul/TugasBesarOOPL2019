package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.oopl.dao.UserDaoImpl;
import com.oopl.entity.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParkingManagerController implements Initializable {
    private LoginEmployeeController controller;
    @FXML
    private JFXTextField txtRegNo;
    @FXML
    private JFXTextField txtRegNo2;
    @FXML
    private JFXTextField txtName;
    @FXML
    private TableColumn<User, String> colName;
    @FXML
    private TableColumn<User, String> colRole;
    @FXML
    private TableColumn<User, String> colNRP;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> colRegNo;
    @FXML
    private TableColumn<User, String> colRegNo2;
    @FXML
    private JFXButton refreshBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXTextField searchField;
    private ObservableList<User> userVehicles;
    private UserDaoImpl userVehiclesDao;
    private User selectedItems;
    private final ObservableList<User> dataList = FXCollections.observableArrayList();
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton removeBtn;
    @FXML
    private Label lblName;
    @FXML
    private Label lblMotorcycle;
    @FXML
    private Label lblCar;
    @FXML
    private Label lblRegNo;
    @FXML
    private Label lblRegNo2;

    public ObservableList<User> getUsersVehicles() {
        if (userVehicles == null) {
            userVehicles = FXCollections.observableArrayList();
            userVehicles.addAll(getUserVehicleDao().showAll());
        }
        return userVehicles;
    }

    public UserDaoImpl getUserVehicleDao() {
        if (userVehiclesDao == null) {
            userVehiclesDao = new UserDaoImpl();
        }
        return userVehiclesDao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox.setVgrow(tableUser, Priority.ALWAYS);
        tableUser.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        dataList.addAll(getUsersVehicles());
        FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getUserName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getNrp().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getVehicleByVehicleRegistrationNum().getRegistrationNum().trim().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getUserroleByUserRoleIdUserRole().getUserRole().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else
                    return false;
            });
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableUser.comparatorProperty());

        tableUser.setItems(sortedData);
        colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUserName()));
        colNRP.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNrp()));
        colRegNo.setCellValueFactory(d -> new SimpleStringProperty(((d.getValue().getVehicleByVehicleRegistrationNum().getVehicletypeByVehicleTypeIdType().getVehicleType().equals("Mobil")) ? d.getValue().getVehicleByVehicleRegistrationNum().getRegistrationNum() : "")));
        colRegNo2.setCellValueFactory(d -> new SimpleStringProperty(((d.getValue().getVehicleByVehicleRegistrationNum().getVehicletypeByVehicleTypeIdType().getVehicleType().equals("Motor")) ? d.getValue().getVehicleByVehicleRegistrationNum().getRegistrationNum() : "")));
        colRole.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUserroleByUserRoleIdUserRole().getUserRole()));

        txtRegNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (txtRegNo.getText().length() > 9) {
                    lblRegNo.setText("License plate can't be more than 9 characters");
                    lblRegNo.setStyle("-fx-text-fill: red");
                    saveBtn.setDisable(true);
                } else {
                    lblRegNo.setText("");
                    lblRegNo.getStyleClass().clear();
                    saveBtn.setDisable(false);
                }
//              "^[a-zA-Z]{1,2}\\s[\\d]{1,4}\\s[a-zA-Z]{1,3}$" //regex
                Matcher m = Pattern.compile("^[a-zA-Z]{1,2}\\s[\\d]{1,4}\\s[a-zA-Z]{1,4}$").matcher(txtRegNo.getText());
                if (m.find()) {
                    lblRegNo.setText("");
                    lblRegNo.getStyleClass().clear();
                    saveBtn.setDisable(false);
                } else {
                    lblRegNo.setText("Invalid license plate number");
                    lblRegNo.setStyle("-fx-text-fill: red");
                    saveBtn.setDisable(true);
                }
            }
        });
        txtRegNo2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (txtRegNo2.getText().replaceAll("\\s+","").length() > 9) {
                    lblRegNo2.setText("License plate can't be more than 9 characters");
                    lblRegNo2.setStyle("-fx-text-fill: red");
                    saveBtn.setDisable(true);
                } else {
                    lblRegNo2.setText("");
                    lblRegNo2.getStyleClass().clear();
                    saveBtn.setDisable(false);
                }
                Matcher m = Pattern.compile("^[a-zA-Z]{1,2}\\s[\\d]{1,4}\\s[a-zA-Z]{1,4}$").matcher(txtRegNo2.getText());
                if (m.find()) {
                    lblRegNo2.setText("");
                    lblRegNo2.getStyleClass().clear();
                    saveBtn.setDisable(false);
                } else {
                    lblRegNo2.setText("Invalid license plate number");
                    lblRegNo2.setStyle("-fx-text-fill: red");
                    saveBtn.setDisable(true);
                }
            }
        });
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        selectedItems = tableUser.getSelectionModel().getSelectedItem();
        txtName.setText(selectedItems.getUserName());
        txtRegNo.setText(((selectedItems.getVehicleByVehicleRegistrationNum().getVehicletypeByVehicleTypeIdType().getVehicleType().equals("Mobil")) ? selectedItems.getVehicleByVehicleRegistrationNum().getRegistrationNum() : ""));
        txtRegNo2.setText(((selectedItems.getVehicleByVehicleRegistrationNum().getVehicletypeByVehicleTypeIdType().getVehicleType().equals("Motor")) ? selectedItems.getVehicleByVehicleRegistrationNum().getRegistrationNum() : ""));
    }

    @FXML
    private void saveAction(ActionEvent actionEvent) {
        User user = new User();
        Vehicle vehicle = new Vehicle();
        Vehicletype type = new Vehicletype();
        if (txtRegNo.getText().isEmpty() & !txtRegNo2.getText().isEmpty()){
            vehicle.setRegistrationNum(txtRegNo2.getText());
            type.setIdType(2);
            type.setVehicleType("Motor");
        }else if (txtRegNo2.getText().isEmpty() & !txtRegNo.getText().isEmpty()){
            vehicle.setRegistrationNum(txtRegNo.getText());
            type.setIdType(1);
            type.setVehicleType("Mobil");
        }
        user.setNrp(selectedItems.getNrp());
        user.setUserName(selectedItems.getUserName());
        user.setUserroleByUserRoleIdUserRole(selectedItems.getUserroleByUserRoleIdUserRole());

        vehicle.setVehicletypeByVehicleTypeIdType(type);

        user.setVehicleByVehicleRegistrationNum(vehicle);

        UserDaoImpl dao = new UserDaoImpl();
        dao.updateData(user);
        int result = dao.updateData(user);
        System.out.println(result);
        resetForm();
        dataList.clear();
        dataList.addAll(getUsersVehicles());
    }

    @FXML
    private void refreshAction(ActionEvent actionEvent) {
        resetForm();
    }

    private void resetForm(){
        txtRegNo2.clear();
        txtRegNo.clear();
        txtName.clear();
        tableUser.refresh();
        lblRegNo.getStyleClass().clear();
        lblRegNo.setText("");
        lblRegNo2.getStyleClass().clear();
        lblRegNo2.setText("");
    }
}
