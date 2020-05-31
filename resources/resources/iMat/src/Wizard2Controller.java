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
import java.util.function.UnaryOperator;
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
    private TextFormatter<String> textFormatter;


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

    private boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    private boolean isValidPostCode(String postcode){
        String onlyDigits = postcode.replaceAll("[^0-9]+", "");
        if (onlyDigits.length()==5){
            customer.setPostCode(onlyDigits);
            return true;
        }
        else {
            return false;
        }
    }
    private boolean isValidAddress(String address){
        if (address.length()>0){
            customer.setAddress(address);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isValidCardNumber(String cardnumber){
        String onlyDigits = cardnumber.replaceAll("[^0-9]+", "");
        if (onlyDigits.length()==16){
            creditCard.setCardNumber(onlyDigits);
            return true;
        }
        else {
            return false;
        }
    }
    private boolean isValidCardLasting3(String str){
        if (str.length()==5){
            if ((Pattern.matches("^[0-9]+$", str.substring(0,2))) & (Pattern.matches("^[0-9]+$", str.substring(3,5))) & str.contains("/")){
                return true;
            }
        }
        if (str.length()==4){
            if ((Pattern.matches("^[0-9]+$", str.substring(0,1))) & (Pattern.matches("^[0-9]+$", str.substring(2,4))) & str.contains("/") & (str.charAt(0)!=0)){
                return true;
            }
        }
        return false;
    }
    private boolean isValidCvcCode3(String str){
        String onlyDigits = str.replaceAll("[^0-9]+", "");
        if (onlyDigits.length()==3){
            creditCard.setVerificationCode(Integer.parseInt(onlyDigits));
            return true;
        }
        else{
            return false;
        }
    }
    private void addListeners(){
        addressField2.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setAddress(newValue);
            if (isValidAddress(addressField2.getText())){
                addressField2.setStyle(null);
            }
        });
        emailField2.textProperty().addListener((observable, oldValue, newValue) -> {
                customer.setEmail(newValue);
                if (isValidEmail(emailField2.getText())){
                    emailField2.setStyle(null);
                }
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
            customer.setPostCode(newValue);
            if (isValidPostCode(postcode2.getText())){
                postcode2.setStyle(null);
            }
        });
        cardnumber3.textProperty().addListener((observable, oldValue, newValue) -> {
            creditCard.setCardNumber(newValue);
            if (isValidCardNumber(cardnumber3.getText())){
                cardnumber3.setStyle(null);
            }
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
            if (isValidCardLasting3(cardLasting3.getText())){
                cardLasting3.setStyle(null);
            }
        });


        cvcCode3.textProperty().addListener((observable, oldValue, newValue) -> {
            //creditCard.setVerificationCode(Integer.parseInt(cvcCode3.getText()));
            if(isValidCvcCode3(cvcCode3.getText())){
                cvcCode3.setStyle(null);
            }
        });

        addressDelivery4.textProperty().addListener((observable, oldValue, newValue) -> {
            customer.setAddress(addressDelivery4.getText());
        });
        cardForDelivery4.textProperty().addListener((observable, oldValue, newValue) -> {
            creditCard.setCardNumber(cardForDelivery4.getText());
        });

        ToggleGroup cardToggleGroup = new ToggleGroup();
        mastercard3.setToggleGroup(cardToggleGroup);
        visaRadioButton3.setToggleGroup(cardToggleGroup);
        if(creditCard.getCardType() == "Visa") {
            visaRadioButton3.setSelected(true);
        } else {
            mastercard3.setSelected(true);
        }
        cardToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (cardToggleGroup.getSelectedToggle() != null) {
                    if (cardToggleGroup.getSelectedToggle() == mastercard3) {
                        creditCard.setCardType("Mastercard");
                     } else {
                        creditCard.setCardType("Visa");
                    }
                }
            }
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
        addressDelivery4.setText(customer.getAddress());
        cardForDelivery4.setText(creditCard.getCardNumber());
    }

    private void isValidMonth(){
        if (cardLasting3.getText().length()>1){
            String month = cardLasting3.getText().substring(0,2);
            if (Pattern.matches("^[0-9]+$", month)) {
                creditCard.setValidMonth(Integer.parseInt(month));
            }
        }
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

        //cardLasting3.getText().substring(0,2);
        //creditCard.setValidMonth();
        //creditCard.setValidYear();
        creditCard.setVerificationCode(Integer.parseInt(cvcCode3.getText()));

        customer.setAddress(addressDelivery4.getText());
        creditCard.setCardNumber(cardForDelivery4.getText());

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

    @FXML
    public void toWizard1(Event event) throws IOException {
        //saveInfo();
        setKnownInfo();
        Wizard1Controller tmp = new Wizard1Controller(controller, checkoutPaneController);
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.1.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));

    }
    @FXML
    public void toWizard2(Event event) {
        //saveInfo();
        setKnownInfo();
        wizardPane2.toFront();
        checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
        checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.png").toString()));
        checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
        checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));


    }
    @FXML
    public void toWizard3(Event event) {

        if (isValidEmail(emailField2.getText()) & isValidPostCode(postcode2.getText()) & isValidAddress(addressField2.getText())){
            setKnownInfo();
            wizardPane3.toFront();
            checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
            checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.1.png").toString()));
            checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.png").toString()));
            checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.1.png").toString()));
        }
        else{
            if (!isValidEmail(emailField2.getText())){
                emailField2.setStyle(" -fx-border-color: RED;");
            }
            if (!isValidPostCode(postcode2.getText())){
                postcode2.setStyle(" -fx-border-color: RED;");
            }
            if (!isValidAddress(addressField2.getText())){
                addressField2.setStyle(" -fx-border-color: RED;");
            }
        }

    }
    @FXML
    public void toWizard4(Event event) {
        //saveInfo();
        if (isValidCardNumber(cardnumber3.getText()) & isValidCardLasting3(cardLasting3.getText()) & isValidCvcCode3(cvcCode3.getText())) {
            setKnownInfo();
            wizardPane4.toFront();
            checkoutPaneController.Step1Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-1.1.png").toString()));
            checkoutPaneController.Step2Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-2.1.png").toString()));
            checkoutPaneController.Step3Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-3.1.png").toString()));
            checkoutPaneController.Step4Image.setImage(new Image(getClass().getResource("/sceneImages/Wizard-4.png").toString()));
        } else {
            if (!isValidCardNumber(cardnumber3.getText())) {
                cardnumber3.setStyle(" -fx-border-color: RED;");
            }
            if (!isValidCardLasting3(cardLasting3.getText())) {
                cardLasting3.setStyle(" -fx-border-color: RED;");
            }
            if (!isValidCvcCode3(cvcCode3.getText())) {
                cvcCode3.setStyle(" -fx-border-color: RED;");
            }

        }
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
        dh.placeOrder(true);
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

