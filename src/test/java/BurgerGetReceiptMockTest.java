import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

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
    float price = 100F;

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
        burger.setBuns(bun);

        Mockito.when(burger.getPrice()).thenReturn(price);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), price);

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }

    @Test
    public void addIngredientGetReceiptValidDataRezultOk() {
        burger.setBuns(bun);

        burger.addIngredient(sauceK);
        burger.addIngredient(fillS);
        burger.addIngredient(sauceS);
        burger.addIngredient(fillM);

        Mockito.when(burger.getPrice()).thenReturn(price);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= sauce %s =%n= filling %s =%n", sauceK.getName(), fillS.getName(), sauceS.getName(), fillM.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), price);

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(expectedReceipt));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }

    @Test
    public void moveIngredientGetReceiptValidDataRezultOk() {
        burger.setBuns(bun);

        burger.addIngredient(sauceK);
        burger.addIngredient(fillS);
        burger.addIngredient(sauceS);
        burger.addIngredient(fillM);

        Mockito.when(burger.getPrice()).thenReturn(price);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= sauce %s =%n= filling %s =%n", sauceK.getName(), fillS.getName(), sauceS.getName(), fillM.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), price);

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
    public void removeIngredientGetReceiptValidDataRezultOk() {
        burger.setBuns(bun);

        burger.addIngredient(sauceK);
        burger.addIngredient(fillS);
        burger.addIngredient(sauceS);
        burger.addIngredient(fillM);

        Mockito.when(burger.getPrice()).thenReturn(price);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= sauce %s =%n= filling %s =%n", sauceK.getName(), fillS.getName(), sauceS.getName(), fillM.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), price);

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(expectedReceipt));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));

        burger.removeIngredient(2);

        expectedReceipt = String.format("= sauce %s =%n= filling %s =%n= filling %s =", sauceK.getName(), fillS.getName(), fillM.getName());
        expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), price);
        String removeIngredient = String.format("= sauce %s =", sauceS.getName());

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
        MatcherAssert.assertThat(burger.getReceipt(), containsString(expectedReceipt));
        MatcherAssert.assertThat(burger.getReceipt(), not(containsString(removeIngredient)));
    }

    @Test
    public void moveBunGetReceiptValidDataRezultOk() {
        burger.setBuns(bun);

        Mockito.when(burger.getPrice()).thenReturn(price);
        String expectedReceiptBegin = String.format("(==== %s ====)%n", bun.getName());
        String expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bun.getName(), price);

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));

        burger.setBuns(bunOther);

        MatcherAssert.assertThat(burger.getReceipt(), not(containsString(expectedReceiptBegin)));

        expectedReceiptBegin = String.format("(==== %s ====)%n", bunOther.getName());
        expectedReceiptEnd = String.format("(==== %s ====)%n%nPrice: %f%n", bunOther.getName(), burger.getPrice());

        MatcherAssert.assertThat(burger.getReceipt(), startsWith(expectedReceiptBegin));
        MatcherAssert.assertThat(burger.getReceipt(), endsWith(expectedReceiptEnd));
    }
}
