package com.gfx.color.infrastructure;

import de.androidpit.colorthief.ColorThief;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;

import java.awt.*;

public class ColorDetector {
    public Color detect() {

            Robot robot = new Robot();

            double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

            Rectangle2D rectangle2D = new Rectangle2D(0, 0, width, height);

            WritableImage WritableImage = new WritableImage((int) width, (int) height);
            WritableImage writableImage = robot.getScreenCapture(WritableImage, rectangle2D);

            int[] color = ColorThief.getColor(
                SwingFXUtils.fromFXImage(writableImage, null)
            );

            return Color.rgb(
                color[0],
                color[1],
                color[2]
            );
    }
}
