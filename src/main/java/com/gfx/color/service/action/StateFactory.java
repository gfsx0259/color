package com.gfx.color.service.action;

import com.gfx.color.entity.action.Action;
import com.gfx.color.entity.action.state.EnabledState;
import com.gfx.color.service.ActionFactory;

public class StateFactory extends ActionFactory {

    private final boolean state;

    public StateFactory(boolean state) {
        this.state = state;
    }

    @Override
    public Action factory() {

        EnabledState state = new EnabledState("on", this.state);

        return new Action(
            "devices.capabilities.on_off",
            state
        );
    }
}
