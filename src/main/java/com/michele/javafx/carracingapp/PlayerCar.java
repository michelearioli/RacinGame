package com.michele.javafx.carracingapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class PlayerCar {
    private final Image carImage;
    private int xpos;
    private double ypos;

    public PlayerCar(int xpos, double ypos) {
        carImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/gamecar3.png")));
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(carImage, xpos, ypos, 100, 175);
    }

    public void moveLeft() {
        xpos -= 100;
        if (xpos < 100) {
            xpos = 100;
        }
    }

    public void moveRight() {
        xpos += 100;
        if (xpos > 500) {
            xpos = 500;
        }
    }

    public void move() {
        ypos -= 40;
        if (ypos < 500) {
            ypos = 500;
        }
    }

    public boolean collidesWith(OpponentCar car) {
        return (ypos < car.getY() && ypos + 175 > car.getY() && xpos == car.getX());
    }

    public void reset() {
        xpos = 300;
        ypos = 700;
    }
}
