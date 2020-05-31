import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingItem;import java.util.ResourceBundle;

public class Wizard1Controller extends AnchorPane implements Initializable{
    @FXML private Button continueAsGuestButton;
    @FXML private Label totalCost;
    @FXML public FlowPane checkoutItemFlowPane;
    private IMatDataHandler dh = IMatDataHandler.getInstance();
    private Controller controller;
    public CheckoutPaneController checkoutPaneController;

    public Wizard1Controller(Controller c, CheckoutPaneController cpc) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wizard1.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
            this.controller = c;
            this.checkoutPaneController = cpc;
            this.checkoutPaneController.checkoutPane.getChildren().clear();
            this.checkoutPaneController.checkoutPane.getChildren().add(this);
            updateTotal();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateItems();
    }

    public void updateItems() {
        for (ShoppingItem si : dh.getShoppingCart().getItems()){
            CheckoutItems tmp = new CheckoutItems(si, this);
            checkoutItemFlowPane.getChildren().add(tmp);
        }
    }

    public void updateTotal() {
        totalCost.setText(dh.getShoppingCart().getTotal() + "kr");
    }


    @FXML
    public void toWizard2(ActionEvent event) throws IOException {
        if(!dh.getShoppingCart().getItems().isEmpty()) {
            Wizard2Controller wizard2Controller = new Wizard2Controller(checkoutPaneController);
        } else {
            checkoutItemFlowPane.setStyle("-fx-border-color: RED");
        }
    }
}
