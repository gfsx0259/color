package com.gfx.color.controller;

import com.gfx.color.entity.info.Group;
import com.gfx.color.infrastructure.HttpClient;
import com.gfx.color.service.client.YandexApiClient;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;

public class HelloController {
    @FXML
    public ComboBox<Group> groups;

    @FXML
    public ColorPicker colorPicker;

    @FXML
    public TextField token;

    private YandexApiClient client;

    @FXML
    public void initialize() throws IOException {
        this.client = new YandexApiClient(new HttpClient(this.token.getText()));

        this.groups.setItems(FXCollections.observableArrayList(this.client.getInfo().getGroups()));
        this.groups.getSelectionModel().selectFirst();
    }

    @FXML
    protected void onColorPicked() throws IOException {
        this.client.setColor(colorPicker.getValue());
    }
}