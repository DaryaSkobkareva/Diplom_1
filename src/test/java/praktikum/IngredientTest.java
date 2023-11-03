package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import static praktikum.IngredientType.*;

@RunWith(Parameterized.class)
public class IngredientTest {
    private Ingredient ingredient;

    @Parameterized.Parameter
    public IngredientType type;
    @Parameterized.Parameter(1)
    public String name;
    @Parameterized.Parameter(2)
    public float price;

    @Parameterized.Parameters(name = "{0} \"{1}\" стоит {2}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {SAUCE, "Соус традиционный гаактический", 100},
                {FILLING, "Мясо бессмертнных моллюсков Protostomia", 200.5f},
                {SAUCE, "Соус традиционный гаактический Galaxy 800", 338.99f},
                {FILLING, "Флуоресцентная булка \"Protostomia\" 800$ 70%", 0}
        };
    }
    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }
    @Test
    public void getTypeTest() {
        assertEquals("Ожидался другой тип ингредиента", type, ingredient.getType());
    }
    @Test
    public void getNameTest() {
        assertEquals("Ожидалось другое наименование ингредиента", name, ingredient.getName());
    }
    @Test
    public void getPriceTest() {
        assertEquals("Ожидалась другая цена ингредиента", price, ingredient.getPrice(), 0.001);
    }
}