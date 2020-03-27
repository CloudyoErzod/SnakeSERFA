package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import Model.*;
import View.ViewJeu;

import java.util.Random;

public class ControllerJeu {
    private LeSnake snake;
    private Proie proie;
    private ViewJeu viewJeu;
    private Game game;
    private Stage stage;


    public static KeyCode key = KeyCode.LEFT;
    public static KeyCode lastKey = KeyCode.LEFT;
    private boolean keyPressed = false;

    public ControllerJeu(LeSnake snake, Proie proie, Game game, ViewJeu viewJeu, Stage stage) {
        this.snake = snake;
        this.proie = proie;
        this.viewJeu = viewJeu;
        this.game = game;
        this.stage = stage;

        animation.setCycleCount(Animation.INDEFINITE);
        animation.setRate(game.getSpeed());
    }

    private final Timeline animation = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        if (snake.getDead()) {
            return;
        } else {

            if (key.equals(KeyCode.LEFT)) {
                snake.move('L');
            } else if (key.equals(KeyCode.RIGHT)) {
                snake.move('R');
            } else if (key.equals(KeyCode.UP)) {
                snake.move('U');
            } else if (key.equals(KeyCode.DOWN)) {
                snake.move('D');
            }

            if (!snake.getDead() && snake.ateApple(proie)) {
                game.incScore();

                newApple();
            }

            keyPressed = false;

            if (!snake.getDead() || snake.hitSelf()) {
                viewJeu.drawGrid(proie, snake, viewJeu.gc);
            }

            viewJeu.gameOver(snake);
        }
    }));

    public void handle(KeyEvent event) {
        if (event.getCode().isArrowKey()) animation.play();

        if (valKey(event.getCode()) && !keyPressed) {
            lastKey = key;
            key = event.getCode();
            keyPressed = true;
            viewJeu.turnDir(snake);
        }
    }


    private boolean valKey(KeyCode key) {
        if (!key.isArrowKey()) return false;
        else {
            if (key.equals(KeyCode.LEFT)) if (!this.key.equals(KeyCode.RIGHT)) return true;
            if (key.equals(KeyCode.RIGHT)) if (!this.key.equals(KeyCode.LEFT)) return true;
            if (key.equals(KeyCode.DOWN)) if (!this.key.equals(KeyCode.UP)) return true;
            if (key.equals(KeyCode.UP)) return !this.key.equals(KeyCode.DOWN);
            return false;
        }
    }

    private void newApple() {
        if (game.isPowerUp()) {
            Random rnd = new Random();
            int ran = rnd.nextInt(12);
            if (ran == 1) {
                proie = new Proie(game.getWidth(), game.getHeight(), snake);
            } else if (ran == 2) {
                proie = new Proie(game.getWidth(), game.getHeight(), snake);
            } else if (ran == 3) {
                proie = new Proie(game.getWidth(), game.getHeight(), snake);
            } else {
                proie = new Proie(game.getWidth(), game.getHeight(), snake);
            }
        } else {
            proie = new Proie(game.getWidth(), game.getHeight(), snake);
        }
    }
}