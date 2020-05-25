import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.List;


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
    @FXML public AnchorPane itemViewPane;

    private Controller controller;
    private IMatDataHandler dh;

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
        this.dh = IMatDataHandler.getInstance();

        switch(s){
            case "Frukt och grönt":
                setFruitsAndGreens();
                updateItemList2(dh, this.controller);
                break;
            case "Mejeriprodukter":
                setMejeriprodukter();
                updateItemList2(dh, this.controller);
                break;
            case "Bröd":
                setBröd();
                updateItemList2(dh, this.controller);
                break;
            case "Fisk":
                setFisk();
                updateItemList2(dh, this.controller);
                break;
            case "Kött":
                setKött();
                updateItemList2(dh, this.controller);
                break;
            case "Skafferi":
                setSkafferi();
                updateItemList2(dh, this.controller);
                break;
            case "Drycker":
                setDrycker();
                updateItemList2(dh, this.controller);
                break;
            default:
                System.out.println("något gick fel med kategorierna");
        }

    }
    public void updateItemList2(IMatDataHandler dh, Controller c){
        double x = 10;
        double y = -20;
        int count = 0;
        List<Product> productList = dh.getProducts();
        BigItemView tmpItem;

        for (Product p : productList){
            count++;
            tmpItem = c.bigItemViewMap.get(p.getName());
            tmpItem.setLayoutX(x);
            tmpItem.setLayoutY(y);
            System.out.println(tmpItem.getLayoutY());
            itemViewPane.getChildren().add(tmpItem);
            System.out.println("la till i itemViewPane");
            x=x+190;
            if (count%3 == 0){
                y=y+150;
                x=10;
                //hej
            }
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

