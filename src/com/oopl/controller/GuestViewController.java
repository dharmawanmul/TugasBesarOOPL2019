package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.oopl.dao.UserDaoImpl;
import com.oopl.entity.User;
import com.oopl.entity.Userrole;
import com.oopl.entity.Vehicle;
import com.oopl.entity.Vehicletype;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuestViewController implements Initializable {
    @FXML
    private JFXTextField txtRegNo;
    @FXML
    private Label lblCheck;
    private final UserDaoImpl userDao = new UserDaoImpl();
    @FXML
    private JFXButton btnMbl;
    @FXML
    private JFXButton btnMtr;

    @FXML
    private void close(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @FXML
    private void addGuestMobil(ActionEvent actionEvent) {
        Vehicle vehicle = new Vehicle();
        Vehicletype vehicletype = new Vehicletype();
        User user = new User();
        Userrole userrole = new Userrole();

        userrole.setIdUserRole(3);

        vehicletype.setIdType(1);

        vehicle.setVehicletypeByVehicleTypeIdType(vehicletype);
        vehicle.setRegistrationNum(txtRegNo.getText());

        user.setVehicleByVehicleRegistrationNum(vehicle);
        user.setUserroleByUserRoleIdUserRole(userrole);
        user.setNrp("GUEST" + String.valueOf(userDao.getGuest()));
        user.setUserName("");

        userDao.addData(user);
    }

    @FXML
    private void addGuestMotor(ActionEvent actionEvent) {
        Vehicle vehicle = new Vehicle();
        Vehicletype vehicletype = new Vehicletype();
        User user = new User();
        Userrole userrole = new Userrole();

        userrole.setIdUserRole(3);

        vehicletype.setIdType(2);

        vehicle.setVehicletypeByVehicleTypeIdType(vehicletype);
        vehicle.setRegistrationNum(txtRegNo.getText());

        user.setVehicleByVehicleRegistrationNum(vehicle);
        user.setUserroleByUserRoleIdUserRole(userrole);
        user.setNrp("GUEST" + String.valueOf(userDao.getGuest()));
        user.setUserName("");

        userDao.addData(user);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtRegNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Matcher m = Pattern.compile("^[a-zA-Z]{1,2}\\s[\\d]{1,4}\\s[a-zA-Z]{1,4}$").matcher(txtRegNo.getText());
                if (m.find()) {
                    lblCheck.setText("Proceed");
                    lblCheck.setStyle("-fx-text-fill: green");
                    btnMbl.setDisable(false);
                    btnMtr.setDisable(false);
                } else {
                    lblCheck.setText("Invalid license plate number");
                    lblCheck.setStyle("-fx-text-fill: red");
                    btnMbl.setDisable(true);
                    btnMtr.setDisable(true);
                }
            }
        });
    }
}
