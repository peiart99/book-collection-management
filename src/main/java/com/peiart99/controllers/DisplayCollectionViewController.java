package com.peiart99.controllers;

import com.peiart99.main.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
    private Text typeText;

    @FXML
    private Text idText;

    @FXML
    private Text uniqueLabel1;

    @FXML
    private Text uniqueLabel2;

    @FXML
    private Text uniqueText1;

    @FXML
    private Text uniqueText2;

    @FXML
    private TextField searchTextField;

    @FXML
    private Text volumeLabel;

    @FXML
    private Text totalText;

    @FXML
    private Text volumeText;

    @FXML
    private ChoiceBox<String> filterChoice;

    private Stage stage;
    private boolean mainTable;
    private DbObject tempSeries;
    private int tempVolumes;
    private Scene scene;
    private Parent root;
    private ObservableList<DbObject> collectionTableList;
    private ObservableList<DbObject> seriesTableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collectionTitleColumn.setCellValueFactory(new PropertyValueFactory<DbObject, String>("name"));
        collectionTableList = FXCollections.observableArrayList(ApplicationStarter.getCurrentCollection().getCollection());
        collectionTableView.setItems(collectionTableList);
        totalText.setText(String.valueOf(ApplicationStarter.getCurrentCollection().getCollectionSize()));
        ObservableList<String> options = FXCollections.observableArrayList("Comicbook", "Novel", "Educational", "Series");
        filterChoice.setItems(options);
        filterChoice.setValue("Comicbook");
        this.mainTable = true;
    }

    public void switchToAddBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/peiart99/views/addBook-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToAddSeries(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/peiart99/views/addSeries-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getSelectedRow(MouseEvent event) {
        seriesTableView.getItems().clear();
        this.mainTable = true;
        DbObject entry = collectionTableView.getSelectionModel().getSelectedItem();
        typeText.setText(entry.getClassName());
        titleText.setText(entry.getName());
        volumeLabel.setText("Volume:");
        idText.setText(String.valueOf(entry.getID()));
        if(entry instanceof Comicbook) {
            this.tempVolumes = 1;
            authorText.setText(((Book) entry).getAuthor());
            publisherText.setText(((Book) entry).getPublisher());
            volumeText.setText(String.valueOf(((Comicbook) entry).getVolume()));
            uniqueLabel1.setText("Illustrator:");
            uniqueText1.setText(((Comicbook) entry).getIllustrator());
        }else if(entry instanceof Novel) {
            this.tempVolumes = 1;
            authorText.setText(((Book) entry).getAuthor());
            publisherText.setText(((Book) entry).getPublisher());
            volumeText.setText(String.valueOf(((Novel) entry).getVolume()));
            uniqueLabel1.setText("Genre:");
            uniqueText1.setText(String.valueOf(((Novel) entry).getGenre()));
        }else if(entry instanceof Educational) {
            this.tempVolumes = 1;
            authorText.setText(((Book) entry).getAuthor());
            publisherText.setText(((Book) entry).getPublisher());
            volumeText.setText(String.valueOf(((Educational) entry).getVolume()));
            uniqueLabel1.setText("Topic:");
            uniqueText1.setText(String.valueOf(((Educational) entry).getTopic()));
        }else if(entry instanceof Series) {
            this.tempSeries = entry;
            this.tempVolumes = ((Series) entry).getVolumes();
            seriesTitleColumn.setCellValueFactory(new PropertyValueFactory<DbObject, String>("name"));
            seriesVolumeColumn.setCellValueFactory(new PropertyValueFactory<DbObject, String>("volume"));
            seriesTableList = FXCollections.observableArrayList(((Series)entry).getBooks());
            seriesTableView.setItems(seriesTableList);
            ControllerDataPath path = ControllerDataPath.getInstance();
            path.setEntry(entry);
            titleText.setText(entry.getName());
            authorText.setText("");
            publisherText.setText("");
            volumeLabel.setText("Volumes:");
            volumeText.setText(String.valueOf(((Series)entry).getVolumes()));
        }
    }

    public void getSelectedSeriesRow(MouseEvent event) {
        DbObject entry = seriesTableView.getSelectionModel().getSelectedItem();
        this.mainTable = false;
        ControllerDataPath path = ControllerDataPath.getInstance();
        path.setEntry(entry);
        titleText.setText(entry.getName());
        authorText.setText(((Book)entry).getAuthor());
        publisherText.setText(((Book)entry).getPublisher());
        volumeText.setText(String.valueOf(((Book) entry).getVolume()));
        volumeLabel.setText("Volume:");
        typeText.setText(entry.getClassName());
        idText.setText(String.valueOf(entry.getID()));
        if(entry instanceof Comicbook) {
            uniqueLabel1.setText("Illustrator:");
            uniqueText1.setText(((Comicbook) entry).getIllustrator());
        }else if(entry instanceof Novel) {
            uniqueLabel1.setText("Genre:");
            uniqueText1.setText(String.valueOf(((Novel) entry).getGenre()));
        }else if(entry instanceof Educational) {
            uniqueLabel1.setText("Topic:");
            uniqueText1.setText(String.valueOf(((Educational) entry).getTopic()));
        }
    }

    public void onDelete(ActionEvent event) {
        if(!Objects.equals(idText.getText(), "") && !Objects.equals(idText.getText(), "N/A")) {
            if(this.mainTable)
            {
                ApplicationStarter.getCurrentCollection().deleteObject(Integer.parseInt(idText.getText()));
                collectionTableList = FXCollections.observableArrayList(ApplicationStarter.getCurrentCollection().getCollection());
                collectionTableView.setItems(collectionTableList);
                seriesTableView.getItems().clear();
                int temp = ApplicationStarter.getCurrentCollection().getCollectionSize();
                ApplicationStarter.getCurrentCollection().setCollectionSize(temp - this.tempVolumes);

            }else {
                ((Series)this.tempSeries).deleteObject(Integer.parseInt(idText.getText()));
                seriesTableList = FXCollections.observableArrayList(((Series)this.tempSeries).getBooks());
                seriesTableView.setItems(seriesTableList);
                int temp = ApplicationStarter.getCurrentCollection().getCollectionSize();
                ApplicationStarter.getCurrentCollection().setCollectionSize(temp - 1);
            }

            resetText();
            totalText.setText(String.valueOf(ApplicationStarter.getCurrentCollection().getCollectionSize()));
        }
    }

    public void onSearch(ActionEvent event) {

        ArrayList<DbObject> temp = new ArrayList<DbObject>();

        if(!Objects.equals(searchTextField.getText(), "")) {
            collectionTableList = FXCollections.observableArrayList(ApplicationStarter.getCurrentCollection().getCollection());
            collectionTableView.setItems(collectionTableList);
            for(DbObject object : ApplicationStarter.getCurrentCollection().getCollection()) {
                if(object.getName().contains(searchTextField.getText())) {
                    temp.add(object);
                }
            }
            collectionTableList = FXCollections.observableArrayList(temp);
            collectionTableView.setItems(collectionTableList);
            seriesTableView.getItems().clear();
        }
    }

    public void onFilter(ActionEvent event) {
        ArrayList<DbObject> temp = new ArrayList<DbObject>();
        collectionTableList = FXCollections.observableArrayList(ApplicationStarter.getCurrentCollection().getCollection());
        collectionTableView.setItems(collectionTableList);
        for(DbObject object : ApplicationStarter.getCurrentCollection().getCollection()) {
            if(Objects.equals(object.getClassName(), filterChoice.getValue())) {
                temp.add(object);
            }
        }
        collectionTableList = FXCollections.observableArrayList(temp);
        collectionTableView.setItems(collectionTableList);
        seriesTableView.getItems().clear();
    }

    public void onReset(ActionEvent event) {
        collectionTableList = FXCollections.observableArrayList(ApplicationStarter.getCurrentCollection().getCollection());
        collectionTableView.setItems(collectionTableList);
        seriesTableView.getItems().clear();
    }



    public void resetText() {
        titleText.setText("N/A");
        idText.setText("N/A");
        authorText.setText("N/A");
        typeText.setText("N/A");
        publisherText.setText("N/A");
        volumeText.setText("N/A");
        volumeLabel.setText("Volume:");
        uniqueText1.setText("");
        uniqueText2.setText("");
        uniqueLabel1.setText("");
        uniqueLabel2.setText("");
    }
}
