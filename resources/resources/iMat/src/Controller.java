import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.WindowEvent;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.*;

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
    protected Map<String, BigItemView> bigItemViewMap = new HashMap<>();

    @FXML private AnchorPane loginPane; @FXML AnchorPane LogInPopUp; @FXML private TextField textFieldEmail; @FXML private TextField textFieldPassword;
    @FXML private ImageView loginCloseButton; @FXML private Button loginButton; @FXML private CheckBox rememberMeCheckbox;
    @FXML private Button createAccountButton; @FXML private Label iMatLogo; @FXML private Label userButton;
    @FXML private TextField searchBar; @FXML private AnchorPane myPagesPane; @FXML private Label myPages;
    @FXML private Label myFavorites; @FXML private Label myDetailsLabel; @FXML private Label purchaseHistory;
    @FXML private ImageView homeButton; @FXML private Label fruitsAndGreens; @FXML private Label dairyItems;
    @FXML private Label breadItems; @FXML private Label fishItems; @FXML private Label meatItems;
    @FXML private Label pantryItems; @FXML private Label drinkItems; @FXML public AnchorPane differentDetailPane;
    @FXML private FlowPane cartItemPane; @FXML private Button checkoutButton; @FXML private Label totalLabel;
    @FXML private Label totalPrizeLabel; @FXML private AnchorPane homePagePane;

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

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

    @FXML
    protected void newWindow (MouseEvent event) throws IOException {
           /*
           AnchorPane homePageDetail = FXMLLoader.load(getClass().getResource("wizard1.fxml"));
           Scene homePageScene = new Scene(homePageDetail);

           //homPageDetailController hej = new homPageDetailController();
           /*
           FXMLLoader homePageDetail = new FXMLLoader(getClass().getResource("homPageDetail.fxml"));
           homePageDetail.setRoot(this);
           homePageDetail.setController(this);

           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recipe_listitem.fxml"));

           fxmlLoader.setRoot(this);
           fxmlLoader.setController(this);
           */
        //Stage window = (Stage) homePageScene.getWindow();
       /*
           Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                   //(Stage)((Node)event.getSource()).getScene().getWindow();
           window.setScene(homePageScene);
           window.show();

        */
    }

    @FXML
    public void toCheckout(ActionEvent event) throws IOException {
        //TODO checkoutPane är inte klar ännu
        //Parent tmp = FXMLLoader.load(getClass().getResource("checkoutPane.fxml"));

        Parent tmp = FXMLLoader.load(getClass().getResource("wizard1.fxml"));
        Scene tmpScene = new Scene(tmp);


        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tmpScene);
        window.show();
    }
       /*
       @FXML
       public void toCheckout (Stage stage) throws IOException {
           newWindowMethod(stage);
       }



       protected void newWindowMethod (Stage stage) throws IOException {
           AnchorPane wizard1 = FXMLLoader.load(getClass().getResource("wizard1.fxml"));
           Scene wizard1Scene = new Scene(wizard1);

           stage.setScene(wizard1Scene);
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
        datahandler.getShoppingCart().addShoppingCartListener(new ShoppingCartListener() {
            @Override
            public void shoppingCartChanged(CartEvent cartEvent) {
                totalPrizeLabel.setText(round(datahandler.getShoppingCart().getTotal(), 2) + " kr");
                updateCart();
                for (ShoppingItem si : datahandler.getShoppingCart().getItems()){
                    System.out.println(si.getProduct());
                }
            }
        });
    }

    private void updateCart(){
        cartItemPane.getChildren().clear();
        for (ShoppingItem shoppingItem : datahandler.getShoppingCart().getItems()){
            CartItemView cartItemView = new CartItemView(shoppingItem);
            cartItemPane.getChildren().add(cartItemView);
        }
    }


    @FXML protected void showHomePageDetail(){
        differentDetailPane.getChildren().clear();
        homPageDetailController c = new homPageDetailController(this);
        differentDetailPane.getChildren().add(c);
    }
    @FXML
    protected void showFruitsAndGreens(MouseEvent event){
        differentDetailPane.getChildren().clear();
        Label tmp = (Label) event.getSource();
        String s = tmp.getText();
        //TODO skapas nya FruitsAndGreensControllers hela tiden. Borde ha ett visst antal och sedan byta mellan
        FruitsAndGreensController c = new FruitsAndGreensController(this, s);
        differentDetailPane.getChildren().add(c);
  /*
  Lägg till metod som sätter in alla BigItemViews under vald kategori

   */
        //BigItemView b = new BigItemView(this);
        //differentDetailPane.getChildren().add(b);
    }
    @FXML
    protected void searchBarUpdateOnEnter(ActionEvent event){
        differentDetailPane.getChildren().clear();
        FruitsAndGreensController fgc = new FruitsAndGreensController(this, searchBar.getText());
        fgc.itemViewPane.getChildren().clear();
        for (Product p : datahandler.findProducts(searchBar.getText())){
            BigItemView tmpItem = bigItemViewMap.get(p.getName());
            fgc.itemViewPane.getChildren().add(tmpItem);
        }
        fgc.itemViewPane.setPadding(new Insets(10,10,10,27));
        fgc.itemViewPane.setVgap(20);
        fgc.itemViewPane.setHgap(20);
        System.out.println("används");
        differentDetailPane.getChildren().add(fgc);
    }


    @FXML
    protected void showMyPagesPane(MouseEvent event){
        myPagesPane.toFront();
    }
    @FXML
    protected void closeLogInPopUp(MouseEvent event){
        loginPane.toBack();
        LogInPopUp.toBack();
        textFieldEmail.clear();
        textFieldPassword.clear();
    }
    @FXML
    private void createNewAccountFromLogInPopUp(ActionEvent event){
        //TODO behövs FXML
        //TODO ska man logga in med användarnamn eller email?
        User user = new User();
        String tmpEmail = textFieldEmail.getText();
        //user.setUserName();
        //user.setPassword();
        loginPane.toBack();
        LogInPopUp.toBack();
        textFieldEmail.clear();
        textFieldPassword.clear();
    }
    @FXML
    public void loginPressed(MouseEvent event){
        loginPane.toFront();
        LogInPopUp.toFront();
    }


    protected void createBigItemViewMap(){
        for (Product p : datahandler.getProducts()){
            BigItemView item = new BigItemView(datahandler, p);
            bigItemViewMap.put(p.getName(), item);
        }
    }




}

