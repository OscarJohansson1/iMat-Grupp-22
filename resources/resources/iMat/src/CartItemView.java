import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
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

    //private final Product product;
    private final ShoppingItem shoppingItem;
    private final IMatDataHandler datahandler;
    private final Controller controller;

    boolean open = false;

    //private Controller controller;
    public CartItemView(ShoppingItem shoppingItem, Controller c) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cartItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.shoppingItem = shoppingItem;
        this.datahandler = IMatDataHandler.getInstance();
        this.controller = c;

        this.cartItemImage.setImage(datahandler.getFXImage(shoppingItem.getProduct()));
        this.cartItemLabel.setText(shoppingItem.getProduct().getName());
        this.cartItemPerKilo.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());

        plus.setVisible(false);
        minus.setVisible(false);
        delete.setVisible(false);

        delete.setPickOnBounds(true);
        Tooltip.install(delete, new Tooltip("Ta bort vara"));
        cartEditPen.setPickOnBounds(true);
        Tooltip.install(cartEditPen, new Tooltip("Ändra vara"));

        addListeners();
    }

    public void setUp() {
        plus.setVisible(true);
        minus.setVisible(true);
        delete.setVisible(true);
        cartItemsPane.setPrefHeight(100);
    }

    private void setDown() {
        plus.setVisible(false);
        minus.setVisible(false);
        delete.setVisible(false);
        cartItemsPane.setPrefHeight(50);
    }

    private void addListeners() {
        cartEditPen.setOnMouseClicked(m->{
            if(!open){
                cartEditPen.setImage(new Image(getClass().getResource("/sceneImages/greenpen.png").toString()));
                setUp();
                open = true;
            } else {
                cartEditPen.setImage(new Image(getClass().getResource("/sceneImages/baseline_create_black_48dp.png").toString()));
                setDown();
                open = false;
            }
        });

        plus.setOnMouseClicked(m->{
            shoppingItem.setAmount(shoppingItem.getAmount() + 1);
            this.cartItemPerKilo.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
            controller.totalPrizeLabel.setText(Controller.round(datahandler.getShoppingCart().getTotal(), 2) + " kr");
        });

        plus.setOnMouseMoved(m->{
            plus.setImage(new Image(getClass().getResource("/sceneImages/greenplus.png").toString()));
        });

        plus.setOnMouseExited(m->{
            plus.setImage(new Image(getClass().getResource("/sceneImages/baseline_add_circle_outline_black_18dp.png").toString()));
        });

        minus.setOnMouseClicked(m->{
            shoppingItem.setAmount(shoppingItem.getAmount() - 1);
            if(shoppingItem.getAmount() == 0){
                datahandler.getShoppingCart().removeItem(shoppingItem);
            }
            this.cartItemPerKilo.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
            controller.totalPrizeLabel.setText(Controller.round(datahandler.getShoppingCart().getTotal(), 2) + " kr");
        });

        minus.setOnMouseMoved(m->{
            minus.setImage(new Image(getClass().getResource("/sceneImages/redminus.png").toString()));
        });

        minus.setOnMouseExited(m->{
            minus.setImage(new Image(getClass().getResource("/sceneImages/baseline_remove_circle_outline_black_18dp.png").toString()));
        });

        delete.setOnMouseClicked(m->{
            datahandler.getShoppingCart().removeItem(shoppingItem);
            controller.totalPrizeLabel.setText(Controller.round(datahandler.getShoppingCart().getTotal(), 2) + " kr");

        });

        delete.setOnMouseMoved(m->{
            delete.setImage(new Image(getClass().getResource("/sceneImages/reddelete.png").toString()));
        });

        delete.setOnMouseExited(m->{
            delete.setImage(new Image(getClass().getResource("/sceneImages/baseline_delete_black_24dp.png").toString()));
        });

        cartEditPen.setOnMouseEntered(m->{
            if(!open) {
                cartEditPen.setImage(new Image(getClass().getResource("/sceneImages/greenpen.png").toString()));
            } else {
                cartEditPen.setImage(new Image(getClass().getResource("/sceneImages/baseline_create_black_48dp.png").toString()));
            }
        });

        cartEditPen.setOnMouseExited(m->{
            if(!open) {
                cartEditPen.setImage(new Image(getClass().getResource("/sceneImages/baseline_create_black_48dp.png").toString()));
            } else {
                cartEditPen.setImage(new Image(getClass().getResource("/sceneImages/greenpen.png").toString()));
            }
        });
    }
}

