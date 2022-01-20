package com.peiart99.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartViewController {

    @FXML
    private Button loadButton;
    @FXML
    private Button newButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void onNewCollection(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/peiart99/views/displayCollection-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onLoadCollection(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/peiart99/views/displayCollection-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}