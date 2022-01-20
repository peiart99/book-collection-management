package com.peiart99.controllers;

import com.peiart99.main.ApplicationStarter;
import com.peiart99.main.DbObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayCollectionViewController implements Initializable {

    @FXML
    private Text authorText;

    @FXML
    private TableView<DbObject> collectionTableView;

    @FXML
    private TableColumn<DbObject, String> collectionTitleColumn;

    @FXML
    private Text publisherText;

    @FXML
    private TableView<DbObject> seriesTableView;

    @FXML
    private TableColumn<DbObject, String> seriesTitleColumn;

    @FXML
    private TableColumn<DbObject, String> seriesVolumeColumn;

    @FXML
    private Text titleText;

    @FXML
    private Text uniqueLabel1;

    @FXML
    private Text uniqueLabel2;

    @FXML
    private Text uniqueText1;

    @FXML
    private Text uniqueText2;

    @FXML
    private Text volumeLabel;

    @FXML
    private Text volumeText;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ObservableList<DbObject> collectionTableList;
    private ObservableList<DbObject> seriesTableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collectionTitleColumn.setCellValueFactory(new PropertyValueFactory<DbObject, String>("name"));
        collectionTableList = FXCollections.observableArrayList(ApplicationStarter.getCurrentCollection().getCollection());
        collectionTableView.setItems(collectionTableList);
    }

    public void switchToAddBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/peiart99/views/addBook-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
