package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LoginController {
    @FXML
    private void onClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @FXML
    private void loginBtnClicked(MouseEvent mouseEvent) {
    }
}
