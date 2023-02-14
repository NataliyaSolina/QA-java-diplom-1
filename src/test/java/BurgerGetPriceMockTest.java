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
        Mockito.when(sauceK.getPrice()).thenReturn(19.99F);
        Mockito.when(sauceS.getPrice()).thenReturn(23.99F);
        Mockito.when(fillS.getPrice()).thenReturn(56.99F);
        Mockito.when(fillM.getPrice()).thenReturn(76.95F);
    }

    @Test
    public void getPriceOnlyBunRezultOk() {
        burger.bun = bun;

        float expectedPrice = bun.getPrice() * 2;

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceWithOneIngredientRezultOk() {
        burger.bun = bun;

        burger.ingredients = List.of(fillS);

        float expectedPrice = bun.getPrice() * 2 + fillS.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceWithIngredientsRezultOk() {
        burger.bun = bun;

        burger.ingredients = List.of(sauceK, fillS, sauceS, fillM);

        float expectedPrice = bun.getPrice() * 2 + sauceK.getPrice() + fillS.getPrice() + sauceS.getPrice() + fillM.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test(expected = NullPointerException.class)
    public void getPriceWithoutBunRezultException() {      //при отсутствии булки исключение
        burger.ingredients = List.of(fillM);

        float expectedPrice = fillS.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }
}
