import javafx.event.ActionEvent;
import javafx.event.Event; import javafx.fxml.FXML; import javafx.fxml.FXMLLoader; import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;


public class BigItemView extends AnchorPane {
    @FXML ImageView itemImage;
    @FXML Label itemNameLabel;
    @FXML Label costPerKiloLabel;
    @FXML TextField quantityItemsTextField;
    @FXML ImageView minusItemImage;
    @FXML ImageView plusItemImage;
    @FXML ImageView favoriteStarImage;
    public int quantity = 0;
    private final Product product;
    private final IMatDataHandler datahandler;

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
        this.costPerKiloLabel.setText(product.getPrice() + " " + product.getUnit());
        this.quantityItemsTextField.setText(String.valueOf(quantity));
        this.product=product;
        this.datahandler=datahandler;

        plusItemImage.setOnMouseMoved(m->{
            plusItemImage.setImage(new Image(getClass().getResource("/sceneImages/greenplus.png").toString()));
        });
        plusItemImage.setOnMouseExited(m->{
            plusItemImage.setImage(new Image(getClass().getResource("/sceneImages/baseline_add_circle_outline_black_18dp.png").toString()));
        });
        minusItemImage.setOnMouseMoved(m->{
            minusItemImage.setImage(new Image(getClass().getResource("/sceneImages/redminus.png").toString()));
        });
        minusItemImage.setOnMouseExited(m->{
            minusItemImage.setImage(new Image(getClass().getResource("/sceneImages/baseline_remove_circle_outline_black_18dp.png").toString()));
        });
        favoriteStarImage.setOnMouseMoved(m->{
            favoriteStarImage.setImage(new Image(getClass().getResource("/sceneImages/yellow_star.png").toString()));
        });
        favoriteStarImage.setOnMouseClicked(m->{
            if(datahandler.isFavorite(product)){
                datahandler.removeFavorite(product);
            } else {
                datahandler.addFavorite(product);
            }
        });
        favoriteStarImage.setOnMouseExited(m->{
            if(!datahandler.isFavorite(product)) {
                favoriteStarImage.setImage(new Image(getClass().getResource("/sceneImages/outline_star_border_black_48dp.png").toString()));
            }
        });
    }

    /**
     * Får inte detta att fungera. Om man kopplar den nu så kommer:
     * "Controller value already specified"
     * Om man tar bort fx:controller="BigItemView" så fungerar inte metoden
     * Om man tar bort setController så går inte BigItemView-objekt att skapas
     * OBS! fungerar nu
     */
    @FXML
    protected void plusButtonActionPerformed (MouseEvent event){
        for (ShoppingItem si : datahandler.getShoppingCart().getItems()){
            if (si.getProduct() == product){
                si.setAmount(si.getAmount() + 1);
                updateQuantity(true);
                datahandler.getShoppingCart().fireShoppingCartChanged(si, true);
                return;
            }
        }
        datahandler.getShoppingCart().addProduct(product);
        updateQuantity(true);
    }

    @FXML
    protected void minusButtonActionPerformed (MouseEvent event){
        if (quantity>0){
            for (ShoppingItem si : datahandler.getShoppingCart().getItems()){
                if (si.getProduct() == product){
                    si.setAmount(si.getAmount() - 1);
                    updateQuantity(false);
                    if (quantity==0){
                        datahandler.getShoppingCart().removeItem(si);

                        return;
                    }
                    datahandler.getShoppingCart().fireShoppingCartChanged(si, true);
                }
            }
        }
    }

    protected void updateQuantity(boolean b){
        if (b){
            quantity++;
            quantityItemsTextField.setText(String.valueOf(quantity));
        }
        else {
            quantity--;
            quantityItemsTextField.setText(String.valueOf(quantity));
        }
    }


}
