import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class myDetailsController extends AnchorPane {
    private Controller controller;
    private IMatDataHandler datahandler;
    @FXML private RadioButton myDetailsVisa;
    @FXML private RadioButton myDetailsMastercard;
    @FXML private TextField myDetailsName;
    @FXML private TextField myDetalisEmail;
    @FXML private TextField myDetalisAddress;
    @FXML private TextField myDetailsCard;
    @FXML private TextField myDetailsDate;
    @FXML private TextField myDetailsCVC;

    public myDetailsController(Controller c){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myDetails.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller = c;
        datahandler = IMatDataHandler.getInstance();

        myDetailsName.setText(datahandler.getCustomer().getFirstName() + " " + datahandler.getCustomer().getLastName());
        myDetalisAddress.setText(datahandler.getCustomer().getAddress());
        myDetalisEmail.setText(datahandler.getCustomer().getEmail());
        myDetailsCard.setText(datahandler.getCreditCard().getCardNumber());
        myDetailsDate.setText(datahandler.getCreditCard().getValidMonth() + "/" + datahandler.getCreditCard().getValidYear());
        myDetailsDate.setEditable(false);
        myDetailsDate.setStyle("-fx-fill-color: GREY;");
        myDetailsCVC.setEditable(false);
        myDetailsCVC.setStyle("-fx-fill-color: GREY;");

        ToggleGroup cardToggleGroup = new ToggleGroup();
        myDetailsMastercard.setToggleGroup(cardToggleGroup);
        myDetailsVisa.setToggleGroup(cardToggleGroup);
        if(datahandler.getCreditCard().getCardType().equals("Visa")) {
            myDetailsVisa.setSelected(true);
        } else {
            myDetailsMastercard.setSelected(true);
        }
        cardToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (cardToggleGroup.getSelectedToggle() != null) {
                    if (cardToggleGroup.getSelectedToggle() == myDetailsMastercard) {
                        datahandler.getCreditCard().setCardType("Mastercard");
                    } else {
                        datahandler.getCreditCard().setCardType("Visa");
                    }
                }
            }
        });

        myDetailsName.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.contains(" ")){
                int i = newValue.indexOf(" ");
                datahandler.getCustomer().setFirstName(newValue.substring(0, i));
                datahandler.getCustomer().setLastName(newValue.substring(i + 1));
            } else {
                datahandler.getCustomer().setFirstName(newValue);
            }

        });
        myDetalisAddress.textProperty().addListener((observable, oldValue, newValue) -> {
            datahandler.getCustomer().setAddress(newValue);
        });
        myDetalisEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            datahandler.getCustomer().setEmail(newValue);
        });
        myDetailsCard.textProperty().addListener((observable, oldValue, newValue) -> {
            datahandler.getCreditCard().setCardNumber(newValue);
        });
    }
}