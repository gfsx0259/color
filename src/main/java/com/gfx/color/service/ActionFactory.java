package com.gfx.color.service;

import com.gfx.color.entity.Action;
import com.gfx.color.service.action.ColorFactory;
import javafx.scene.paint.Color;

public abstract class ActionFactory {
    public abstract Action factory();

    public static ActionFactory createColor(Color color) {
        return new ColorFactory(color);
    }
}
