package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.util.ArrayList;


public class LeSnake {
    private boolean dead = false;
    private final Point head = new Point();
    private final ArrayList<Point> body = new ArrayList<>();
    private final int gx;
    private final int gy;
    private boolean ignoreBorders = false;
    private boolean noBorder;
    private Image imgInputHead = new Image("Asset/snakehead.png");
    private ImageView imageHead = new ImageView(imgInputHead);
    private Image imgInputBody = new Image("Asset/snakebody.png");
    private ImageView imageBody = new ImageView(imgInputBody);
    private Image imgInputTurn = new Image("Asset/snaketurnleft.png");
    private ImageView imageTurn = new ImageView(imgInputTurn);
    private Image imgInputTail = new Image("Asset/snaketail.png");
    private ImageView imageTail = new ImageView(imgInputTail);
    private int snakeDir = 270;

    public LeSnake(int x, int y, boolean noBorder) {
        this.noBorder = noBorder;
        this.head.setLocation(x / 2, y / 2);
        this.body.add(new Point(x / 2 + 1, y / 2));
        this.gx = x;
        this.gy = y;
    }

    public void move(char dir) {
        body.add(new Point(head));
        switch (dir) {
            case 'U':
                snakeDir = 0;
                head.translate(0, -1);
                if (borderControl() && (!ignoreBorders && !noBorder)) {
                    head.translate(0, 1);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(head.getX(), gy - 1);
                }
                break;

            case 'D':
                snakeDir = 180;
                head.translate(0, 1);
                if (borderControl() && (!ignoreBorders && !noBorder)) {
                    head.translate(0, -1);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(head.getX(), 0);
                }
                break;

            case 'L':
                snakeDir = 270;
                head.translate(-1, 0);
                if (borderControl() && (!ignoreBorders && !noBorder)) {
                    head.translate(1, 0);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(gx - 1, head.getY());
                }
                break;

            case 'R':
                snakeDir = 90;
                head.translate(1, 0);
                if (borderControl() && (!ignoreBorders && !noBorder)) {
                    head.translate(-1, 0);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(0, head.getY());
                }
                break;
        }

        if (body.size() > 4 && hitSelf() && !body.get(0).equals(head)) {
            dead = true;
        }
    }

    public int getBodyDir(int bodyCount) {
        if (bodyCount == 0) {
            return 0;
        } else if (body.get(bodyCount).getY() == body.get(bodyCount - 1).getY()) {
            return 270;
        } else {
            return 0;
        }
    }

    public boolean ateApple(Proie proie) {
        if (!head.equals(proie.getPos())) {
            body.remove(0);
        }
        return head.equals(proie.getPos());
    }

    private boolean borderControl() {
        return head.getX() < 0 || head.getY() < 0 || head.getX() >= gx || head.getY() >= gy;
    }

    public boolean hitSelf() {
        return body.contains(head);
    }

    public ArrayList<Point> getBody() {
        return new ArrayList<>(body);
    }

    public Point getHead() {
        return new Point(head);
    }

    public boolean getDead() {
        return dead;
    }

    public ImageView getImageViewHead() {
        return imageHead;
    }

    public ImageView getImageViewBody() {
        return imageBody;
    }

    public ImageView getImageViewTurn() {
        return imageTurn;
    }

    public int getSnakeDir() {
        return snakeDir;
    }

    public ImageView getImageViewTail() {
        return imageTail;
    }
}
