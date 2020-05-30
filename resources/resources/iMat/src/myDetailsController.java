import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class myDetailsController extends AnchorPane {
    private Controller controller;

    public myDetailsController(Controller c){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myDetails.fxml"));
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