
package com.example.cum;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FormuprincipalContoller {

    @FXML
    private Button reservar;

    @FXML
    private Button salir;

    @FXML
    void inclickreservar(ActionEvent event) throws IOException {
        Stage stage =(Stage) reservar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void onClickSalir(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}