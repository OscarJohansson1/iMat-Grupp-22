import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class FruitsAndGreensController extends AnchorPane {
    @FXML private AnchorPane categoryItemViewPane;
    @FXML private AnchorPane categoryViewPane;
    @FXML private Label categoryTitleLabel;
    @FXML private CheckBox categoryShowAllLabel;
    @FXML private CheckBox categoryOneLabel;
    @FXML private CheckBox categoryTwoLabel;
    @FXML private CheckBox categoryThreeLabel;
    @FXML private CheckBox categoryFourLabel;
    @FXML private CheckBox categoryFiveLabel;
    @FXML private CheckBox categorySixLabel;
    @FXML private CheckBox categorySevenLabel;
    @FXML private CheckBox categoryEightLabel;
    @FXML private CheckBox categoryNineLabel;
    @FXML private CheckBox categoryTenLabel;
    @FXML private AnchorPane itemViewPane;

    private Controller controller;

    //Strängen är kategorin som användaren klickat på.
    public FruitsAndGreensController(Controller c, String s){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fruitsAndGreens.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller = c;

        categoryShowAllLabel.setSelected(true);
        categoryTitleLabel.setText(s);
        switch(s){
            case "Frukt och grönt":
                setFruitsAndGreens();
                break;
            case "Mejeriprodukter":
                setMejeriprodukter();
                break;
            case "Bröd":
                setBröd();
                break;
            case "Fisk":
                setFisk();
                break;
            case "Kött":
                setKött();
                break;
            case "Skafferi":
                setSkafferi();
                break;
            case "Drycker":
                setDrycker();
                break;
            default:
                System.out.println("något gick fel med kategorierna");
        }

    }

    private void setFruitsAndGreens(){
        categoryOneLabel.setText("Baljväxter");
        categoryTwoLabel.setText("Bär");
        categoryThreeLabel.setText("Citrusfrukter");
        categoryFourLabel.setText("Exotiska frukter");
        categoryFiveLabel.setText("Grönsaksfrukter");
        categorySixLabel.setText("Kål");
        categorySevenLabel.setText("Meloner");
        categoryEightLabel.setText("Rotfrukter");
        categoryNineLabel.setText("Stenfrukter");
        categoryTenLabel.setText("Örtfrukter");
    }

    private void setMejeriprodukter(){
        categoryOneLabel.setVisible(false);
        categoryTwoLabel.setVisible(false);
        categoryThreeLabel.setVisible(false);
        categoryFourLabel.setVisible(false);
        categoryFiveLabel.setVisible(false);
        categorySixLabel.setVisible(false);
        categorySevenLabel.setVisible(false);
        categoryEightLabel.setVisible(false);
        categoryNineLabel.setVisible(false);
        categoryTenLabel.setVisible(false);
    }
    private void setBröd(){
        categoryOneLabel.setVisible(false);
        categoryTwoLabel.setVisible(false);
        categoryThreeLabel.setVisible(false);
        categoryFourLabel.setVisible(false);
        categoryFiveLabel.setVisible(false);
        categorySixLabel.setVisible(false);
        categorySevenLabel.setVisible(false);
        categoryEightLabel.setVisible(false);
        categoryNineLabel.setVisible(false);
        categoryTenLabel.setVisible(false);
    }
    private void setFisk(){
        categoryOneLabel.setVisible(false);
        categoryTwoLabel.setVisible(false);
        categoryThreeLabel.setVisible(false);
        categoryFourLabel.setVisible(false);
        categoryFiveLabel.setVisible(false);
        categorySixLabel.setVisible(false);
        categorySevenLabel.setVisible(false);
        categoryEightLabel.setVisible(false);
        categoryNineLabel.setVisible(false);
        categoryTenLabel.setVisible(false);
    }
    private void setKött(){
        categoryOneLabel.setVisible(false);
        categoryTwoLabel.setVisible(false);
        categoryThreeLabel.setVisible(false);
        categoryFourLabel.setVisible(false);
        categoryFiveLabel.setVisible(false);
        categorySixLabel.setVisible(false);
        categorySevenLabel.setVisible(false);
        categoryEightLabel.setVisible(false);
        categoryNineLabel.setVisible(false);
        categoryTenLabel.setVisible(false);
    }
    private void setSkafferi(){
        categoryOneLabel.setText("FLOUR_SUGAR_SALT");
        categoryTwoLabel.setText("NUTS_AND_SEEDS");
        categoryThreeLabel.setText("PASTA");
        categoryFourLabel.setText("POTATO_RICE");
        categoryFiveLabel.setText("SWEET");
        categorySixLabel.setText("HERB");
        categorySevenLabel.setVisible(false);
        categoryEightLabel.setVisible(false);
        categoryNineLabel.setVisible(false);
        categoryTenLabel.setVisible(false);
    }
    private void setDrycker(){
        categoryOneLabel.setText("HOT_DRINKS");
        categoryTwoLabel.setText("COLD_DRINKS");
        categoryThreeLabel.setVisible(false);
        categoryFourLabel.setVisible(false);
        categoryFiveLabel.setVisible(false);
        categorySixLabel.setVisible(false);
        categorySevenLabel.setVisible(false);
        categoryEightLabel.setVisible(false);
        categoryNineLabel.setVisible(false);
        categoryTenLabel.setVisible(false);
    }

}

