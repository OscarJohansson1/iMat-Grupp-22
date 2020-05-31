import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML private Label myHistoryDate;
    @FXML private Label myHistoryTime;
    @FXML private Label myHistoryTotalPrize;

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
        writeTimeAndDate(order);
        writeTotalPrize(order);
        flowPane2.getChildren().clear();
        for (ShoppingItem si : order.getItems()){
            myHistoryDetailsDetails mhdd = new myHistoryDetailsDetails(controller, si);
            flowPane2.getChildren().add(mhdd);
        }
    }

    public void writeTimeAndDate(Order order) {
        String s = order.getDate().toString();
        int first = s.indexOf(" ");
        int second = s.indexOf(" ", first + 1);
        int third = s.indexOf(" ", second + 1);
        int fourth = s.indexOf(" ", third + 1);
        if(s.substring(first + 1, second).equals("May")) {
            myHistoryDate.setText(("Datum: " + s.substring(second + 1, third) + " Maj"));
        } else if (s.substring(first + 1, second).equals("June")) {
            myHistoryDate.setText(("Datum: " + s.substring(second + 1, third) + " Juli"));
        } else {
            myHistoryDate.setText(("Datum: " + s.substring(first + 1, third)));
        }
        myHistoryTime.setText("Tid: kl. " + s.substring(third + 1, fourth - 3));
    }

    public void writeTotalPrize(Order order){
        double total = 0;
        for(ShoppingItem si : order.getItems()){
            total += si.getTotal();
        }
        myHistoryTotalPrize.setText("Totalt: " + total + " kr");
    }

}