import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    @FXML private SplitPane splitPane;
    @FXML public FlowPane itemViewPane;

    private Controller controller;
    private IMatDataHandler dh;
    private String currentString;
    private ArrayList<ProductCategory> customList;

    //Strängen är kategorin som användaren klickat på.
    public FruitsAndGreensController(Controller c, String s){

        //TODO Pilar längst ner saknas

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

        currentString = s;
        customList = new ArrayList<>();

        categoryShowAllLabel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    itemViewPane.getChildren().clear();
                    uncheckAll();
                    showAllLabel();
                } else if (isEverythingUnchecked()) {
                    categoryShowAllLabel.setSelected(true);
                }

            }
        });
        categoryOneLabel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    if(categoryShowAllLabel.isSelected()){
                        itemViewPane.getChildren().clear();
                        customList.clear();
                        categoryShowAllLabel.setSelected(false);
                    }
                    itemViewPane.getChildren().clear();
                    showOneLabel();
                } else {
                    itemViewPane.getChildren().clear();
                    hideOneLabel();
                    if(customList.isEmpty()){
                        categoryShowAllLabel.setSelected(true);
                    }
                }
            }
        });
        categoryTwoLabel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    if(categoryShowAllLabel.isSelected()){
                        customList.clear();
                        categoryShowAllLabel.setSelected(false);
                    }
                    itemViewPane.getChildren().clear();
                    showTwoLabel();
                } else {
                    itemViewPane.getChildren().clear();
                    hideTwoLabel();
                    if(customList.isEmpty()){
                        categoryShowAllLabel.setSelected(true);
                    }
                }

            }
        });
        categoryThreeLabel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    if(categoryShowAllLabel.isSelected()){
                        customList.clear();
                        categoryShowAllLabel.setSelected(false);
                    }
                    itemViewPane.getChildren().clear();
                    showThreeLabel();
                } else {
                    itemViewPane.getChildren().clear();
                    hideThreeLabel();
                    if(customList.isEmpty()){
                        categoryShowAllLabel.setSelected(true);
                    }
                }

            }
        });
        categoryFourLabel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    if(categoryShowAllLabel.isSelected()){
                        customList.clear();
                        categoryShowAllLabel.setSelected(false);
                    }
                    itemViewPane.getChildren().clear();
                    showFourLabel();
                } else {
                    itemViewPane.getChildren().clear();
                    hideFourLabel();
                    if(customList.isEmpty()){
                        categoryShowAllLabel.setSelected(true);
                    }
                }

            }
        });
        categoryFiveLabel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    if(categoryShowAllLabel.isSelected()){
                        customList.clear();
                        categoryShowAllLabel.setSelected(false);
                    }
                    itemViewPane.getChildren().clear();
                    showFiveLabel();
                } else {
                    itemViewPane.getChildren().clear();
                    hideFiveLabel();
                    if(customList.isEmpty()){
                        categoryShowAllLabel.setSelected(true);
                    }
                }

            }
        });

        showProducts(s);
    }

    public void showProducts(String s){
        switch(s){
            case "Frukter och bär":
                setFruits();
                splitPane.setDividerPosition(0, 0.30);
                updateItemList2(dh, this.controller, new ArrayList<>(Arrays.asList(ProductCategory.BERRY, ProductCategory.CITRUS_FRUIT,
                        ProductCategory.EXOTIC_FRUIT, ProductCategory.MELONS, ProductCategory.FRUIT)));
                break;
            case "Grönsaker":
                setGreens();
                splitPane.setDividerPosition(0, 0.30);
                updateItemList2(dh, this.controller, new ArrayList<>(Arrays.asList(ProductCategory.POD, ProductCategory.CABBAGE,
                        ProductCategory.VEGETABLE_FRUIT, ProductCategory.HERB, ProductCategory.ROOT_VEGETABLE)));
                break;
            case "Mejeriprodukter":
                setMejeriprodukter();
                splitPane.setDividerPosition(0, 0.23);
                updateItemList2(dh, this.controller, ProductCategory.DAIRIES);
                break;
            case "Bröd":
                setBröd();
                splitPane.setDividerPosition(0, 0.23);
                updateItemList2(dh, this.controller, ProductCategory.BREAD);
                break;
            case "Fisk":
                setFisk();
                splitPane.setDividerPosition(0, 0.23);
                updateItemList2(dh, this.controller, ProductCategory.FISH);
                break;
            case "Kött":
                setKött();
                splitPane.setDividerPosition(0, 0.23);
                updateItemList2(dh, this.controller, ProductCategory.MEAT);
                break;
            case "Skafferi":
                setSkafferi();
                splitPane.setDividerPosition(0, 0.30);
                updateItemList2(dh, this.controller, new ArrayList<>(Arrays.asList(ProductCategory.FLOUR_SUGAR_SALT,
                        ProductCategory.NUTS_AND_SEEDS, ProductCategory.PASTA, ProductCategory.POTATO_RICE, ProductCategory.SWEET)));
                break;
            case "Drycker":
                setDrycker();
                splitPane.setDividerPosition(0, 0.23);
                updateItemList2(dh, this.controller, new ArrayList<>(Arrays.asList(ProductCategory.COLD_DRINKS,
                        ProductCategory.HOT_DRINKS)));
                break;
            default:
                splitPane.setDividerPosition(0, 0.15);
                categoryTitleLabel.setText("Sökresultat för \"" +  s + "\":");
        }
    }

    public void updateItemList2(IMatDataHandler dh, Controller c, ProductCategory pc){
        updateItemList2(dh, c, new ArrayList<>(Arrays.asList(pc)));
    }

    public void updateItemList2(IMatDataHandler dh, Controller c, ArrayList<ProductCategory> pc){

        int count = 0;
        List<Product> productList = new ArrayList<>();
        for( ProductCategory p : pc) {
            productList.addAll(dh.getProducts(p));
        }
        BigItemView tmpItem;

        for (Product p : productList){
            count++;
            tmpItem = c.bigItemViewMap.get(p.getName());
            itemViewPane.getChildren().add(tmpItem);
        }
        itemViewPane.setPadding(new Insets(10,10,10,27));
        itemViewPane.setVgap(20);
        itemViewPane.setHgap(20);
    }

    private void setFruits(){
        categoryOneLabel.setText("Bär");
        categoryTwoLabel.setText("Citrusfrukter");
        categoryThreeLabel.setText("Exotiska frukter");
        categoryFourLabel.setText("Meloner");
        categoryFiveLabel.setText("Stenfrukter");
        categorySixLabel.setVisible(false);
        categorySevenLabel.setVisible(false);
        categoryEightLabel.setVisible(false);
        categoryNineLabel.setVisible(false);
        categoryTenLabel.setVisible(false);
    }

    private void setGreens(){
        categoryOneLabel.setText("Baljväxter");
        categoryTwoLabel.setText("Kål");
        categoryThreeLabel.setText("Grönsaksfrukter");
        categoryFourLabel.setText("Örtkryddor");
        categoryFiveLabel.setText("Rotfrukter");
        categorySixLabel.setVisible(false);
        categorySevenLabel.setVisible(false);
        categoryEightLabel.setVisible(false);
        categoryNineLabel.setVisible(false);
        categoryTenLabel.setVisible(false);
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
        categorySixLabel.setVisible(false);
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
    private void setSearch(){
        categoryShowAllLabel.setVisible(false);
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

    public void showAllLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList = new ArrayList<>(Arrays.asList(ProductCategory.BERRY, ProductCategory.CITRUS_FRUIT,
                        ProductCategory.EXOTIC_FRUIT, ProductCategory.FRUIT, ProductCategory.MELONS));
                break;
            case "Grönsaker":
                customList = new ArrayList<>(Arrays.asList(ProductCategory.POD, ProductCategory.CABBAGE,
                        ProductCategory.VEGETABLE_FRUIT, ProductCategory.HERB, ProductCategory.ROOT_VEGETABLE));
                break;
            case "Mejeriprodukter":
                customList.add(ProductCategory.DAIRIES);
                break;
            case "Bröd":
                customList.add(ProductCategory.BREAD);
                break;
            case "Fisk":
                customList.add(ProductCategory.FISH);
                break;
            case "Kött":
                customList.add(ProductCategory.MEAT);
                break;
            case "Skafferi":
                customList = new ArrayList<>(Arrays.asList(ProductCategory.FLOUR_SUGAR_SALT,
                        ProductCategory.NUTS_AND_SEEDS, ProductCategory.PASTA, ProductCategory.POTATO_RICE, ProductCategory.SWEET));
                break;
            case "Drycker":
                customList = new ArrayList<>(Arrays.asList(ProductCategory.COLD_DRINKS,
                        ProductCategory.HOT_DRINKS));
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void showOneLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.add(0, ProductCategory.BERRY);
                break;
            case "Grönsaker":
                customList.add(0, ProductCategory.POD);
                break;
            case "Skafferi":
                customList.add(0, ProductCategory.FLOUR_SUGAR_SALT);
                break;
            case "Drycker":
                customList.add(0, ProductCategory.COLD_DRINKS);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void hideOneLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.remove(ProductCategory.BERRY);
                break;
            case "Grönsaker":
                customList.remove(ProductCategory.POD);
                break;
            case "Skafferi":
                customList.remove(ProductCategory.FLOUR_SUGAR_SALT);
                break;
            case "Drycker":
                customList.remove(ProductCategory.COLD_DRINKS);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void showTwoLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.add(0, ProductCategory.CITRUS_FRUIT);
                break;
            case "Grönsaker":
                customList.add(0, ProductCategory.CABBAGE);
                break;
            case "Skafferi":
                customList.add(0, ProductCategory.NUTS_AND_SEEDS);

                break;
            case "Drycker":
                customList.add(0, ProductCategory.HOT_DRINKS);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void hideTwoLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.remove(ProductCategory.CITRUS_FRUIT);
                break;
            case "Grönsaker":
                customList.remove(ProductCategory.CABBAGE);
                break;
            case "Skafferi":
                customList.remove(ProductCategory.NUTS_AND_SEEDS);

                break;
            case "Drycker":
                customList.remove(ProductCategory.HOT_DRINKS);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void showThreeLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.add(0, ProductCategory.EXOTIC_FRUIT);
                break;
            case "Grönsaker":
                customList.add(0, ProductCategory.VEGETABLE_FRUIT);
                break;
            case "Skafferi":
                customList.add(0, ProductCategory.PASTA);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void hideThreeLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.remove(ProductCategory.EXOTIC_FRUIT);
                break;
            case "Grönsaker":
                customList.remove(ProductCategory.VEGETABLE_FRUIT);
                break;
            case "Skafferi":
                customList.remove(ProductCategory.PASTA);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void showFourLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.add(0, ProductCategory.MELONS);
                break;
            case "Grönsaker":
                customList.add(0, ProductCategory.HERB);
                break;
            case "Skafferi":
                customList.add(0, ProductCategory.POTATO_RICE);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void hideFourLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.remove(ProductCategory.MELONS);
                break;
            case "Grönsaker":
                customList.remove(ProductCategory.HERB);
                break;
            case "Skafferi":
                customList.remove(ProductCategory.POTATO_RICE);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void showFiveLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.add(0, ProductCategory.FRUIT);
                break;
            case "Grönsaker":
                customList.add(0, ProductCategory.ROOT_VEGETABLE);
                break;
            case "Skafferi":
                customList.add(0, ProductCategory.SWEET);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    public void hideFiveLabel() {
        switch(currentString){
            case "Frukter och bär":
                customList.remove(ProductCategory.FRUIT);
                break;
            case "Grönsaker":
                customList.remove(ProductCategory.ROOT_VEGETABLE);
                break;
            case "Skafferi":
                customList.remove(ProductCategory.SWEET);
                break;
            default:
                System.out.println("oops, något fel med kategoricheckboxes");
        }
        updateItemList2(dh, this.controller, customList);
    }

    private void uncheckAll() {
        categoryOneLabel.setSelected(false);
        categoryTwoLabel.setSelected(false);
        categoryThreeLabel.setSelected(false);
        categoryFourLabel.setSelected(false);
        categoryFiveLabel.setSelected(false);
        categorySixLabel.setSelected(false);
        categorySevenLabel.setSelected(false);
        categoryEightLabel.setSelected(false);
        categoryNineLabel.setSelected(false);
        categoryTenLabel.setSelected(false);
    }

    private boolean isEverythingUnchecked() {
        return !(categoryOneLabel.isSelected() || categoryTwoLabel.isSelected() || categoryThreeLabel.isSelected() ||
                categoryFourLabel.isSelected() || categoryFiveLabel.isSelected() || categorySixLabel.isSelected() ||
                categorySevenLabel.isSelected() || categoryEightLabel.isSelected() || categoryNineLabel.isSelected() ||
                categoryTenLabel.isSelected());
    }
}

