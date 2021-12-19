package com.gfx.color.service.response;

import com.gfx.color.entity.info.Group;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class InfoResponse {
    private JsonObject response;

    public InfoResponse(JsonObject response) {
        this.response = response;
    }

    public Group[] getGroups() {
        return (new Gson()).fromJson(this.response.get("groups"), Group[].class);
    }
}
