package com.gfx.color.infrastructure;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;

public class HttpClient {
    public HttpResponse post(String uri, String body, String token) throws IOException {
        HttpPost httpPost = new HttpPost(uri);

        httpPost.addHeader("Authorization", token);
        httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));

        return this.fetch(httpPost);
    }

    public HttpResponse get(String uri, String token) throws IOException {
        HttpGet httpGet = new HttpGet(uri);

        httpGet.addHeader("Authorization", token);

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
