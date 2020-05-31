import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class myHistoryDetailsDetails extends AnchorPane {
    private Controller controller;
    private IMatDataHandler datahandler;
    @FXML
    private Label name;
    @FXML private Label cost;
    @FXML private Label amount;
    @FXML private Button add;
    private ShoppingItem si;


    public myHistoryDetailsDetails(Controller c, ShoppingItem si){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myDetailsItems2.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = c;
        datahandler = IMatDataHandler.getInstance();
        this.si = si;

        name.setText(si.getProduct().getName());
        amount.setText((si.getProduct().getPrice() * si.getAmount()) + " kr");
        cost.setText(si.getAmount() + si.getProduct().getUnitSuffix());
        add.setOnMouseClicked(m->{
            for (ShoppingItem s : datahandler.getShoppingCart().getItems()){
                if (s.getProduct() == si.getProduct()){
                    s.setAmount(s.getAmount() + si.getAmount());
                    datahandler.getShoppingCart().fireShoppingCartChanged(s, true);
                    return;
                }
            }
            datahandler.getShoppingCart().addProduct(si.getProduct(), si.getAmount());
        });
    }
}
