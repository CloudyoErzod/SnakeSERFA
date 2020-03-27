package View;
import javafx.scene.SubScene;
import javafx.scene.layout.*;

public class ViewMenu extends SubScene {

    private final int WIDTH = 630;

    public ViewMenu(String IMAGE_BACKGROUND) {
        super(new AnchorPane(), 150, 200);
        int HEIGHT = 517;
        prefWidth(HEIGHT); prefHeight(WIDTH);
        setLayoutX(810); setLayoutY(190);
    }

}