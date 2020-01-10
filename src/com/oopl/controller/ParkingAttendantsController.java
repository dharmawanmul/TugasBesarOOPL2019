package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.oopl.dao.PermissionsDaoImpl;
import com.oopl.dao.TicketDaoImpl;
import com.oopl.dao.VoucherDaoImpl;
import com.oopl.entity.Employee;
import com.oopl.entity.Permissions;
import com.oopl.entity.Ticket;
import com.oopl.entity.Voucher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParkingAttendantsController implements Initializable {
    @FXML
    private JFXTextField txtRegNo;
    @FXML
    private JFXTextField txtVoucher;
    @FXML
    private JFXTextField txtPermission;
    @FXML
    private JFXTextArea txtPrice;
    @FXML
    private ImageView imgEmp;
    @FXML
    private Label lblEmpName;
    private Employee loginEmployee;
    @FXML
    private Label lblEmpId;
    private LoginEmployeeController controller;
    @FXML
    private JFXButton printBtn;
    private TicketDaoImpl ticketDao = new TicketDaoImpl();
    private PermissionsDaoImpl permissionsDao = new PermissionsDaoImpl();
    private VoucherDaoImpl voucherDao = new VoucherDaoImpl();
    @FXML
    private JFXTextField txtNRP;
    @FXML
    private JFXTextField txtTicket;
    @FXML
    private Label lblDisplay;

    public void setController(LoginEmployeeController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNRP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Matcher m = Pattern.compile("^[\\d]{1,10}$").matcher(txtNRP.getText());
                if (m.find()) {
                    lblDisplay.setText("");
                    lblDisplay.getStyleClass().clear();
                    printBtn.setDisable(true);
                } else {
                    lblDisplay.setText("Invalid NRP");
                    lblDisplay.setStyle("-fx-text-fill: red");
                    printBtn.setDisable(false);
                }
            }
        });
//        txtRegNo.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                Matcher m = Pattern.compile("^[a-zA-Z]{1,2}\\s[\\d]{1,4}\\s[a-zA-Z]{1,4}$").matcher(txtRegNo.getText());
//                if (m.find()) {
//                    lblDisplay.setText("");
//                    lblDisplay.getStyleClass().clear();
//                    printBtn.setDisable(false);
//                } else {
//                    lblDisplay.setText("Invalid license plate number");
//                    lblDisplay.setStyle("-fx-text-fill: red");
//                    printBtn.setDisable(true);
//                }
//            }
//        });
    }

    public void setLoginEmployee(Employee employee) {
        this.loginEmployee = employee;
    }

    @FXML
    private void printAct(ActionEvent actionEvent) {
        Ticket ticket = new Ticket();
        if (!txtNRP.getText().isEmpty()) {
            ticket = ticketDao.getTicketByNRPAndRegisNum(txtNRP.getText(), txtRegNo.getText());
        } else if (!txtTicket.getText().isEmpty()) {
            ticket = ticketDao.getTicketByIdTicketAndRegisNum(txtTicket.getText(), txtRegNo.getText());
        }
        ticket.setDateOut(Timestamp.valueOf(LocalDateTime.now()));
        ticket.setStatus(1);

        long milliseconds = ticket.getDateOut().getTime() - ticket.getDateIn().getTime();
        int seconds = (int) milliseconds / 1000;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = (seconds % 3600) % 60;
        if (seconds > 0){
            minutes += 1;
        }
        if (minutes > 0){
            hours += 1;
        }
        if (ticket.getUserByUserNrp().getNrp().substring(0, 5).equals("GUEST")) {
            if (ticket.getVehicleByVehicleRegistrationNum().getVehicletypeByVehicleTypeIdType().getIdType() == 1) {
                ticket.setTotal((double) hours * 3000);
            } else if (ticket.getVehicleByVehicleRegistrationNum().getVehicletypeByVehicleTypeIdType().getIdType() == 2) {
                ticket.setTotal((double) hours * 2000);
            }
        }
        ticket.setTotal((double) (ticket.getVehicleByVehicleRegistrationNum().getVehicletypeByVehicleTypeIdType().getIdType() == 2 ? 2000 : 3000));
        double total = ticket.getTotal();

        if (hours > 23){
            ticket.setTotal(total + (hours/24*15000));
        }
        if (!txtPermission.getText().isEmpty()){
            if (permissionsDao.getPermissionById(txtPermission.getText()) != null & permissionsDao.getPermissionById(txtPermission.getText()).getDateEnd().after(Date.valueOf(LocalDate.now()))){
                ticket.setTotal(0.0);
            } else if(permissionsDao.getPermissionById(txtPermission.getText().trim()).getDateEnd().before(Date.valueOf(LocalDate.now()))){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Permission Expired !");
                a.showAndWait();
            }
        }
        if(!txtVoucher.getText().isEmpty()){
            Voucher voucher = voucherDao.getVoucherById(txtVoucher.getText().trim());
            if(voucher != null){
                if (voucher.getVoucherType().equals("FLAT")){
                    ticket.setTotal(5000.0);
                }else if(voucher.getVoucherType().equals("FREE")){
                    ticket.setTotal(0.0);
                }
            }
        }
        txtPrice.setText("Rp. " + String.valueOf(ticket.getTotal()));
        txtPrice.setStyle("-fx-text-fill: black");
        ticketDao.updateData(ticket);
        clearAll();
    }

    private void clearAll() {
        txtPermission.clear();
        txtPrice.clear();
        txtNRP.clear();
        txtRegNo.clear();
        txtTicket.clear();
        txtVoucher.clear();
        txtVoucher.setDisable(false);
        txtTicket.setDisable(false);
        txtNRP.setDisable(false);
        txtRegNo.setDisable(false);
    }

    @FXML
    private void nrpClicked(MouseEvent mouseEvent) {
        txtTicket.clear();
        txtVoucher.clear();
        txtPermission.setDisable(false);
    }

    @FXML
    private void permissionClicked(MouseEvent mouseEvent) {
        txtVoucher.clear();
    }

    @FXML
    private void ticketClicked(MouseEvent mouseEvent) {
        txtNRP.clear();
        txtVoucher.setDisable(false);
        txtPermission.setDisable(false);
    }

    @FXML
    private void voucherClicked(MouseEvent mouseEvent) {
        txtPermission.clear();
    }
}
