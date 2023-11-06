package com.michele.javafx.carracingapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Road {
    private double roadOffset = 0;
    public void drawRoad(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, 700, 700);
        gc.setFill(Color.rgb(159, 135, 114));
        gc.fillRect(90, 0, 10, 700);
        gc.fillRect(600, 0, 10, 700);
        gc.fillRect(100, 0, 500, 700);

        for (int i = 0; i <= 700; i += 100) {
            gc.setFill( Color.WHITE);
            gc.fillRect(350, i + roadOffset, 10, 70);
            update();
        }
    }
    public void update() {
        roadOffset += 5;
        if (roadOffset >= 100) {
            roadOffset = 0;
        }
    }
}
