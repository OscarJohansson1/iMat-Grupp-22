import javafx.fxml.Initializable;import javafx.event.ActionEvent;import javafx.fxml.FXML;import javafx.geometry.Insets;import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;import javafx.scene.control.TextField;import javafx.scene.image.ImageView;import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;import javafx.scene.layout.AnchorPane;import javafx.scene.layout.FlowPane;
import javafx.stage.WindowEvent;import se.chalmers.cse.dat216.project.*;import java.io.IOException;import java.math.BigDecimal;import java.math.RoundingMode;
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
    @FXML AnchorPane checkoutPane;
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
    public void testMethod(MouseEvent event){
        System.out.println("hejsvehs");
    }
    @FXML
    public void toHomePage (ActionEvent event) throws IOException {
        System.out.println("to HomePage");
        Parent tmp = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        //kan man hitta controllern från denna?
        Scene tmpScene = new Scene(tmp);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tmpScene);
        window.show();
    }

}
