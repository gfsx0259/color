package com.gfx.color.entity.action.state;

import com.gfx.color.entity.action.Color;

public class ColorState implements State {
    public String instance;
    public Color value;

    public ColorState(String instance, Color color) {
        this.instance = instance;
        this.value = color;
    }
}
