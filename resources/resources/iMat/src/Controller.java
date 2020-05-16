import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

    public class Controller {
        private IMatDataHandler datahandler;
        //Product p finns endast för att slippa errors (ska egentligen vara det man trycker på)
        private Product p;
        @FXML private AnchorPane loginPane; @FXML private TextField textFieldEmail; @FXML private TextField textFieldPassword;
        @FXML private ImageView loginCloseButton; @FXML private Button loginButton; @FXML private CheckBox rememberMeCheckbox;
        @FXML private Button createAccountButton; @FXML private Label iMatLogo; @FXML private Label userButton;
        @FXML private TextField searchBar; @FXML private AnchorPane myPagesPane; @FXML private Label myPages;
        @FXML private Label myFavorites; @FXML private Label myDetailsLabel; @FXML private Label purchaseHistory;
        @FXML private ImageView homeButton; @FXML private Label fruitsAndGreens; @FXML private Label dairyItems;
        @FXML private Label breadItems; @FXML private Label fishItems; @FXML private Label meatItems;
        @FXML private Label pantryItems; @FXML private Label drinkItems; @FXML private AnchorPane differentDetailPane;
        @FXML private AnchorPane cartItemPane; @FXML private Button checkoutButton; @FXML private Label totalLabel;
        @FXML private Label totalPrizeLabel;

        @FXML
        protected void plusButtonActionPerformed (ActionEvent event){
            for (ShoppingItem si : datahandler.getShoppingCart().getItems()){
                if (si.getProduct() == p){
                    si.setAmount(si.getAmount() + 1);
                    //Öka countern mellan Plus och Minus
                    return;
                }
            }
            datahandler.getShoppingCart().addProduct(p);
            //Öka countern mellan Plus och Minus
        }
        @FXML
        protected void favoriteButtonActionPerformed (ActionEvent event){

            if (datahandler.isFavorite(p)) {
                datahandler.removeFavorite(p);
            }
            else datahandler.addFavorite(p);
        }
/*
        @FXML
        protected void minusButtonActionPerformed (ActionEvent event) {
        /* counter är det som finns mellan Plus och Minus
            if (counter > 0) {
                for (ShoppingItem si : datahandler.getShoppingCart().getItems()){
                    if (si.getProduct().equals(p)){
                        si.setAmount(si.getAmount() - 1);
                        datahandler.getShoppingCart().removeItem(si);
                        counter--
                    }
                }
            }
        }
         */

        @FXML
        protected void testMethodActionEvent(ActionEvent event){
            System.out.println("hej");
        }
        @FXML
        protected void testMethodMouseEvent(MouseEvent event){
            System.out.println("hi");
        }
        @FXML
        protected void testMethodMouseEvent2(MouseEvent event){
            searchBar.clear();
        }
        @FXML
        protected void testMethodKeyEvent(KeyEvent event){
            System.out.println("hello");
        }
        @FXML
        protected void testMethodWindowEvent(WindowEvent event){
            System.out.println("hola");
        }

        public void setDatahandler(IMatDataHandler dh){
            this.datahandler = dh;
        }

        public void addShoppingCartListener(){
            datahandler.getShoppingCart().addShoppingCartListener(cartEvent -> totalPrizeLabel.setText(String.valueOf(datahandler.getShoppingCart().getTotal())));
        }

    }




