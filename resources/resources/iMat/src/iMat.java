import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.util.Locale;
import java.util.ResourceBundle;

public class iMat extends Application
 {

    @Override
    public void start(Stage stage) throws Exception {

        //ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/resources/iMat/sceneImages/rb/rb_sv_SE", Locale.forLanguageTag("sv-se"));

        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));

        Scene scene = new Scene(root, 800, 500);

        //stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        initialize();
        launch(args);

    }
    private static void initialize(){
        IMatDataHandler datahandler = IMatDataHandler.getInstance();
        //antagligen mer som ska skapas
    }
}
