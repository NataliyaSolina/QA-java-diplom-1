import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

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
    public Float DELTA = 0.0001f;

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
    public void setBunGetPriceValidDataRezultOk() {
        burger.setBuns(bun);

        float expectedPrice = bun.getPrice() * 2;

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void addIngredientGetPriceValidDataRezultOk() {
        burger.setBuns(bun);

        burger.addIngredient(sauceK);
        burger.addIngredient(fillS);
        burger.addIngredient(sauceS);
        burger.addIngredient(fillM);

        float expectedPrice = bun.getPrice() * 2 + sauceK.getPrice() + fillS.getPrice() + sauceS.getPrice() + fillM.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void moveIngredientGetPriceValidDataRezultOk() {
        burger.setBuns(bun);

        burger.addIngredient(sauceK);
        burger.addIngredient(fillS);
        burger.addIngredient(sauceS);
        burger.addIngredient(fillM);

        float expectedPrice = bun.getPrice() * 2 + sauceK.getPrice() + fillS.getPrice() + sauceS.getPrice() + fillM.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);

        burger.moveIngredient(2, 3);

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void removeIngredientGetPriceValidDataRezultOk() {
        burger.setBuns(bun);

        burger.addIngredient(sauceK);
        burger.addIngredient(fillS);
        burger.addIngredient(sauceS);
        burger.addIngredient(fillM);

        float expectedPrice = bun.getPrice() * 2 + sauceK.getPrice() + fillS.getPrice() + sauceS.getPrice() + fillM.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);

        burger.removeIngredient(2);

        expectedPrice -= sauceS.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void moveBunGetPriceValidDataRezultOk() {
        burger.setBuns(bun);

        burger.setBuns(bunOther);

        float expectedPrice = bunOther.getPrice() * 2;

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }
}
