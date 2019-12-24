package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class LoginUserController {
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXTextField txtNRP;
    @FXML
    private JFXTextField txtRegNo;


    @FXML
    private void close(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @FXML
    private void actionLogin(ActionEvent actionEvent) {

    }
}
