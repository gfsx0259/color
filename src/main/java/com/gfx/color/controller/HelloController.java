package com.gfx.color.controller;

import com.gfx.color.entity.info.Group;
import com.gfx.color.infrastructure.HttpClient;
import com.gfx.color.service.client.ApiClient;
import com.gfx.color.service.client.AuthClient;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URISyntaxException;

public class HelloController {
    @FXML
    public ComboBox<Group> groups;
    public ColorPicker colorPicker;
    public WebView browser;

    private ApiClient apiClient;

    @FXML
    public void initialize() throws URISyntaxException {
        setModeGuest();

        AuthClient authClient = new AuthClient(browser.getEngine(), "");
        authClient.addListener(() -> {
            this.groups.setItems(FXCollections.observableArrayList(this.apiClient.getInfo().getGroups()));
            this.groups.getSelectionModel().selectFirst();

            setModeAuthorized();
        });

        this.apiClient = new ApiClient(new HttpClient(), authClient);
    }

    @FXML
    protected void onColorPicked() throws IOException {
        this.apiClient.setColor(colorPicker.getValue(), this.groups.getId());
    }

    private void setModeGuest() {
        groups.setVisible(false);
        colorPicker.setVisible(false);
        browser.setVisible(true);
    }

    private void setModeAuthorized() {
        groups.setVisible(true);
        colorPicker.setVisible(true);
        browser.setVisible(false);
    }
}
