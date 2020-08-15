package be.li.mymeal.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {
    @SerializedName("meals")
    public List<Ingredient> ingredients;
}
