package com.gfx.color.task;

import com.gfx.color.infrastructure.ColorDetector;
import com.gfx.color.service.client.ApiClient;
import javafx.application.Platform;
import java.io.IOException;
import java.util.TimerTask;

public class ChangeColorTask extends TimerTask {
    private final ApiClient apiClient;
    private final ColorDetector colorDetector;
    private final String groupId;

    public ChangeColorTask(ApiClient apiClient, ColorDetector colorDetector, String groupId)
    {
        this.apiClient = apiClient;
        this.colorDetector = colorDetector;
        this.groupId = groupId;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            try {
                this.apiClient.setColor(this.colorDetector.detect(), this.groupId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
