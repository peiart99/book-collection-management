package com.peiart99.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddBookViewController implements Initializable {

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField publisherTextField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField titleTextField;

    @FXML
    private ListView<String> typeChoiceList;

    @FXML
    private ChoiceBox<?> uniqueChoice;

    @FXML
    private Text uniqueLabel;

    @FXML
    private Text warningText;

    @FXML
    private TextField uniqueTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList("Comicbook", "Novel", "Educational");
        typeChoiceList.setItems(options);
    }

    public void onChoice(MouseEvent event) {
        String choice = typeChoiceList.getSelectionModel().getSelectedItem();
        switch (choice) {
            case "Comicbook" -> System.out.println("komiksior");
            case "Novel" -> System.out.println("nowelka");
            case "Educational" -> System.out.println("ecyclopedieee");
        }
    }

    public void onSubmit(ActionEvent event) {
        if(Objects.equals(typeChoiceList.getSelectionModel().getSelectedItem(), null)) {
            warningText.setText("You must choose a book type!");
        }else {
            warningText.setText("");
            System.out.println("Gituwa");
        }
    }
}
