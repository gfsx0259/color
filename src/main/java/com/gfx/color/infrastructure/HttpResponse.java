package com.gfx.color.infrastructure;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpResponse {
    private final org.apache.http.HttpResponse response;

    public HttpResponse(org.apache.http.HttpResponse response) {
        this.response = response;
    }

    public String asString() throws IOException {
        return EntityUtils
                .toString(this.response.getEntity());
    }

    public JsonObject asJson() throws IOException {
        return (new Gson())
                .fromJson(this.asString(), JsonObject.class);
    }
}
