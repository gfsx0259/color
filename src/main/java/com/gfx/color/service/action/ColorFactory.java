package com.gfx.color.service.action;

import com.gfx.color.entity.action.Action;
import com.gfx.color.entity.action.Color;
import com.gfx.color.entity.action.State;
import com.gfx.color.service.ActionFactory;

public class ColorFactory extends ActionFactory {

    private final javafx.scene.paint.Color color;

    public ColorFactory(javafx.scene.paint.Color color) {
        this.color = color;
    }

    @Override
    public Action factory() {

        int Hue = ((int) this.color.getHue());
        int Saturation = ((int) (this.color.getSaturation() * 100));
        int Brightness = ((int) (this.color.getBrightness() * 100));

        Color color = new Color(Hue, Saturation, Brightness);
        State state = new State("hsv", color);

        return new Action(
            "devices.capabilities.color_setting",
            state
        );
    }
}
