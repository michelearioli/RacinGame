package com.michele.javafx.carracingapp;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Objects;
import java.util.random.RandomGenerator;

public class CarRacingController {

    AnimationTimer timer;

    private PlayerCar playerCar;
    private OpponentCar[] opponentCars;
    private Road road;
    private Tree[] tree;
    private boolean gameover;
    private int score;
    private int speed;
    private int delay;
    Sound sound = new Sound();

    private final GraphicsContext gc;

    public CarRacingController(GraphicsContext gc) {
        this.gc = gc;
    }

    public void handlePlay() {
        initializeGameObjects();
        initializeTimer();
        playMusic(0);
    }
    private void initializeGameObjects() {
        playerCar = new PlayerCar(300, 700);
        road = new Road();

        opponentCars = new OpponentCar[3];
        opponentCars[0] = new OpponentCar(new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/gamecar1.png"))));
        opponentCars[1] = new OpponentCar(new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/gamecar2.png"))));
        opponentCars[2] = new OpponentCar(new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/gamecar4.png"))));

        tree = new Tree[4];
        tree[0] = new Tree(0);
        tree[1] = new Tree(0);
        tree[2] = new Tree(600);
        tree[3] = new Tree(600);
    }
    private void initializeTimer() {
        if (timer != null) {
            timer.stop();
        }
        timer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 100_000_000) {
                    mainLoop();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }
    private void mainLoop() {
        if (!gameover) {
            playerCar.move();
            RandomGenerator rg = RandomGenerator.getDefault();
            for (OpponentCar car : opponentCars) {
                car.move(rg.nextInt(1,6));
            }
            for (Tree tr : tree) {
                tr.move();
            }
            checkCollisions();
            score++;
            speed++;

            if (speed > 140) {
                speed = 240 - delay;
            }

            if (score % 50 == 0) {
                delay -= 10;
                if (delay < 60) {
                    delay = 60;
                }
            }
            drawGame();
        }
    }
    void keyPressed(KeyEvent event) {
        if (!gameover) {
            switch (event.getCode()) {
                case LEFT -> playerCar.moveLeft();
                case RIGHT -> playerCar.moveRight();
                case Q -> Platform.exit();
            }
        } else if (event.getCode() == KeyCode.ENTER) {
            restartGame();
        }
    }
    void drawGame() {
        gc.clearRect(0, 0, 700, 700);
        road.drawRoad(gc);

        if (!gameover) {
            playerCar.draw(gc);

            for (OpponentCar car : opponentCars) {
                car.draw(gc);
            }
            for (Tree tr : tree) {
                tr.draw(gc);
            }
            gc.setFont(new javafx.scene.text.Font("MV Boli", 30));
            gc.fillText("Score: " + score, 100, 30);
            gc.fillText(speed + " Km/h", 500, 30);
        } else {
            gc.setFill(javafx.scene.paint.Color.GRAY);
            gc.fillRect(120, 210, 460, 200);
            gc.setFill(javafx.scene.paint.Color.DARKGRAY);
            gc.fillRect(130, 220, 440, 180);
            gc.setFont(new javafx.scene.text.Font("MV Boli", 50));
            gc.setFill(javafx.scene.paint.Color.RED);
            gc.fillText("Game Over!", 210, 260);
            gc.setFill(javafx.scene.paint.Color.WHITE);
            gc.setFont(new javafx.scene.text.Font("MV Boli", 30));
            gc.fillText("Score: " + score, 190, 300);
            gc.fillText("Press Enter to Restart", 190, 340);
        }
    }

    private void checkCollisions() {
        for (OpponentCar car : opponentCars) {
            if (playerCar.collidesWith(car)) {
                gameover = true;
                stopMusic();
                sound.setFile(1);
                sound.play();
                drawGame();
                break;
            }
        }
    }
    private void restartGame() {
        playerCar.reset();
        for (OpponentCar car : opponentCars) {
            car.reset();
        }
        gameover = false;
        playMusic(0);
        score = 0;
        speed = 90;
        delay = 100;
        drawGame();
    }
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {
        sound.stop();
    }
}
