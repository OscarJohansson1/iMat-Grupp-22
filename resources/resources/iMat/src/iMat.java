import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.util.Locale;
import java.util.ResourceBundle;

public class iMat extends Application {

    /**
     * hade det varit bättre om datahandler skapades i controller?
     */
    private static IMatDataHandler datahandler;
   private static Controller controller;

   public static void main(String[] args) {
        initialize();
        launch(args);
    }
    private static void initialize(){
        datahandler = IMatDataHandler.getInstance();
    }
    @Override
    public void start(Stage stage) throws Exception {

        try {

            FXMLLoader tmp = new FXMLLoader(getClass().getResource("homePage.fxml"));
            Parent root = tmp.load();
            controller = tmp.getController();
            Scene scene = new Scene(root, 1200, 720);
            controller.setDatahandler(datahandler);
            stage.setTitle("iMat");
            stage.setScene(scene);
            stage.show();
            controller.addShoppingCartListener();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
