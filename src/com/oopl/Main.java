package com.oopl;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private double initialX, initialY;
    private void addDragListeners(final Node n){
        n.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(me.getButton()!= MouseButton.MIDDLE)
                {
                    initialX = me.getSceneX();
                    initialY = me.getSceneY();
                }
                else
                {
                    n.getScene().getWindow().centerOnScreen();
                    initialX = n.getScene().getWindow().getX();
                    initialY = n.getScene().getWindow().getY();
                }
            }
        });

        n.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(me.getButton()!=MouseButton.MIDDLE)
                {
                    n.getScene().getWindow().setX( me.getScreenX() - initialX );
                    n.getScene().getWindow().setY( me.getScreenY() - initialY);
                }
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
//      Employee
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginEmployee.fxml"));
//      User
//        Parent root = FXMLLoader.load(getClass().getResource("view/LoginUser.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("com/oopl/icon/parking.png"));
        addDragListeners(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
