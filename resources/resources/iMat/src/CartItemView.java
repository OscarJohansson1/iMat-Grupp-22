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
    @FXML
    AnchorPane cartItemsPane;
    @FXML ImageView plus;
    @FXML ImageView minus;
    @FXML ImageView delete;
    @FXML Label amount;


    BigItemView biv;

    //private final Product product;
    private final ShoppingItem shoppingItem;
    private final IMatDataHandler datahandler;

    boolean open = false;

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

        biv = new BigItemView(datahandler, shoppingItem.getProduct());
        amount.setText(Integer.toString((int) shoppingItem.getAmount()));
        plus.setVisible(false);
        minus.setVisible(false);
        delete.setVisible(false);
        amount.setVisible(false);

        addListeners();
    }

    public void setUp() {
        plus.setVisible(true);
        minus.setVisible(true);
        delete.setVisible(true);
        amount.setVisible(true);
        cartItemsPane.setPrefHeight(100);
    }

    private void setDown() {
        plus.setVisible(false);
        minus.setVisible(false);
        delete.setVisible(false);
        amount.setVisible(false);
        cartItemsPane.setPrefHeight(50);
    }

    private void addListeners() {
        cartEditPen.setOnMouseClicked(m->{
            if(!open){
                setUp();
                open = true;
            } else {
                setDown();
                open = false;
            }
        });

        plus.setOnMouseClicked(m->{
            shoppingItem.setAmount(shoppingItem.getAmount() + 1);
            amount.setText(Integer.toString((int) shoppingItem.getAmount()));
            this.cartItemPerKilo.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
        });

        minus.setOnMouseClicked(m->{
            shoppingItem.setAmount(shoppingItem.getAmount() - 1);
            if(shoppingItem.getAmount() == 0){
                datahandler.getShoppingCart().removeItem(shoppingItem);
            }
            amount.setText(Integer.toString((int) shoppingItem.getAmount()));
            this.cartItemPerKilo.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
        });

        delete.setOnMouseClicked(m->{
            datahandler.getShoppingCart().removeItem(shoppingItem);
        });
    }
}

