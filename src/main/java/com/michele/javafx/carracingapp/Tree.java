package com.michele.javafx.carracingapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Tree {
    private final Image treeImage;
    private final double xpos;
    private double ypos;


    public Tree(double xpos) {
        treeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/tree.png")));
        this.xpos = xpos;
        ypos = -240 * Math.random() * 5;
    }
    public void draw(GraphicsContext gc) {
        gc.drawImage(treeImage, xpos, ypos, 100, 175);
    }
    public void move() {
        ypos += 50;
        if (ypos > 700) {
            ypos = -240 * Math.random() * 5;
        }
    }

}
