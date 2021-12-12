module com.gfx.color {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    opens com.gfx.color to javafx.fxml;
    exports com.gfx.color;
    exports com.gfx.color.entity;
    exports com.gfx.color.controller;
    opens com.gfx.color.controller to javafx.fxml;
}