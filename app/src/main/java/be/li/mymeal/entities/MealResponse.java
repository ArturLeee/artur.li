package be.li.mymeal.entities;

import java.util.List;

/**
 * Wraps the {@link Meal} class since the API wraps all responses in a top level "meals" array.
 *
 * This class allows {@link com.google.gson.Gson} to properly unwrap the response in the desired responses.
 */
public class MealResponse {
    public List<Meal> meals;
}
