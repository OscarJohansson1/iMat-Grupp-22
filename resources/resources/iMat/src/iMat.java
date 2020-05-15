import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.util.Locale;
import java.util.ResourceBundle;

public class iMat extends Application
 {

    @Override
    public void start(Stage stage) throws Exception {

        try {

            FXMLLoader tmp = new FXMLLoader(getClass().getResource("homePage.fxml"));
            Parent root = tmp.load();
            Controller controller = tmp.getController();
            Scene scene = new Scene(root, 1200, 720);
            controller.setDatahandler(IMatDataHandler.getInstance());
            stage.setTitle("iMat");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        initialize();
        launch(args);

    }
    private static void initialize(){
        //IMatDataHandler datahandler = IMatDataHandler.getInstance();
        //antagligen mer som ska skapas
    }
}
