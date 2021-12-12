package com.gfx.color.controller;

import com.gfx.color.entity.Action;
import com.gfx.color.infrastructure.HttpClient;
import com.gfx.color.service.ActionFactory;
import com.gfx.color.service.RequestBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import java.io.IOException;

public class HelloController {
    @FXML
    public ColorPicker colorPicker;

    @FXML
    public TextField token;

    @FXML
    protected void onColorPicked() throws IOException {
        Action action = ActionFactory
                .createColor(colorPicker.getValue())
                .factory();

        HttpClient requestBuilder = new HttpClient(this.token.getText());
        requestBuilder
                .post(
                    "/groups/4ccc5cbf-0b37-46cf-b0c4-2899668cdda0/actions",
                        new RequestBuilder().build(action)
                );
    }
}