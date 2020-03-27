package View;

import Controller.ControllerMenu;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import Controller.ControllerJeu;
import Model.*;

import java.awt.*;

public class ViewJeu {

    public Canvas gridCanvas;
    public GraphicsContext gc;

    public Label gameOverLabel;
    private boolean gameOverVis = false;

    private Game game;
    private double initX;
    private double initY;
    private double fullScale = 1;
    private int[][] turnArray = new int[ControllerMenu.getxValue()][ControllerMenu.getyValue()];

    public ViewJeu(Game game) {
        this.game = game;
    }

    public void makeScene(Scene scene, LeSnake snake, Proie proie) {
        Scene gameScene = scene;
        gridCanvas = new Canvas(gameScene.getWidth(), gameScene.getHeight());
        initX = scene.getWidth();
        initY = scene.getHeight();
        gc = gridCanvas.getGraphicsContext2D();
        drawGrid(proie, snake, gc);

        gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setId("gameOverLabel");
        gameOverLabel.setVisible(false);

    }


    public void drawGrid(Proie proie, LeSnake snek, GraphicsContext gc) {
        gc.setFill(game.getColor());
        gc.fillRect(0, 0, gridCanvas.getWidth(), gridCanvas.getHeight());

        gc.drawImage(proie.getImage(), (proie.getPos().x * game.getMultiplier()) * fullScale, (proie.getPos().y * game.getMultiplier()) * fullScale, game.getMultiplier() * fullScale - 3 * fullScale, game.getMultiplier() * fullScale - 3 * fullScale);

        int bodyIndex = 0;
        for (Point point : snek.getBody()) {
            drawBody(snek, point, bodyIndex);
            bodyIndex++;
        }

        drawHead(snek);

    }

    private void drawHead(LeSnake snek) {
        snek.getImageViewHead().setRotate(snek.getSnakeDir());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = snek.getImageViewHead().snapshot(params, null);
        gc.drawImage(rotatedImage, (snek.getHead().x * game.getMultiplier()) * fullScale, (snek.getHead().y * game.getMultiplier()) * fullScale, game.getMultiplier() * fullScale * fullScale, game.getMultiplier() * fullScale * fullScale);
    }


    private void drawBody(LeSnake snek, Point point, int index) {
        Image rotatedImage;
        if (snek.getBody().size() <= 1) {
            snek.getImageViewTail().setRotate(snek.getSnakeDir());
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            rotatedImage = snek.getImageViewTail().snapshot(params, null);
        } else if (index == 0) {
            int rotateTail;
            if (snek.getBody().get(1).getX() == snek.getBody().get(0).getX() && snek.getBody().get(1).getY() == snek.getBody().get(0).getY() - 1) {
                rotateTail = 0;
            } else if (snek.getBody().get(1).getX() == snek.getBody().get(0).getX() && snek.getBody().get(1).getY() == snek.getBody().get(0).getY() + 1) {
                rotateTail = 180;
            } else if (snek.getBody().get(1).getX() == snek.getBody().get(0).getX() - 1 && snek.getBody().get(1).getY() == snek.getBody().get(0).getY()) {
                rotateTail = 270;
            } else {
                rotateTail = 90;
            }
            snek.getImageViewTail().setRotate(rotateTail);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            rotatedImage = snek.getImageViewTail().snapshot(params, null);
        } else if (index > 0 && (turnArray[(int) snek.getBody().get(index).getX()][(int) snek.getBody().get(index).getY()] != 0)) {
            snek.getImageViewTurn().setRotate(90 * turnArray[(int) snek.getBody().get(index).getX()][(int) snek.getBody().get(index).getY()]);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            rotatedImage = snek.getImageViewTurn().snapshot(params, null);
        } else {
            snek.getImageViewBody().setRotate(snek.getBodyDir(index));
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            rotatedImage = snek.getImageViewBody().snapshot(params, null);
        }

        if (turnArray[(int) snek.getBody().get(0).getX()][(int) snek.getBody().get(0).getY()] != 0) {
            turnArray[(int) snek.getBody().get(0).getX()][(int) snek.getBody().get(0).getY()] = 0;
        }

        gc.drawImage(rotatedImage, (point.x * game.getMultiplier()) * fullScale, (point.y * game.getMultiplier()) * fullScale, game.getMultiplier() * fullScale * fullScale, game.getMultiplier() * fullScale * fullScale);
    }

    public void turnDir(LeSnake snek) {
        if ((ControllerJeu.lastKey == KeyCode.LEFT && ControllerJeu.key == KeyCode.UP) || (ControllerJeu.lastKey == KeyCode.DOWN && ControllerJeu.key == KeyCode.RIGHT)) {
            turnArray[(int) snek.getHead().getX()][(int) snek.getHead().getY()] = 2;
        } else if ((ControllerJeu.lastKey == KeyCode.LEFT && ControllerJeu.key == KeyCode.DOWN) || (ControllerJeu.lastKey == KeyCode.UP && ControllerJeu.key == KeyCode.RIGHT)) {
            turnArray[(int) snek.getHead().getX()][(int) snek.getHead().getY()] = 3;
        } else if ((ControllerJeu.lastKey == KeyCode.RIGHT && ControllerJeu.key == KeyCode.UP) || (ControllerJeu.lastKey == KeyCode.DOWN && ControllerJeu.key == KeyCode.LEFT)) {
            turnArray[(int) snek.getHead().getX()][(int) snek.getHead().getY()] = 1;
        } else if ((ControllerJeu.lastKey == KeyCode.UP && ControllerJeu.key == KeyCode.LEFT) || (ControllerJeu.lastKey == KeyCode.RIGHT && ControllerJeu.key == KeyCode.DOWN)) {
            turnArray[(int) snek.getHead().getX()][(int) snek.getHead().getY()] = 4;
        } else turnArray[(int) snek.getHead().getX()][(int) snek.getHead().getY()] = 0;
    }

    public void gameOver(LeSnake snek) {
        if (snek.getDead() && !gameOverVis) {
            gameOverVis = true;
            gameOverLabel.setVisible(true);
        } else if (!snek.getDead() && gameOverVis) {
            gameOverLabel.setVisible(false);
            gameOverVis = false;
        }
    }

}