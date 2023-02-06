package com.example.cum;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController  implements Initializable
{

    @FXML
    private Button botonsalir;
    @FXML
    private CheckBox porroCheck;
    @FXML
    private Label nofumar;
    @FXML
    private ComboBox tipoHabitacionComboB;
    @FXML
    private Button aceptarboton;

    @FXML
    protected void onHelloButtonClick() {

        Stage stage =(Stage) botonsalir.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("formuprincipal2.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    }

    @FXML
    protected void onfumaporrotickClick() {
        if(porroCheck.isSelected()){
        nofumar.setVisible(true);
    }else{
            nofumar.setVisible(false);
        }
    }
    @FXML
    protected void onAceptarButtonClick( ) {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoHabitacionComboB.getItems().addAll("Doble de uso individual","Doble","Junior Suite","Suite");
    }

}