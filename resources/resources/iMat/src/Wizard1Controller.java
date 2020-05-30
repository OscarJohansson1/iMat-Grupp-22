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

public class Wizard1Controller extends AnchorPane{
    @FXML private Button continueAsGuestButton;
    @FXML private FlowPane checkoutItemFlowPane;
    private IMatDataHandler dh = IMatDataHandler.getInstance();
    private Controller controller;

    public Wizard1Controller(){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wizard1.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
    }
/*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Wizard1Controller skapades");
        for (ShoppingItem si : dh.getShoppingCart().getItems()){
            CheckoutItems tmp = new CheckoutItems(si);
            checkoutItemFlowPane.getChildren().add(tmp);
        }
    }

 */

    @FXML
    public void toWizard2(ActionEvent event) throws IOException {
        Parent tmp = FXMLLoader.load(getClass().getResource("wizard.2.fxml"));
        Scene tmpScene = new Scene(tmp);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tmpScene);
        window.show();
        System.out.println("till Wizard2");
    }

    @FXML
    public void testMethod(MouseEvent event){
        System.out.println("hejsvehs");
    }


}
