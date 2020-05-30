import javafx.fxml.Initializable;import javafx.event.ActionEvent;import javafx.fxml.FXML;import javafx.geometry.Insets;import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;import javafx.scene.control.TextField;import javafx.scene.image.ImageView;import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;import javafx.scene.layout.AnchorPane;import javafx.scene.layout.FlowPane;
import javafx.stage.WindowEvent;
import org.w3c.dom.Text;
import se.chalmers.cse.dat216.project.*;import java.io.IOException;import java.math.BigDecimal;import java.math.RoundingMode;
import java.net.URL;import java.util.*;import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;import javafx.event.ActionEvent;
import javafx.fxml.FXML;import javafx.fxml.FXMLLoader;import javafx.fxml.Initializable;import javafx.scene.Parent;
import javafx.scene.control.*;import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;import javafx.stage.Modality;
import javafx.stage.Stage;import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;import se.chalmers.cse.dat216.project.ShoppingItem;import java.util.ResourceBundle;


public class CheckoutPaneController implements Initializable{
    //@FXML private Button continueAsGuestButton;
    //@FXML private FlowPane checkoutItemFlowPane;
    @FXML AnchorPane checkoutPane;@FXML ImageView Step1Image; @FXML ImageView Step2Image; @FXML ImageView Step3Image;@FXML ImageView Step4Image;
    @FXML TextField searchBarCheckout; @FXML Button searchBarButtonCheckout; @FXML Label iMatLogo;
    private IMatDataHandler dh = IMatDataHandler.getInstance();
    public Controller controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("något");
        //checkoutPane.getChildren().clear();
        Wizard1Controller tmp = new Wizard1Controller(controller, this);
        //checkoutPane.getChildren().add(tmp);
    }
    public void setController(Controller c){
        this.controller = c;
        System.out.println("setController i CheckoutPaneController");
    }
    @FXML
    public void toHomePageMouseEvent(MouseEvent event) throws IOException {
        if (controller == null){
            System.out.println("controller e null");
        }
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(prepReturnToHomePage());
        window.show();
    }
    /*
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

     */
    @FXML
    public void searchBarUpdateInCheckout(ActionEvent event) throws IOException {

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(prepReturnToHomePage());
        window.show();
        controller.searchBar.setText(this.searchBarCheckout.getText());
        controller.updateSearchBar();
    }


    @FXML
    public void testMethod(MouseEvent event){
        System.out.println("hejsvehs");
    }
    @FXML
    public void toHomePage (ActionEvent event) throws IOException {
        System.out.println("to HomePage");
       /*
       Parent tmp = FXMLLoader.load(getClass().getResource("homePage.fxml"));
       //kan man hitta controllern från denna?
       Scene tmpScene = new Scene(tmp);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(tmpScene);
       window.show();

        */
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(prepReturnToHomePage());
        window.show();
    }
    private Scene prepReturnToHomePage() throws IOException {
        FXMLLoader tmp = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Parent root = tmp.load();
        controller = tmp.getController();
        Scene scene = new Scene(root);
        controller.setDatahandler(dh);
        controller.showHomePageDetail();
        controller.createBigItemViewMap();
        controller.addShoppingCartListener();
        controller.updateCart();
        //TODO kanske behöver skapas new CheckoutPaneController innan "setController(controller)"
        setController(controller);

        return scene;
    }

}

