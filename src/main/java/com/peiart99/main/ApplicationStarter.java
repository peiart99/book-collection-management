package com.peiart99.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationStarter extends Application {

    private static Collection currentCollection = new Collection("collection");

    public static void addToCollection(DbObject book) {currentCollection.addBook(book);}

    public static Collection getCurrentCollection() {
        return currentCollection;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStarter.class.getResource("/com/peiart99/views/start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Collection Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}