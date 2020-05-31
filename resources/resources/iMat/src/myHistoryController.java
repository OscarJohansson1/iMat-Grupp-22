import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class myHistoryController extends AnchorPane {
    private Controller controller;
    private IMatDataHandler datahandler;
    @FXML private FlowPane flowPane1;
    @FXML private FlowPane flowPane2;
    @FXML private AnchorPane fristPane;
    @FXML private AnchorPane secondPane;
    @FXML private Button backButtonMyHistory;

    public myHistoryController(Controller c){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myHistory.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        datahandler = IMatDataHandler.getInstance();
        this.controller = c;

        fristPane.toFront();
        backButtonMyHistory.setOnMouseClicked(m->{
            fristPane.toFront();
        });

        addOrders();
    }

    private void addOrders() {
        flowPane1.getChildren().clear();
        for (Order o : datahandler.getOrders()){
            myHistoryDetailsController m = new myHistoryDetailsController(controller, o, this);
            flowPane1.getChildren().add(m);
        }
    }

    public void showSecondPane(Order order) {
        secondPane.toFront();
        fristPane.toBack();
        flowPane2.getChildren().clear();
        for (ShoppingItem si : order.getItems()){
            myHistoryDetailsDetails mhdd = new myHistoryDetailsDetails(controller, si);
            flowPane2.getChildren().add(mhdd);
        }
    }

}