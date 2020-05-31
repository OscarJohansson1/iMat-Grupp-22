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
import java.util.regex.Pattern;

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
    public Customer customer = dh.getCustomer();
    public CreditCard creditCard = dh.getCreditCard();


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
        checkoutPaneController.checkoutPane.getChildren().clear();
        checkoutPaneController.checkoutPane.getChildren().add(this);
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wizardPane2.toFront();
        setKnownInfo();
        addListeners();

        /*
        1.      Lägg till en ny ToggleGroup med metoden:

        difficultyToggleGroup = new ToggleGroup();

        2.     Sätt denna togglegroup på varje radioButton med metoden:

        radioButton.setToggleGroup(difficultyToggleGroup);

        Detta gör att endast en av radioknapparna kan vara valda åt gången och gör att vi kan läsa av vilken av knapparna
        som är vald i gruppen.

        3.      Välj vilken radiobutton som ska vara vald som default med hjälp av metoden:

        radioButton.setSelected(true);

        4.     Lägg till en lyssnare till selectedToggleProperty på Togglegroup som uppdaterar backend och träfflistan när
        användaren har ändrat i comboboxen:

        difficultyToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (difficultyToggleGroup.getSelectedToggle() != null) {
                    RadioButton selected = (RadioButton) difficultyToggleGroup.getSelectedToggle();
                    backendController.setDifficulty(selected.getText());
                    updateRecipeList();
                }
            }
        });

         */
        ToggleGroup difficultyToggleGroup = new ToggleGroup();


    }

    private void addListeners(){
        addressField2.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setAddress(newValue);
        });
        emailField2.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setEmail(newValue);
        });
        surname2.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setFirstName(newValue);
        });
        lastname2.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setLastName(newValue);
        });
        postCity2.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setPostAddress(newValue);
        });
        postcode2.textProperty().addListener((observable, oldValue, newValue) -> {
            String onlyDigits = newValue.replaceAll("[^0-9]+", "");
            if (onlyDigits.length() == 5){
                customer.setPostCode(onlyDigits);
            }
        });
        cardnumber3.textProperty().addListener((observable, oldValue, newValue) -> {
            creditCard.setCardNumber(newValue);
        });
        cardLasting3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length()>1){
                String month = newValue.substring(0,2);
                System.out.println(month);
                if (Pattern.matches("^[0-9]+$", month)) {
                    creditCard.setValidMonth(Integer.parseInt(month));
                }
            }
            if (newValue.length()>4){
                String year = newValue.substring(3,5);
                System.out.println(year);
                if (Pattern.matches("^[0-9]+$", year)) {
                    creditCard.setValidYear(Integer.parseInt(year));
                }
            }
        });
        /* ska ej sparas
        cvcCode3.textProperty().addListener((observable, oldValue, newValue) -> {
            creditCard.setVerificationCode(Integer.parseInt(cvcCode3.getText()));
        });

         */
        addressDelivery4.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setAddress(addressDelivery4.getText());
        });
        cardForDelivery4.textProperty().addListener((observable, oldValue, newValue) -> {
            creditCard.setCardNumber(cardForDelivery4.getText());
        });
    }


    private void setKnownInfo(){
        addressField2.setText(customer.getAddress());
        emailField2.setText(customer.getEmail());
        surname2.setText(customer.getFirstName());
        lastname2.setText(customer.getLastName());
        postCity2.setText(customer.getPostAddress());
        postcode2.setText(customer.getPostCode());
        //mastercard3
        //isaRadioButton3
        cardnumber3.setText(creditCard.getCardNumber());
        cardLasting3.setText(creditCard.getValidMonth() + "/" + creditCard.getValidYear());
        cvcCode3.setText(String.valueOf(creditCard.getVerificationCode()));
        addressDelivery4.setText(customer.getPostAddress());
        cardForDelivery4.setText(creditCard.getCardNumber());
    }


    private void saveInfo(){
        customer.setAddress(addressField2.getText());
        customer.setEmail(emailField2.getText());
        customer.setFirstName(surname2.getText());
        customer.setLastName(lastname2.getText());

        //dh.getCustomer().setMobilePhoneNumber();
        //dh.getCustomer().setPhoneNumber();
        customer.setPostAddress(postCity2.getText());
        customer.setPostCode(postcode2.getText());
        creditCard.setCardNumber(cardnumber3.getText());

        /*
        if (cardLasting3.getText().length()>1){
            creditCard.setValidMonth(Integer.parseInt(cardLasting3.getText().substring(0,1)));
        }
        if (cardLasting3.getText().length()>3){
            creditCard.setValidYear(Integer.parseInt(cardLasting3.getText().substring(3)));
        }
        if (cvcCode3.getText().length()==3){
            creditCard.setVerificationCode(Integer.parseInt(cvcCode3.getText()));
        }

         */

    }
    /*
    @FXML RadioButton mastercard3;@FXML RadioButton visaRadioButton3;
    @FXML TextField cardnumber3;@FXML TextField cardLasting3;@FXML TextField cvcCode3;
    private void addListenerCardType(){

    }
    private void addListenerCardnumber3(){

    }
    private void addListenerCardLasting3(){

    }
    private void addListenercvcCode3(){

    }

     */


    @FXML
    public void toWizard1(Event event) throws IOException {
        saveInfo();
        setKnownInfo();
        Wizard1Controller tmp = new Wizard1Controller(controller, checkoutPaneController);
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.1.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));

    }
    @FXML
    public void toWizard2(Event event) {
        saveInfo();
        setKnownInfo();
        wizardPane2.toFront();
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));

    }
    @FXML
    public void toWizard3(Event event) {
        saveInfo();
        setKnownInfo();
        wizardPane3.toFront();
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.1.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));

    }
    @FXML
    public void toWizard4(Event event){
        saveInfo();
        setKnownInfo();
        wizardPane4.toFront();
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.1.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.png").toString()));

    }

    @FXML
    public void toCheckoutComplete(ActionEvent event) throws IOException {
        // Flytta kod till knappen på "Tack för beställning" sidan

        /*FXMLLoader tmp = new FXMLLoader(getClass().getResource("homePage.fxml"));
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
        window.show();*/
        checkoutPaneController.checkoutPane.getChildren().clear();
        loggInCheckoutCompleteController temp = new loggInCheckoutCompleteController(checkoutPaneController);
        checkoutPaneController.checkoutPane.getChildren().add(temp);
        checkoutPaneController.setVisibleFalse();

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

