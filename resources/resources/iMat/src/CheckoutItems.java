import javafx.event.ActionEvent;
import javafx.event.Event; import javafx.fxml.FXML; import javafx.fxml.FXMLLoader; import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;


public class CheckoutItems extends AnchorPane {
    @FXML ImageView checkoutItemImage;
    @FXML Label checkoutItemLabel;
    @FXML Label checkoutItemAmount;
    @FXML Label checkoutItemTotalCost;
    @FXML ImageView checkoutItemPen;
    @FXML ImageView checkoutItemTrashcan;
    @FXML AnchorPane checkoutItemEditPane;
    @FXML ImageView checkoutItemMinusImage;
    @FXML ImageView checkoutItemPlusImage;
    @FXML TextField checkoutItemTextField;

    //private final Product product;
    private final ShoppingItem shoppingItem;
    private final IMatDataHandler datahandler;

    //private Controller controller;
    public CheckoutItems(ShoppingItem shoppingItem) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkoutItems.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        //this.controller = c;

        this.shoppingItem = shoppingItem;
        this.datahandler = IMatDataHandler.getInstance();

        this.checkoutItemImage.setImage(datahandler.getFXImage(shoppingItem.getProduct()));
        this.checkoutItemLabel.setText(shoppingItem.getProduct().getName());
        this.checkoutItemAmount.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
        this.checkoutItemTotalCost.setText(Controller.round(shoppingItem.getTotal(), 2) + " kr");

    }
}
