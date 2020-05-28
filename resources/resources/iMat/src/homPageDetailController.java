import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class homPageDetailController extends AnchorPane {

    @FXML Button startShoppingButton;

    private Controller controller;

    public homPageDetailController(Controller c){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homPageDetail.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = c;
    }
    @FXML
    protected void startShopping(ActionEvent event){

        controller.differentDetailPane.getChildren().clear();
        //TODO skapas nya FruitsAndGreensControllers hela tiden. Borde ha ett visst antal och sedan byta mellan
        FruitsAndGreensController c = new FruitsAndGreensController(controller, "Frukt och grönt");
        controller.differentDetailPane.getChildren().add(c);
    }

}
