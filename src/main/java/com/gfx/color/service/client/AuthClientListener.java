package com.gfx.color.service.client;

import java.io.IOException;

@FunctionalInterface
public interface AuthClientListener {
    void invoke() throws IOException;
}
