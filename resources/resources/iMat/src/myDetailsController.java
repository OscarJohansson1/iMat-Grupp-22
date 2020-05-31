import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
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

        ToggleGroup cardToggleGroup = new ToggleGroup();
        myDetailsMastercard.setToggleGroup(cardToggleGroup);
        myDetailsVisa.setToggleGroup(cardToggleGroup);
        if(datahandler.getCreditCard().getCardType() == "Visa") {
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
    }
}