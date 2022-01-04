package com.gfx.color.controller;

import com.gfx.color.entity.info.Group;
import com.gfx.color.infrastructure.HttpClient;
import com.gfx.color.service.client.ApiClient;
import com.gfx.color.service.client.AuthClient;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URISyntaxException;

public class HelloController {
    @FXML
    public ComboBox<Group> groups;
    public ColorPicker colorPicker;
    public Button getToken;
    public WebView browser;

    private ApiClient apiClient;
    private AuthClient authClient;

    @FXML
    public void initialize() throws URISyntaxException {
        this.authClient = new AuthClient(browser.getEngine());
        this.authClient.auth();
    }

    @FXML
    protected void onColorPicked() throws IOException {
        this.apiClient.setColor(colorPicker.getValue());
    }

    @FXML
    public void onTokenClick() throws URISyntaxException, IOException {

        String token = this.authClient.parseToken();

        this.apiClient = new ApiClient(new HttpClient(token));

        this.groups.setItems(FXCollections.observableArrayList(this.apiClient.getInfo().getGroups()));
        this.groups.getSelectionModel().selectFirst();
    }
}
