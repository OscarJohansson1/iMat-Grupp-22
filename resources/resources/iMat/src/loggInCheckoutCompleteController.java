import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class loggInCheckoutCompleteController extends AnchorPane {
    private CheckoutPaneController controller;
    private Controller c;
    private IMatDataHandler dh = IMatDataHandler.getInstance();

    public loggInCheckoutCompleteController(CheckoutPaneController c){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loggInCheckoutComplete.fxml"));
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
    public void returnToHomepage(ActionEvent event) throws IOException {
        FXMLLoader tmp = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Parent root = tmp.load();
        c = tmp.getController();
        Scene scene = new Scene(root, 1200, 700);
        c.setDatahandler(dh);
        c.showHomePageDetail();
        c.createBigItemViewMap();
        c.addShoppingCartListener();
        dh.getShoppingCart().clear();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}