import javafx.event.Event;
import javafx.fxml.Initializable;import javafx.event.ActionEvent;import javafx.fxml.FXML;import javafx.geometry.Insets;import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;import javafx.scene.layout.AnchorPane;import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
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
    @FXML TextField searchBarCheckout; @FXML Button searchBarButtonCheckout; @FXML Label iMatLogo; @FXML private ImageView line1;
    @FXML private ImageView line2; @FXML private ImageView line3; @FXML private Text step1Label; @FXML private Text step2Label;
    @FXML private Text step3Label; @FXML private Text step4Label; @FXML private Label kassaLabel; @FXML private Button toTheHomepageButton;
    private IMatDataHandler dh = IMatDataHandler.getInstance();
    public Controller controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Wizard1Controller tmp = new Wizard1Controller(controller, this);
    }
    public void setController(Controller c){
        this.controller = c;
    }

    @FXML
    public void toHomePageMouseEvent(MouseEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(prepReturnToHomePage());
        window.show();
    }
    @FXML
    public void toHelpPaneMouseEvent(MouseEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(prepReturnToHomePage());
        window.show();
        controller.loginPane.toFront();
    }

    @FXML
    public void toWizard1(Event event) throws IOException {
        Wizard2Controller w2c = new Wizard2Controller(this);
        w2c.toWizard1(event);
    }
    @FXML
    public void toWizard2(Event event) {
        Wizard2Controller w2c = new Wizard2Controller(this);
        w2c.toWizard2(event);
    }
    @FXML
    public void toWizard3(Event event) {
        Wizard2Controller w2c = new Wizard2Controller(this);
        w2c.toWizard3(event);
    }
    @FXML
    public void toWizard4(Event event) {
        Wizard2Controller w2c = new Wizard2Controller(this);
        w2c.toWizard4(event);
    }


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
    public void setVisibleFalse(){
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        step1Label.setVisible(false);
        step2Label.setVisible(false);
        step3Label.setVisible(false);
        step4Label.setVisible(false);
        Step1Image.setVisible(false);
        Step2Image.setVisible(false);
        Step3Image.setVisible(false);
        Step4Image.setVisible(false);
        kassaLabel.setVisible(false);
        toTheHomepageButton.setVisible(false);
    }

}

