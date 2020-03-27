package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Main.Main;
import java.io.IOException;

public class ViewHandler {

    private Stage primaryStage = new Stage();
    private AnchorPane root;
    private Stage gameStage;
    private ViewMenu mainSubScene;

    public void initalizeMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../Asset/sample.fxml"));
            root = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        initStartSubScene();
        root.getChildren().addAll(mainSubScene);
        Scene mainScene = new Scene(root);

        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void initStartSubScene() {
        mainSubScene = new ViewMenu("Asset/SubScene.png");
        mainSubScene.setLayoutX(500);

    }

    public void initGameStage() {
        gameStage = new Stage();
        gameStage.setTitle("Snake");
        gameStage.setResizable(false);
    }

    public Stage getGameStage() {
        return gameStage;
    }
}
