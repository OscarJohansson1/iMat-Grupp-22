import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
       System.out.println("1");
        initialize();
       System.out.println("2");
        launch(args);
        System.out.println("3");

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
            //controller.newWindowMethod(stage);
            stage.setScene(scene);
            controller.showHomePageDetail();
            controller.initialize();
            stage.show();

            controller.addShoppingCartListener();
            //controller.newWindow();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
