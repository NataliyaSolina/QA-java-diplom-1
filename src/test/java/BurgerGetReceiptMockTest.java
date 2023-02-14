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

@RunWith(MockitoJUnitRunner.class)
public class BurgerGetReceiptMockTest {
    @Spy
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
    public static final Float PRICE = 100F;

    @Before
    public void init() {
        Mockito.when(bun.getName()).thenReturn("Желтенькая булка");
        Mockito.when(bunOther.getName()).thenReturn("Черная булка");
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
    public void setBunGetReceiptValidDataRezultOk() {
        burger.bun = bun;

        Mockito.when(burger.getPrice()).thenReturn(PRICE);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), PRICE);

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }

    @Test
    public void addIngredientGetReceiptValidDataRezultOk() {
        burger.bun = bun;

        burger.ingredients = List.of(sauceK, fillS, sauceS, fillM);

        Mockito.when(burger.getPrice()).thenReturn(PRICE);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= sauce %s =%n= filling %s =%n", sauceK.getName(), fillS.getName(), sauceS.getName(), fillM.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), PRICE);

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(expectedReceipt));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }

    @Test
    public void getReceiptAfterMoveIngredientValidDataRezultOk() { //проверка того что после move работает getReceirt
        burger.bun = bun;

        burger.ingredients.add(sauceK);
        burger.ingredients.add(fillS);
        burger.ingredients.add(sauceS);
        burger.ingredients.add(fillM);

        Mockito.when(burger.getPrice()).thenReturn(PRICE);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= sauce %s =%n= filling %s =%n", sauceK.getName(), fillS.getName(), sauceS.getName(), fillM.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), PRICE);

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(expectedReceipt));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));

        burger.moveIngredient(2, 3);

        expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= filling %s =%n= sauce %s =%n", sauceK.getName(), fillS.getName(), fillM.getName(), sauceS.getName());

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(expectedReceipt));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }

    @Test
    public void getReceiptAfterRemoveIngredientValidDataRezultOk() { //проверка того что после remove работает getReceirt
        burger.bun = bun;

        burger.ingredients.add(sauceK);
        burger.ingredients.add(fillS);
        burger.ingredients.add(sauceS);
        burger.ingredients.add(fillM);

        Mockito.when(burger.getPrice()).thenReturn(PRICE);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= sauce %s =%n= filling %s =%n", sauceK.getName(), fillS.getName(), sauceS.getName(), fillM.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), PRICE);

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(expectedReceipt));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));

        burger.removeIngredient(2);

        expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= filling %s =", sauceK.getName(), fillS.getName(), fillM.getName());
        expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), PRICE);
        String removeIngredient = String.format("= sauce %s =", sauceS.getName());

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(expectedReceipt));
        MatcherAssert.assertThat(burger.getReceipt(), not(containsString(removeIngredient)));
    }

    @Test
    public void getReceiptAfterMoveBunValidDataRezultOk() {   //проверка того что после замены булки работает getReceirt
        burger.bun = bun;

        burger.setBuns(bunOther);

        String expectedReceiptBegin = String.format("(==== %s ====)%n", bunOther.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bunOther.getName(), burger.getPrice());

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }
}
