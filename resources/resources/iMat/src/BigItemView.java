import javafx.event.Event; import javafx.fxml.FXML; import javafx.fxml.FXMLLoader; import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView; import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;


public class BigItemView extends AnchorPane {
    @FXML ImageView itemImage;
    @FXML Label itemNameLabel;
    @FXML Label costPerKiloLabel;
    @FXML TextField quantityItemsTextField;
    @FXML ImageView minusItemImage;
    @FXML ImageView plusItemImage;
    public int quantity = 0;

    //private Controller controller;
    public BigItemView(IMatDataHandler datahandler, Product product){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bigItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load();
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        //this.controller = c;
        this.itemImage.setImage(datahandler.getFXImage(product));
        this.itemNameLabel.setText(product.getName());
        this.costPerKiloLabel.setText(product.getPrice() + " " + product.getUnitSuffix());
        this.quantityItemsTextField.setText(String.valueOf(quantity));


    }
}
