import javafx.event.Event;
import javafx.fxml.Initializable;import javafx.event.ActionEvent;import javafx.fxml.FXML;import javafx.geometry.Insets;import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;import javafx.scene.layout.AnchorPane;import javafx.scene.layout.FlowPane;
import javafx.stage.WindowEvent;import se.chalmers.cse.dat216.project.*;import java.io.IOException;import java.math.BigDecimal;import java.math.RoundingMode;
import java.net.URL;import java.util.*;import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;import javafx.event.ActionEvent;
import javafx.fxml.FXML;import javafx.fxml.FXMLLoader;import javafx.fxml.Initializable;import javafx.scene.Parent;
import javafx.scene.control.*;import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;import javafx.stage.Modality;
import javafx.stage.Stage;import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;import se.chalmers.cse.dat216.project.ShoppingItem;import java.util.ResourceBundle;

public class Wizard2Controller extends AnchorPane implements Initializable {
@FXML AnchorPane wizardPane4;@FXML DatePicker pickADate4;
@FXML TextField addressDelivery4;@FXML TextField cardForDelivery4;
@FXML Button backButton4;@FXML Button nextButton4;

@FXML AnchorPane wizardPane3;
@FXML RadioButton mastercard3;@FXML RadioButton visaRadioButton3;
@FXML TextField cardnumber3;@FXML TextField cardLasting3;@FXML TextField cvcCode3;
@FXML Button backButton3;@FXML Button nextButton3;

@FXML AnchorPane wizardPane2;@FXML TextField surname2;
@FXML TextField lastname2;@FXML TextField emailField2;@FXML TextField addressField2;
@FXML TextField postcode2;@FXML TextField postCity2;@FXML Button backButton2;@FXML Button nextButton2;

    private IMatDataHandler dh = IMatDataHandler.getInstance();
    private Controller controller;
    private CheckoutPaneController checkoutPaneController;
    public Wizard2Controller(CheckoutPaneController cpc){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wizard.2.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.checkoutPaneController=cpc;
        System.out.println("wizard2Controller konstruktor");
        checkoutPaneController.checkoutPane.getChildren().clear();
        checkoutPaneController.checkoutPane.getChildren().add(this);
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Wizard2Controller initialize");
        wizardPane2.toFront();


    }
    @FXML
    public void toWizard1(Event event) throws IOException {
        System.out.println("till Wizard1");
        Wizard1Controller tmp = new Wizard1Controller(controller, checkoutPaneController);
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.1.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));
    }
    @FXML
    public void toWizard2(Event event) {
        wizardPane2.toFront();
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));
    }
    @FXML
    public void toWizard3(Event event) {
        wizardPane3.toFront();
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.1.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));
    }
    @FXML
    public void toWizard4(Event event){
        wizardPane4.toFront();
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.1.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.png").toString()));
    }

    @FXML
    public void toCheckoutComplete(ActionEvent event) throws IOException {
        System.out.println("toCheckoutComplete användes");
        FXMLLoader tmp = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Parent root = tmp.load();
        controller = tmp.getController();
        Scene scene = new Scene(root, 1200, 700);
        controller.setDatahandler(dh);
        controller.showHomePageDetail();
        controller.createBigItemViewMap();
        controller.addShoppingCartListener();
        dh.getShoppingCart().clear();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
/*
        Parent tmp = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Scene tmpScene = new Scene(tmp);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tmpScene);
        window.show();

 */

        //TODO skriv klart denna
    }







}

