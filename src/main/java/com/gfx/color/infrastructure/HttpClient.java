package com.gfx.color.infrastructure;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;

public class HttpClient {
    private final String url = "https://api.iot.yandex.net/v1.0/";
    private final String token;

    public HttpClient(String token) {
        this.token = token;
    }

    public HttpResponse post(String action, String body) throws IOException {
        HttpPost httpPost = new HttpPost(this.url.concat(action));

        httpPost.addHeader("Authorization", this.token);
        httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));

        return HttpClientBuilder
                .create()
                .build()
                .execute(httpPost);
    }

    public HttpResponse get(String action) throws IOException {
        HttpGet httpGet = new HttpGet(this.url.concat(action));

        httpGet.addHeader("Authorization", this.token);

        return HttpClientBuilder
                .create()
                .build()
                .execute(httpGet);
    }
}
