package com.michele.javafx.carracingapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Objects;

public class CarRacingApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Racing Game");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/carIcon.png"))));
        Canvas canvas = new Canvas(700, 700);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        CarRacingController controller = new CarRacingController(gc);

        controller.handlePlay();
        scene.setOnKeyPressed(controller::keyPressed);
        primaryStage.show();
    }
}
