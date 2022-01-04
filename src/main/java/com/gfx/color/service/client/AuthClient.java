package com.gfx.color.service.client;

import javafx.scene.web.WebEngine;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthClient {
    private static final String OAUTH_URI = "https://oauth.yandex.ru/authorize";
    public static final String OAUTH_CLIENT_ID = "12f2f8f043e1425f81d9e72dcaef9d97";

    private final WebEngine webEngine;

    public AuthClient(WebEngine webEngine) {
        this.webEngine = webEngine;
    }

    public void auth() throws URISyntaxException {
        URIBuilder UriBuilder = new URIBuilder(OAUTH_URI);
        UriBuilder.setParameter("response_type", "token");
        UriBuilder.setParameter("client_id", OAUTH_CLIENT_ID);

        webEngine.load(UriBuilder.build().toString());
    }

    public String parseToken() throws URISyntaxException {
        URIBuilder UriBuilder = new URIBuilder();
        UriBuilder.setQuery(new URI(webEngine.getDocument().getDocumentURI()).getFragment());

        Map<String, String> params = UriBuilder.getQueryParams()
                .stream()
                .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));

        return StringUtils.capitalize(params.get("token_type")) + " " + params.get("access_token");
    }
}
