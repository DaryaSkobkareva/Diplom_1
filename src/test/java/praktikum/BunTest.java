package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {
    private Bun bun;

    @Parameterized.Parameter
    public String name;
    @Parameterized.Parameter(1)
    public float price;

    @Parameterized.Parameters(name = "Булка \"{0}\" стоит {1}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Флуоресцентная булка", 10},
                {"Флуоресцентная булка Magic Light", 200.5f},
                {"Флуоресцентная булка Magic Light 800", 1338.99f},
                {"Флуоресцентная булка \"Magic&Light\" 800 70%", 0}
        };
    }
    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }
    @Test
    public void getNameTest() {
        assertEquals("Ожидалось другое наименование булки", name, bun.getName());
    }
    @Test
    public void getPriceTest() {
        assertEquals("Ожидалась другая цена булки", price, bun.getPrice(), 0.001);
    }
}