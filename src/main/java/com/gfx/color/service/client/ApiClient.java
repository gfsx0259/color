package com.gfx.color.service.client;

import com.gfx.color.entity.action.Action;
import com.gfx.color.entity.action.Request;
import com.gfx.color.infrastructure.HttpClient;
import com.gfx.color.service.ActionFactory;
import com.gfx.color.service.response.InfoResponse;
import com.google.gson.Gson;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ApiClient {

    private static final String API_URI = "https://api.iot.yandex.net/v1.0/";
    private static final String ENDPOINT_INFO = "/user/info";
    private static final String ENDPOINT_ACTION = "/groups/4ccc5cbf-0b37-46cf-b0c4-2899668cdda0/actions";
    private final HttpClient httpClient;

    public ApiClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public InfoResponse getInfo() throws IOException {
        return new InfoResponse(this.httpClient.get(API_URI.concat(ENDPOINT_INFO)).asJson());
    }

    public void setColor(Color color) throws IOException {
        Action action = ActionFactory.createColor(color).factory();

        this.httpClient.post(API_URI.concat(ENDPOINT_ACTION), this.buildRequest(action));
    }

    private String buildRequest(Action action) {
        List<Action> actions = Collections.singletonList(action);
        return (new Gson())
                .toJson(new Request(actions));
    }
}
