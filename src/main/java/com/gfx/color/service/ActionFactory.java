package com.gfx.color.service;

import com.gfx.color.entity.action.Action;
import com.gfx.color.service.action.ColorFactory;
import com.gfx.color.service.action.StateFactory;
import javafx.scene.paint.Color;

public abstract class ActionFactory {
    public abstract Action factory();

    public static ActionFactory createColor(Color color) {
        return new ColorFactory(color);
    }

    public static ActionFactory createState(boolean state) {
        return new StateFactory(state);
    }
}
