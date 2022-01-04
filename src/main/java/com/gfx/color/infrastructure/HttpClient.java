package com.gfx.color.infrastructure;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;

public class HttpClient {
    private final String token;

    public HttpClient(String token) {
        this.token = token;
    }

    public HttpResponse post(String uri, String body) throws IOException {
        HttpPost httpPost = new HttpPost(uri);

        httpPost.addHeader("Authorization", this.token);
        httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));

        return this.fetch(httpPost);
    }

    public HttpResponse get(String uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);

        httpGet.addHeader("Authorization", this.token);

        return this.fetch(httpGet);
    }

    private HttpResponse fetch(HttpUriRequest request) throws IOException {
        return new HttpResponse(HttpClientBuilder
                .create()
                .build()
                .execute(request)
        );
    }
}
