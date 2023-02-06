package com.example.cum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("formuprincipal2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //ComboBox.getItems().addAll("Doble de uso individual","Doble","Junior Suite","Suite");
        stage.setTitle("Hello!");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}