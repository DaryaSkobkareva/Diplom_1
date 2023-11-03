package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static praktikum.IngredientType.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    private final Burger burger = new Burger();
    private final List<Ingredient> ingredients = List.of(
            new Ingredient(SAUCE, "hot sauce", 100),
            new Ingredient(SAUCE, "sour cream", 200),
            new Ingredient(SAUCE, "chili sauce", 300),
            new Ingredient(FILLING, "cutlet", 100),
            new Ingredient(FILLING, "dinosaur", 200),
            new Ingredient(FILLING, "sausage", 300)
    );

    @Test
    public void setBunTest() {
        burger.setBuns(new Bun("black bun", 100));
        assertEquals("Ожидалось другое наименование булки", "black bun", burger.bun.getName());
        assertEquals("Ожидалась другая цена булки", 100, burger.bun.getPrice(), 0.001);
    }
    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredients.get(1));
        assertEquals("Ожидался другой ингредиент", ingredients.get(1), burger.ingredients.get(0));
    }
    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredients.get(5));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(3));

        burger.removeIngredient(1);

        List<Ingredient> expectedIngredientsList = List.of(
                ingredients.get(5),
                ingredients.get(3));

        assertEquals("Ожидался другой список ингредиентов", expectedIngredientsList, burger.ingredients);
    }
    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(4));
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(5));

        burger.moveIngredient(2,0);

        List<Ingredient> expectedIngredientsList = List.of(
                ingredients.get(3),
                ingredients.get(1),
                ingredients.get(4),
                ingredients.get(5));

        assertEquals("Ожидался другой список ингредиентов", expectedIngredientsList, burger.ingredients);
    }
    @Test
    public void getPriceTest() {
        when(bun.getPrice()).thenReturn(89.99f);
        when(ingredient.getPrice()).thenReturn(111.5f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        assertEquals("Ожидалась другая цена бургера", 402.98, burger.getPrice(), 0.001);
    }
    @Test
    public void getReceiptTest() {
        burger.setBuns(new Bun("red bun", 300));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        burger.addIngredient(ingredients.get(4));

        String expectedReceipt =
                        "(==== red bun ====)\r\n"
                        + "= sauce sour cream =\r\n"
                        + "= sauce chili sauce =\r\n"
                        + "= filling dinosaur =\r\n"
                        + "(==== red bun ====)\r\n"
                        + "\r\nPrice: 1300,000000\r\n";

        assertEquals("Ожидался другой рецепт бургера", expectedReceipt, burger.getReceipt());
    }
}
