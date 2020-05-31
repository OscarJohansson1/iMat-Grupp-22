import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class myHistoryDetailsController extends AnchorPane {
    private Controller controller;
    @FXML private Label dateLabel;
    @FXML private Label allProductsLabel;
    @FXML private Button showMoreButton;
    @FXML private Label showMoreLabel;


    public myHistoryDetailsController(Controller c, Order order, myHistoryController history){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myDetalisToDetalis.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = c;
        String s = order.getDate().toString();
        int first = s.indexOf(" ");
        int second = s.indexOf(" ", first + 1);
        int third = s.indexOf(" ", second + 1);
        if(s.substring(first + 1, second).equals("May")) {
            dateLabel.setText((s.substring(second + 1, third) + " Maj"));
        } else if (s.substring(first + 1, second).equals("June")) {
            dateLabel.setText((s.substring(second + 1, third) + " Juni"));
        } else {
            dateLabel.setText((s.substring(first + 1, third)));
        }
        StringBuilder sb = new StringBuilder();
        for(ShoppingItem si : order.getItems()) {
            sb.append(si.getProduct().getName()).append(", ");
        }
        sb.delete(sb.lastIndexOf(", "), sb.lastIndexOf(", ") + 1);
        allProductsLabel.setText(sb.toString());

        showMoreLabel.setOnMouseClicked(m->{
            history.showSecondPane(order);
        });

        showMoreButton.setOnMouseClicked(m->{
            history.showSecondPane(order);
        });
    }


}
