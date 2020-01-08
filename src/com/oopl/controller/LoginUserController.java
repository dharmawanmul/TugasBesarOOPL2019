package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.oopl.Main;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
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
    private void close(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @FXML
    private void actionLogin(ActionEvent actionEvent) {

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
                Matcher m = Pattern.compile("^[0-9]$").matcher(txtNRP.getText());
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
        stage.show();

    }
}
