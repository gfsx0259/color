package com.gfx.color.service.client;

import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthClient {
    private static final String OAUTH_URI = "https://oauth.yandex.ru/authorize";
    public static final String OAUTH_CLIENT_ID = "12f2f8f043e1425f81d9e72dcaef9d97";

    private final WebEngine webEngine;
    private String token;

    List<AuthClientListener> listeners = new ArrayList<>();

    public AuthClient(WebEngine webEngine, String token) throws URISyntaxException {
        this.webEngine = webEngine;
        this.token = token;

        if (this.token.isEmpty()) {
            this.auth();
        }
    }

    public void addListener(AuthClientListener listener) {
        listeners.add(listener);
    }

    public String getToken() {
        return token;
    }

    private void auth() throws URISyntaxException {
        URIBuilder UriBuilder = new URIBuilder(OAUTH_URI);
        UriBuilder
                .setParameter("response_type", "token")
                .setParameter("client_id", OAUTH_CLIENT_ID);

        webEngine.load(UriBuilder.build().toString());
        webEngine.getLoadWorker().stateProperty().addListener((ov, t, t1) -> {
            if (t1.equals(Worker.State.SUCCEEDED) && webEngine.getDocument().getDocumentURI().contains("access_token")) {
                try {
                    this.token = parseToken(webEngine.getDocument().getDocumentURI());

                    for (AuthClientListener listener : listeners) {
                        listener.invoke();
                    }
                } catch (URISyntaxException| IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String parseToken(String Uri) throws URISyntaxException {
        URIBuilder UriBuilder = new URIBuilder();
        UriBuilder.setQuery(new URI(Uri).getFragment());

        Map<String, String> params = UriBuilder.getQueryParams()
                .stream()
                .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));

        return StringUtils.capitalize(params.get("token_type")) + " " + params.get("access_token");
    }
}
