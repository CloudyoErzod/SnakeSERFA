package Controller;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Main.Main;
import Model.Proie;
import Model.Game;
import Model.LeSnake;
import View.ViewJeu;
import View.ViewHandler;

public class ControllerMenu {

    private static int X_VALUE = 20;
    private static int Y_VALUE = 20;
    private Scene gameScene;
    private boolean powerUp;
    private char difficulty = 'N';
    private ViewHandler viewHandler = Main.getViewHandler();

    public void handleStart() {
        viewHandler.initGameStage();
        Stage gameStage = viewHandler.getGameStage();
        launchSnake(gameStage);
        gameStage.setScene(gameScene);
        viewHandler.getPrimaryStage().close();
        gameStage.show();
    }

    public void handleQuit() {
        System.exit(0);
    }

    private void launchSnake(Stage gameStage) {
        Game game = new Game(X_VALUE, Y_VALUE, powerUp, difficulty);
        LeSnake snake = new LeSnake(game.getWidth(), game.getHeight(), game.isNoBorder());
        Proie proie = new Proie(game.getWidth(), game.getHeight(), snake);

        ViewJeu viewJeu = new ViewJeu(game);
        ControllerJeu controller = new ControllerJeu(snake, proie, game, viewJeu, gameStage);

        StackPane gamePane = new StackPane();
        gameScene = new Scene(gamePane, game.getWidth() * game.getMultiplier(), game.getHeight() * game.getMultiplier());

        gameScene.setOnKeyPressed(controller::handle);

        viewJeu.makeScene(gameScene, snake, proie);
        gamePane.getChildren().addAll(viewJeu.gridCanvas, viewJeu.gameOverLabel);
    }

    public static int getxValue() {
        return X_VALUE;
    }

    public static int getyValue() {
        return Y_VALUE;
    }
}

