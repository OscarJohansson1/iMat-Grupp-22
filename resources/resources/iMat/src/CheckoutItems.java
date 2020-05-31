import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
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

    @FXML AnchorPane paneForPen;

    //private final Product product;
    private final ShoppingItem shoppingItem;
    private final IMatDataHandler datahandler;
    private final Wizard1Controller wizard1;
    private int count = 0;
    private boolean open = false;

    //private Controller controller;
    public CheckoutItems(ShoppingItem shoppingItem, Wizard1Controller w) {

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
        this.wizard1 = w;

        this.checkoutItemImage.setImage(datahandler.getFXImage(shoppingItem.getProduct()));
        this.checkoutItemLabel.setText(shoppingItem.getProduct().getName());
        this.checkoutItemAmount.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
        this.checkoutItemTotalCost.setText(Controller.round(shoppingItem.getTotal(), 2) + " kr");

        addListeners();
    }

    @FXML
    public void editPaneToFront(MouseEvent event){
        if ((count & 1) == 0){
            paneForPen.toFront();
            checkoutItemEditPane.toFront();
            open = true;
        }
        else {
            paneForPen.toBack();
            checkoutItemEditPane.toBack();
            open = false;
        }
        count++;

    }

    private void addListeners() {

        checkoutItemPlusImage.setOnMouseClicked(m -> {
            shoppingItem.setAmount(shoppingItem.getAmount() + 1);
            this.checkoutItemAmount.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
            this.checkoutItemTotalCost.setText(Controller.round(shoppingItem.getTotal(), 2) + " kr");
            wizard1.updateTotal();
        });

        checkoutItemPlusImage.setOnMouseMoved(m -> {
            checkoutItemPlusImage.setImage(new Image(getClass().getResource("/sceneImages/greenplus.png").toString()));
        });

        checkoutItemPlusImage.setOnMouseExited(m -> {
            checkoutItemPlusImage.setImage(new Image(getClass().getResource("/sceneImages/baseline_add_circle_outline_black_18dp.png").toString()));
        });

        checkoutItemMinusImage.setOnMouseClicked(m -> {
            shoppingItem.setAmount(shoppingItem.getAmount() - 1);
            if (shoppingItem.getAmount() == 0) {
                datahandler.getShoppingCart().removeItem(shoppingItem);
                wizard1.checkoutItemFlowPane.getChildren().remove(this);
            }
            this.checkoutItemAmount.setText(Math.round(shoppingItem.getAmount()) + " " + shoppingItem.getProduct().getUnitSuffix());
            this.checkoutItemTotalCost.setText(Controller.round(shoppingItem.getTotal(), 2) + " kr");
            wizard1.updateTotal();
        });

        checkoutItemMinusImage.setOnMouseMoved(m -> {
            checkoutItemMinusImage.setImage(new Image(getClass().getResource("/sceneImages/redminus.png").toString()));
        });

        checkoutItemMinusImage.setOnMouseExited(m -> {
            checkoutItemMinusImage.setImage(new Image(getClass().getResource("/sceneImages/baseline_remove_circle_outline_black_18dp.png").toString()));
        });

        checkoutItemTrashcan.setOnMouseClicked(m -> {
            datahandler.getShoppingCart().removeItem(shoppingItem);
            wizard1.checkoutItemFlowPane.getChildren().remove(this);
            wizard1.updateTotal();
        });

        checkoutItemTrashcan.setOnMouseMoved(m -> {
            checkoutItemTrashcan.setImage(new Image(getClass().getResource("/sceneImages/reddelete.png").toString()));
        });

        checkoutItemTrashcan.setOnMouseExited(m -> {
            checkoutItemTrashcan.setImage(new Image(getClass().getResource("/sceneImages/baseline_delete_black_24dp.png").toString()));
        });

        checkoutItemPen.setOnMouseEntered(m -> {
            if (!open) {
                checkoutItemPen.setImage(new Image(getClass().getResource("/sceneImages/greenpen.png").toString()));
            } else {
                checkoutItemPen.setImage(new Image(getClass().getResource("/sceneImages/baseline_create_black_48dp.png").toString()));
            }
        });

        checkoutItemPen.setOnMouseExited(m -> {
            if (!open) {
                checkoutItemPen.setImage(new Image(getClass().getResource("/sceneImages/baseline_create_black_48dp.png").toString()));
            } else {
                checkoutItemPen.setImage(new Image(getClass().getResource("/sceneImages/greenpen.png").toString()));
            }
        });
    }


}
