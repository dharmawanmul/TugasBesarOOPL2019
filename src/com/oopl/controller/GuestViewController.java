package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.oopl.dao.TicketDaoImpl;
import com.oopl.dao.UserDaoImpl;
import com.oopl.dao.VehicleDaoImpl;
import com.oopl.entity.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuestViewController implements Initializable {
    @FXML
    private JFXTextField txtRegNo;
    @FXML
    private Label lblCheck;
    private final UserDaoImpl userDao = new UserDaoImpl();
    private final VehicleDaoImpl vehicleDao = new VehicleDaoImpl();
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

        vehicleDao.addData(vehicle);
        userDao.addData(user);

        Ticket ticket = new Ticket();
        TicketDaoImpl ticketDao = new TicketDaoImpl();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMYY");
        LocalDateTime now = LocalDateTime.now();
        String front = dtf.format(now);
        String back = "0000" + String.valueOf(ticketDao.getAIId(dtf.format(now)));
        int end = back.length();
        int begin = end - 5;
        ticket.setIdTicket(front + "-" + back.substring(begin, end));
        ticket.setDateIn(Timestamp.valueOf(LocalDateTime.now()));
        ticket.setStatus(0);
        ticket.setTotal(0.0);
        ticket.setUserByUserNrp(user);
        ticket.setVehicleByVehicleRegistrationNum(vehicle);
        Station station = new Station();
        station.setIdStation(3);
        ticket.setStationByStationIdStation(station);
        ticketDao.addData(ticket);

        Stage loginStage = (Stage) btnMbl.getScene().getWindow();
        loginStage.close();
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

        vehicleDao.addData(vehicle);
        userDao.addData(user);

        Ticket ticket = new Ticket();
        TicketDaoImpl ticketDao = new TicketDaoImpl();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMYY");
        LocalDateTime now = LocalDateTime.now();
        String front = dtf.format(now);
        String back = "0000" + String.valueOf(ticketDao.getAIId(dtf.format(now)));
        int end = back.length();
        int begin = end - 5;
        ticket.setIdTicket(front + "-" + back.substring(begin, end));
        ticket.setDateIn(Timestamp.valueOf(LocalDateTime.now()));
        ticket.setStatus(0);
        ticket.setTotal(0.0);
        ticket.setUserByUserNrp(user);
        ticket.setVehicleByVehicleRegistrationNum(vehicle);
        Station station = new Station();
        station.setIdStation(5);
        ticket.setStationByStationIdStation(station);
        ticketDao.addData(ticket);

        Stage loginStage = (Stage) btnMtr.getScene().getWindow();
        loginStage.close();
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
