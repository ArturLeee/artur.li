package be.li.mymeal;

import org.junit.Test;

import be.li.mymeal.entities.Meal;

import static org.junit.Assert.*;

public class MealTest {

    @Test
    public void ingredients_are_parsed()
    {
        Meal meal = new Meal();
        meal.ingredient1 = "ingredient 1";
        meal.measure1 = "measure 1";

        Meal.Ingredient ingredient = meal.getIngredients().get(0);
    }

    @Test
    public void ingredients_are_assembled()
    {
        Meal meal = new Meal();
        meal.ingredient1 = "ingredient 1";
        meal.measure1 = "measure 1";
        meal.ingredient2 = "ingredient 2";
        meal.measure2 = "measure 3";
        meal.ingredient3 = "ingredient 3";
        meal.measure3 = "measure 3";

        assertEquals("There should be 3 ingredients", meal.getIngredients().size(), 3);
        assertEquals("Name should match", meal.getIngredients().get(0).name, meal.ingredient1);
        assertEquals("Name should match", meal.getIngredients().get(0).measurement, meal.measure1);
        assertEquals("Name should match", meal.getIngredients().get(1).name, meal.ingredient2);
        assertEquals("Name should match", meal.getIngredients().get(1).measurement, meal.measure2);
        assertEquals("Name should match", meal.getIngredients().get(2).name, meal.ingredient3);
        assertEquals("Name should match", meal.getIngredients().get(2).measurement, meal.measure3);
    }
}