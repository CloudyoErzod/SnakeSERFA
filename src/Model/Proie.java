package Model;

import javafx.scene.image.Image;
import java.awt.*;
import java.util.Random;


public class Proie {
    private Point pos;
    private Image image = new Image("Asset/snakeapple.png");

    public Proie(int x, int y, LeSnake snek) {
        int tx;
        int ty;
        do {
            Random rnd = new Random();
            tx = rnd.nextInt(x);
            ty = rnd.nextInt(y);
            pos = new Point(tx, ty);
        } while (snek.getHead().equals(pos) || snek.getBody().contains(pos));
    }

    public Point getPos() {
        return new Point(pos);
    }

    public Image getImage() {
        return image;
    }
}