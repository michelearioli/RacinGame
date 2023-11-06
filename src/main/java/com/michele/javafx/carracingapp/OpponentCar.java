package com.michele.javafx.carracingapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.random.RandomGenerator;

public class OpponentCar {
    private final Image carImage;
    private int xpos;
    private double ypos;

    public OpponentCar(Image carImage) {
        this.carImage = carImage;
        RandomGenerator rg = RandomGenerator.getDefault();
        xpos = 100 * rg.nextInt(1,6);

        ypos = -240 * Math.random() * 5;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(carImage, xpos, ypos, 100, 175);
    }

    public void move(int random) {
        ypos += 50;
        if (ypos > 700) {
            xpos = 100 * random;
            ypos = -240 * Math.random() * 5;
        }
    }

    public double getX() {
        return xpos;
    }

    public double getY() {
        return ypos;
    }

    public void reset() {
        RandomGenerator rg = RandomGenerator.getDefault();
        xpos = 100 * rg.nextInt(1,6);
        ypos = -240 * Math.random() * 5;
    }
}