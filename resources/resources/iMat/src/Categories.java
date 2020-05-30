import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.ArrayList;
import java.util.Arrays;

public class Categories {
    private ArrayList<ProductCategory> fruit = new ArrayList<>(Arrays.asList(ProductCategory.BERRY, ProductCategory.CITRUS_FRUIT,
            ProductCategory.EXOTIC_FRUIT, ProductCategory.FRUIT, ProductCategory.MELONS));
    private ArrayList<ProductCategory> greens = new ArrayList<>(Arrays.asList(ProductCategory.POD, ProductCategory.ROOT_VEGETABLE,
            ProductCategory.VEGETABLE_FRUIT));
    private ArrayList<ProductCategory> dairy = new ArrayList<>(Arrays.asList(ProductCategory.DAIRIES));
    private ArrayList<ProductCategory> bread = new ArrayList<>(Arrays.asList(ProductCategory.BREAD));
    private ArrayList<ProductCategory> fish = new ArrayList<>(Arrays.asList(ProductCategory.FISH));
    private ArrayList<ProductCategory> meat = new ArrayList<>(Arrays.asList(ProductCategory.MEAT));
    private ArrayList<ProductCategory> dryFood = new ArrayList<>(Arrays.asList(ProductCategory.FLOUR_SUGAR_SALT,
            ProductCategory.NUTS_AND_SEEDS, ProductCategory.PASTA, ProductCategory.POTATO_RICE, ProductCategory.SWEET,
            ProductCategory.HERB));
    private ArrayList<ProductCategory> drink = new ArrayList<>(Arrays.asList(ProductCategory.COLD_DRINKS, ProductCategory.HOT_DRINKS));

    public ArrayList<ProductCategory> getFruit() {
        return fruit;
    }

    public ArrayList<ProductCategory> getGreens() {
        return greens;
    }

    public ArrayList<ProductCategory> getDairy() {
        return dairy;
    }

    public ArrayList<ProductCategory> getBread() {
        return bread;
    }

    public ArrayList<ProductCategory> getFish() {
        return fish;
    }

    public ArrayList<ProductCategory> getMeat() {
        return meat;
    }

    public ArrayList<ProductCategory> getDryFood() {
        return dryFood;
    }

    public ArrayList<ProductCategory> getDrink() {
        return drink;
    }
}
