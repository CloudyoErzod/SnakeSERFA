package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import View.ViewHandler;

public class Main extends Application {

    private static ViewHandler viewHandler;

    @Override
    public void start(Stage primaryStage) {
        viewHandler = new ViewHandler();
        viewHandler.initalizeMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static ViewHandler getViewHandler() {
        return viewHandler;
    }
}

