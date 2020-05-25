import javafx.event.Event; import javafx.fxml.FXML; import javafx.fxml.FXMLLoader; import javafx.scene.control.Label; import javafx.scene.image.ImageView; import javafx.scene.layout.AnchorPane; import java.io.IOException;
public class BigItemView extends AnchorPane {
    private Controller controller;
    public BigItemView(Controller c){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bigItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load();
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        this.controller = c; }
}
