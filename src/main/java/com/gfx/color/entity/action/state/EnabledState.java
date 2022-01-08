package com.gfx.color.entity.action.state;

public class EnabledState implements State {
    public String instance;
    public boolean value;

    public EnabledState(String instance, boolean value) {
        this.instance = instance;
        this.value = value;
    }
}
