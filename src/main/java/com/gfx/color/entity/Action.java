package com.gfx.color.entity;

public class Action {
    public String type;
    public State state;

    public Action(String type, State state) {
        this.type = type;
        this.state = state;
    }
}
