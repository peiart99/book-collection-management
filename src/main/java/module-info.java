module com.peiart99.bookcollectionmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.peiart99.main to javafx.fxml;
    exports com.peiart99.main;
    exports com.peiart99.controllers;
    opens com.peiart99.controllers to javafx.fxml;
}