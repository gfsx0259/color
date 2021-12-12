package com.gfx.color.service;

import com.gfx.color.entity.Action;
import com.gfx.color.entity.Request;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.List;

public class RequestBuilder {
    public String build(Action action) {
        List<Action> actions = Collections.singletonList(action);

        Gson gson = new Gson();
        return gson
                .toJson(new Request(actions));
    }
}
