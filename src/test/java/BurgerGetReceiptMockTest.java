import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BurgerGetReceiptMockTest {
    @Spy
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
    public static final Float PRICE = 100F;

    @Before
    public void init() {
        Mockito.when(bun.getName()).thenReturn("Желтенькая булка");
        Mockito.when(sauceK.getName()).thenReturn("Кепчук");
        Mockito.when(sauceK.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauceS.getName()).thenReturn("Сырный соус");
        Mockito.when(sauceS.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(fillS.getName()).thenReturn("Сосиська");
        Mockito.when(fillS.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(fillM.getName()).thenReturn("Мяско");
        Mockito.when(fillM.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void getReceiptWithOnlyBunRezultOk() {
        burger.bun = bun;

        Mockito.when(burger.getPrice()).thenReturn(PRICE);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), PRICE);

        assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }

    @Test
    public void getReceiptWithOneIngredientRezultOk() {
        burger.bun = bun;

        burger.ingredients = List.of(fillM);

        Mockito.when(burger.getPrice()).thenReturn(PRICE);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceipt = String.format("= filling %s =%n", fillM.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), PRICE);

        assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        assertThat(burger.getReceipt(), containsString(expectedReceipt));
        assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }

    @Test
    public void getReceiptWithIngredientsRezultOk() {
        burger.bun = bun;

        burger.ingredients = List.of(sauceK, fillS, sauceS, fillM);

        Mockito.when(burger.getPrice()).thenReturn(PRICE);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= sauce %s =%n= filling %s =%n", sauceK.getName(), fillS.getName(), sauceS.getName(), fillM.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), PRICE);

        assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        assertThat(burger.getReceipt(), containsString(expectedReceipt));
        assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }

    @Test(expected = NullPointerException.class)
    public void getReceiptWithoutBunRezultException() {            //при отсутствии булки исключение
        burger.ingredients = List.of(fillM);

        String expectedReceipt = String.format("= filling %s =%n", fillM.getName());
        String expectedReceiptEnd = String.format("%nPrice: %f%n", PRICE);

        Mockito.when(burger.getPrice()).thenReturn(PRICE);

        MatcherAssert.assertThat(burger.getReceipt(), containsString(expectedReceipt));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }
}
