import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerGetPriceMockTest {
    Burger burger = new Burger();
    @Mock
    Bun bun;
    @Mock
    Bun bunOther;
    @Mock
    Ingredient sauceK;
    @Mock
    Ingredient sauceS;
    @Mock
    Ingredient fillS;
    @Mock
    Ingredient fillM;
    public static final Float DELTA = 0.0001f;

    @Before
    public void init() {
        Mockito.when(bun.getPrice()).thenReturn(36.66F);
        Mockito.when(bunOther.getPrice()).thenReturn(59.66F);
        Mockito.when(sauceK.getPrice()).thenReturn(19.99F);
        Mockito.when(sauceS.getPrice()).thenReturn(23.99F);
        Mockito.when(fillS.getPrice()).thenReturn(56.99F);
        Mockito.when(fillM.getPrice()).thenReturn(76.95F);
    }

    @Test
    public void getPriceOnlyBunValidDataRezultOk() {
        burger.bun = bun;

        float expectedPrice = bun.getPrice() * 2;

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceWithIngredientValidDataRezultOk() {
        burger.bun = bun;

        burger.ingredients = List.of(sauceK, fillS, sauceS, fillM);

        float expectedPrice = bun.getPrice() * 2 + sauceK.getPrice() + fillS.getPrice() + sauceS.getPrice() + fillM.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceAfterMoveIngredientValidDataRezultOk() { //проверка того что после move работает getPrice
        burger.bun = bun;

        burger.ingredients.add(sauceK);
        burger.ingredients.add(fillS);
        burger.ingredients.add(sauceS);
        burger.ingredients.add(fillM);

        float expectedPrice = bun.getPrice() * 2 + sauceK.getPrice() + fillS.getPrice() + sauceS.getPrice() + fillM.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);

        burger.moveIngredient(2, 3);

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceAfterRemoveIngredientValidDataRezultOk() { //проверка того что после remove работает getPrice
        burger.bun = bun;

        burger.ingredients.add(sauceK);
        burger.ingredients.add(fillS);
        burger.ingredients.add(sauceS);
        burger.ingredients.add(fillM);

        float expectedPrice = bun.getPrice() * 2 + sauceK.getPrice() + fillS.getPrice() + sauceS.getPrice() + fillM.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
        System.out.println(burger.ingredients.get(2));
        burger.removeIngredient(2);

        expectedPrice -= sauceS.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceAfterMoveBunValidDataRezultOk() { //проверка того что после замены булки работает getPrice
        burger.bun = bun;

        burger.setBuns(bunOther);

        float expectedPrice = bunOther.getPrice() * 2;

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }
}
