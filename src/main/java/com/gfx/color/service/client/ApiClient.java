package com.gfx.color.service.client;

import com.gfx.color.entity.action.Action;
import com.gfx.color.entity.action.Request;
import com.gfx.color.infrastructure.HttpClient;
import com.gfx.color.infrastructure.HttpResponse;
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
    private static final String ENDPOINT_ACTION = "/groups/%s/actions";
    private final HttpClient httpClient;
    private final AuthClient authClient;

    public ApiClient(HttpClient httpClient, AuthClient authClient) {
        this.httpClient = httpClient;
        this.authClient = authClient;
    }

    public InfoResponse getInfo() throws IOException {
        String token = this.authClient.getToken();

        HttpResponse response = this.httpClient.get(
            API_URI.concat(ENDPOINT_INFO),
            token
        );
        return new InfoResponse(
            response.asJson()
        );
    }

    public void setColor(Color color, String groupId) throws IOException {
        Action action = ActionFactory.createColor(color).factory();
        String token = this.authClient.getToken();

        this.httpClient.post(
            API_URI.concat(ENDPOINT_ACTION.formatted(groupId)),
            this.buildRequest(action),
            token
        );
    }

    public void setState(boolean state, String groupId) throws IOException {
        Action action = ActionFactory.createState(state).factory();
        String token = this.authClient.getToken();

        this.httpClient.post(
                API_URI.concat(ENDPOINT_ACTION.formatted(groupId)),
                this.buildRequest(action),
                token
        );
    }

    private String buildRequest(Action action) {
        List<Action> actions = Collections.singletonList(action);
        return (new Gson())
                .toJson(new Request(actions));
    }
}
