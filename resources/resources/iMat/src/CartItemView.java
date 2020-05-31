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


public class CartItemView extends AnchorPane {

    @FXML
    ImageView cartItemImage;
    @FXML
    Label cartItemLabel;
    @FXML
    Label cartItemPerKilo;
    @FXML
    Label cartTotalItemPrize;
    @FXML
    ImageView cartEditPen;

    //private final Product product;
    private final ShoppingItem shoppingItem;
    private final IMatDataHandler datahandler;

    //private Controller controller;
    public CartItemView(ShoppingItem shoppingItem) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cartItemView.fxml"));
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

        this.cartItemImage.setImage(datahandler.getFXImage(shoppingItem.getProduct()));
        this.cartItemLabel.setText(shoppingItem.getProduct().getName());
        this.cartItemPerKilo.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
    }

}
