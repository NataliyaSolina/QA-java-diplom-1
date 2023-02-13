import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunParameterizedTest {

    private final String name;
    private final float price;
    public Float DELTA = 0.0001f;

    public BunParameterizedTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{0}, {1}")
    public static Object[][] dataForTest() {
        return new Object[][]{
                {"Кукурузная булочка", 39.99F},
                {"Булочка с кунжутом", 49.99F},
                {"Ржаная булочка", 29.00F},
                {"Чернильная булочка", 61.01F}
        };
    }

    @Test
    public void paramTest() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(name, bun.getName());
        Assert.assertEquals(price, bun.getPrice(), DELTA);
    }
}
