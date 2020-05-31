import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class myFavoriteController extends AnchorPane {
    private Controller controller;
    private IMatDataHandler datahandler;
    @FXML private FlowPane myFavoriteFlowPane;

    public myFavoriteController(Controller c){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myFavorite.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.datahandler = IMatDataHandler.getInstance();
        this.controller = c;
        showFavorites(datahandler, controller);
    }

    public void showFavorites(IMatDataHandler dh, Controller c){

        int count = 0;
        List<Product> productList = dh.favorites();
        if(productList != null) {
            BigItemView tmpItem;

            for (Product p : productList) {
                count++;
                tmpItem = c.bigItemViewMap.get(p.getName());
                myFavoriteFlowPane.getChildren().add(tmpItem);
            }
            myFavoriteFlowPane.setPadding(new Insets(10, 10, 10, 27));
            myFavoriteFlowPane.setVgap(20);
            myFavoriteFlowPane.setHgap(20);
        }
    }
}
