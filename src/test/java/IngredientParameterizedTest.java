import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {
    private final IngredientType type;
    private final String name;
    private final float price;
    public Float DELTA = 0.0001f;

    public IngredientParameterizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{0}, {1}, {2}")
    public static Object[][] dataForTest() {
        return new Object[][]{
                {IngredientType.SAUCE, "Сырный соус", 29.99F},
                {IngredientType.SAUCE, "Кетчуп", 19.99F},
                {IngredientType.SAUCE, "Сметанный соус", 29.00F},
                {IngredientType.FILLING, "Куриная сосиска", 51.99F},
                {IngredientType.FILLING, "Говяжья котлета", 81.00F},
                {IngredientType.FILLING, "Бекон", 41.05F}
        };
    }

    @Test
    public void paramTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(type, ingredient.getType());
        Assert.assertEquals(name, ingredient.getName());
        Assert.assertEquals(price, ingredient.getPrice(), DELTA);
    }
}
