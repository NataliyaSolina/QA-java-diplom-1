import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.*;

import java.util.List;

import static praktikum.IngredientType.*;

public class BurgerTest {
    Burger burger = new Burger();
    Bun bun;
    Bun bunOther;
    Ingredient sauceK;
    Ingredient sauceS;
    Ingredient fillS;
    Ingredient fillM;

    @Before
    public void init() {
        bun = new Bun("Желтенькая булка", 36.66F);
        bunOther = new Bun("Черная булка", 59.66F);
        sauceK = new Ingredient(SAUCE, "Кепчук", 19.99F);
        sauceS = new Ingredient(SAUCE, "Сырный соус", 23.99F);
        fillS = new Ingredient(FILLING, "Сосиська", 56.99F);
        fillM = new Ingredient(FILLING, "Мяско", 76.95F);
    }

    @Test
    public void setBunValidDataRezultOk() {
        burger.setBuns(bun);

        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientValidDataRezultOk() {
        burger.setBuns(bun);

        burger.addIngredient(sauceK);
        burger.addIngredient(fillS);
        burger.addIngredient(sauceS);
        burger.addIngredient(fillM);

        List<Ingredient> expectedIngredients = List.of(sauceK, fillS, sauceS, fillM);

        Assert.assertEquals(expectedIngredients, burger.ingredients);
    }

    @Test
    public void moveIngredientGetPriceValidDataRezultOk() {
        burger.setBuns(bun);

        burger.addIngredient(sauceK);
        burger.addIngredient(fillS);
        burger.addIngredient(sauceS);
        burger.addIngredient(fillM);

        List<Ingredient> expectedIngredients = List.of(sauceK, fillS, sauceS, fillM);

        Assert.assertEquals(expectedIngredients, burger.ingredients);

        burger.moveIngredient(2, 3);

        List<Ingredient> expectedIngredientsNew = List.of(sauceK, fillS, fillM, sauceS);

        Assert.assertEquals(expectedIngredientsNew, burger.ingredients);
    }

    @Test
    public void removeIngredientGetPriceValidDataRezultOk() {
        burger.setBuns(bun);

        burger.addIngredient(sauceK);
        burger.addIngredient(fillS);
        burger.addIngredient(sauceS);
        burger.addIngredient(fillM);

        List<Ingredient> expectedIngredients = List.of(sauceK, fillS, sauceS, fillM);

        Assert.assertEquals(expectedIngredients, burger.ingredients);

        burger.removeIngredient(2);

        List<Ingredient> expectedIngredientsNew = List.of(sauceK, fillS, fillM);

        Assert.assertEquals(expectedIngredientsNew, burger.ingredients);
    }

    @Test
    public void moveBunGetPriceValidDataRezultOk() {
        burger.setBuns(bun);

        Assert.assertEquals(bun, burger.bun);

        burger.setBuns(bunOther);

        Assert.assertEquals(bunOther, burger.bun);
    }
}
