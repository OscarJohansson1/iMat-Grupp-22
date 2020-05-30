import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class myPagesController extends AnchorPane {
    private Controller controller;

    public myPagesController(Controller c){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myPages.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = c;

    }
}
