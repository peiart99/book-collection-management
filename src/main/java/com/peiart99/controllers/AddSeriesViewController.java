package com.peiart99.controllers;

import com.peiart99.enums.NovelGenre;
import com.peiart99.enums.Topic;
import com.peiart99.main.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddSeriesViewController implements Initializable {

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField publisherTextField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField volumeTextField;

    @FXML
    private ListView<String> typeChoiceList;

    @FXML
    private ChoiceBox<NovelGenre> uniqueChoice;
    @FXML
    private ChoiceBox<Topic> uniqueChoice2;

    @FXML
    private Text uniqueLabel;

    @FXML
    private Text volumeLabel;

    @FXML
    private Text warningText;

    @FXML
    private TextField uniqueTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList("Empty", "Comicbook", "Novel", "Educational");
        uniqueChoice.getItems().setAll(NovelGenre.values());
        uniqueChoice2.getItems().setAll(Topic.values());
        uniqueTextField.setVisible(false);
        uniqueChoice.setVisible(false);
        uniqueChoice2.setVisible(false);
        volumeTextField.setVisible(false);
        volumeLabel.setText("");
        typeChoiceList.setItems(options);
        volumeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!t1.matches("\\d*")) {
                    volumeTextField.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void onChoice(MouseEvent event) {
        String choice = typeChoiceList.getSelectionModel().getSelectedItem();
        switch (choice) {
            case "Empty" -> {
                uniqueChoice.setVisible(false);
                uniqueChoice2.setVisible(false);
                uniqueTextField.setVisible(false);
                uniqueLabel.setText("");
                volumeTextField.setVisible(false);
                volumeLabel.setText("");
            }
            case "Comicbook" -> {
                uniqueChoice.setVisible(false);
                uniqueChoice2.setVisible(false);
                volumeTextField.setVisible(true);
                volumeLabel.setText("Volumes:");
                uniqueTextField.clear();
                uniqueTextField.setVisible(true);
                uniqueLabel.setText("Illustrator:");
            }
            case "Novel", "Educational" -> {
                volumeTextField.setVisible(true);
                volumeLabel.setText("Volumes:");
                if (choice.equals("Novel")) {
                    uniqueChoice.setVisible(true);
                    uniqueChoice.setValue(NovelGenre.Adventure);
                    uniqueChoice2.setVisible(false);
                } else {
                    uniqueChoice.setVisible(false);
                    uniqueChoice2.setVisible(true);
                    uniqueChoice2.setValue(Topic.History);
                }
                uniqueTextField.setVisible(false);
                String text = choice.equals("Novel") ? "Genre:" : "Topic:";
                uniqueLabel.setText(text);
            }
        }
    }

    public void onSubmit(ActionEvent event) throws IOException {
        int volumes;
        int i;
        Series newSeries = new Series(titleTextField.getText());
        volumes = Objects.equals(volumeTextField.getText(), "") ? 1 : Integer.parseInt(volumeTextField.getText());

        if(Objects.equals(typeChoiceList.getSelectionModel().getSelectedItem(), null)) {
            warningText.setText("You must choose a book type!");
            warningText.textAlignmentProperty();
        }else if(Objects.equals(titleTextField.getText(), "")) {
            warningText.setText("You must provide a title at least!");
        }else {
            String choice = typeChoiceList.getSelectionModel().getSelectedItem();
            switch(choice)
            {
                case "Empty" -> {
                }
                case "Comicbook" -> {
                    for(i = 0; i < volumes; i++) {
                        Comicbook newComicbook = new Comicbook(titleTextField.getText(), authorTextField.getText(), uniqueTextField.getText(), publisherTextField.getText(), i + 1);
                        newSeries.addVolume(newComicbook);
                    }
                }
                case "Novel" -> {
                    for(i = 0; i < volumes; i++) {
                        Novel newNovel = new Novel(titleTextField.getText(), authorTextField.getText(), publisherTextField.getText(), i + 1, uniqueChoice.getValue());
                        newSeries.addVolume(newNovel);
                    }
                }
                case "Educational" -> {
                    for(i = 0; i < volumes; i++) {
                        Educational newEducational = new Educational(titleTextField.getText(), authorTextField.getText(), publisherTextField.getText(), i + 1, uniqueChoice2.getValue());
                        newSeries.addVolume(newEducational);
                    }
                }
            }

            ApplicationStarter.getCurrentCollection().addBook(newSeries);
            goBack(event);
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/peiart99/views/displayCollection-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
