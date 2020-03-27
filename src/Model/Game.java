package Model;

import javafx.scene.paint.Color;

public class Game {
    private Color color = Color.web("a2f452");
    private int width;
    private int height;
    private int score;
    private int maxScore;
    private double multiplier;
    private double speed;
    private boolean powerUp;
    private boolean noBorder;

    public Game(int x, int y, boolean powerUp, char difficulty) {
        if (difficulty == 'E') {
            speed = 8.5;
            noBorder = true;
        } else if (difficulty == 'N') {
            speed = 8.5;
            noBorder = false;
        } else if (difficulty == 'H') {
            speed = 13;
            noBorder = false;
        }
        this.powerUp = powerUp;
        width = x;
        height = y;
        maxScore = x * y - 2;
        this.powerUp = powerUp;
        if (height > 50) {
            multiplier = 10;
        } else {
            multiplier = 20;
        }
    }

    public void incScore() {
        score++;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public boolean isPowerUp() {
        return powerUp;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isNoBorder() {
        return noBorder;
    }
}
