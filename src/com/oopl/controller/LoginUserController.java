package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.oopl.Main;
import com.oopl.dao.TicketDaoImpl;
import com.oopl.entity.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginUserController implements Initializable {
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXTextField txtNRP;
    @FXML
    private JFXTextField txtRegNo;
    @FXML
    private Label lblCheck;
    @FXML
    private Label lblCheckReg;
    private TicketDaoImpl ticketDao = new TicketDaoImpl();

    @FXML
    private void close(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @FXML
    private void actionLogin(ActionEvent actionEvent) {
        Ticket ticket = new Ticket();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMYY");
        LocalDateTime now = LocalDateTime.now();
        String front = dtf.format(now);
        String back = "0000" + String.valueOf(ticketDao.getAIId(dtf.format(now)));
        int end = back.length();
        int begin = end-5;
        ticket.setIdTicket(front+"-"+back.substring(begin,end));
        ticket.setDateIn(Timestamp.valueOf(LocalDateTime.now()));
        ticket.setStatus(0);
        ticket.setTotal(0.0);

        User user = new User();
        user.setNrp(txtNRP.getText());

        Vehicletype vehicletype = new Vehicletype();

        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNum(txtRegNo.getText());
        vehicle.setVehicletypeByVehicleTypeIdType(vehicletype);

        ticket.setUserByUserNrp(user);
        ticket.setVehicleByVehicleRegistrationNum(vehicle);

        Station station = new Station();
        station.setIdStation(1);
        if (station.getIdStation() == 1 || station.getIdStation() == 2 || station.getIdStation() == 3) {
            vehicletype.setIdType(1);
        } else {
            vehicletype.setIdType(2);
        }
        ticket.setStationByStationIdStation(station);

        ticketDao.addData(ticket);
        //report

        Stage loginStage = (Stage) loginBtn.getScene().getWindow();
        loginStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtRegNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Matcher m = Pattern.compile("^[a-zA-Z]{1,2}\\s[\\d]{1,4}\\s[a-zA-Z]{1,4}$").matcher(txtRegNo.getText());
                if (m.find()) {
                    lblCheck.setText("");
                    lblCheck.getStyleClass().clear();
                    loginBtn.setDisable(false);
                } else {
                    lblCheck.setText("Please input a valid license plate number");
                    lblCheck.setStyle("-fx-text-fill: red");
                    loginBtn.setDisable(true);
                }
            }
        });
        txtNRP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Matcher m = Pattern.compile("^[0-9]{1,10}$").matcher(txtNRP.getText());
                if (m.find()) {
                    lblCheck.setText("");
                    lblCheck.getStyleClass().clear();
                    loginBtn.setDisable(false);
                } else {
                    lblCheck.setText("Please input a valid NRP");
                    lblCheck.setStyle("-fx-text-fill: red");
                    loginBtn.setDisable(true);
                }
            }
        });
        txtRegNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Matcher m = Pattern.compile("^[a-zA-Z]{1,2}\\s[\\d]{1,4}\\s[a-zA-Z]{1,4}$").matcher(txtRegNo.getText());
                if (m.find()) {
                    lblCheckReg.setText("Valid license plate number");
                    lblCheckReg.setStyle("-fx-text-fill: green");
                    loginBtn.setDisable(false);
                } else {
                    lblCheckReg.setText("Invalid license plate number");
                    lblCheckReg.setStyle("-fx-text-fill: red");
                    loginBtn.setDisable(true);
                }
            }
        });
    }


    @FXML
    private void loadGuestView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();

        loader.setLocation(Main.class.getResource("view/GuestView.fxml"));
        loader.setRoot(null);

        Pane root = loader.load();

        Stage loginStage = (Stage) loginBtn.getScene().getWindow();
        loginStage.close();

        stage.setTitle("Guest View");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("com/oopl/icon/parking.png"));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
