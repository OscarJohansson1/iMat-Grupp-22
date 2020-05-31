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
            Scene scene = new Scene(root, 1200, 700);
            controller.setDatahandler(datahandler);
            stage.setTitle("iMat");
            //controller.newWindowMethod(stage);
            stage.setScene(scene);
            stage.setResizable(false);
            controller.showHomePageDetail();
            stage.show();
            controller.createBigItemViewMap();
            controller.addShoppingCartListener();
            CheckoutPaneController c = new CheckoutPaneController();
            c.setController(controller);
            c.controller.createBigItemViewMap();
            Thread printingHook = new Thread(() -> datahandler.shutDown());
            Runtime.getRuntime().addShutdownHook(printingHook);



            //TODO skapa Wizard1Controller om det behövs
            //Wizard1Controller wiz = new Wizard1Controller();
            //controller.newWindow();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
