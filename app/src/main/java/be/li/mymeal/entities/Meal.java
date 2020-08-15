package be.li.mymeal.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single meal.
 * <p>
 * This can come from the API or local database storage.
 */
@Entity
public class Meal {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @SerializedName("idMeal")
    public String id;

    @ColumnInfo(name = "name")
    @SerializedName("strMeal")
    public String name;

    @Nullable
    @ColumnInfo(name = "drink")
    @SerializedName("strDrinkAlternate")
    public String drinkAlternate;

    @ColumnInfo(name = "category")
    @SerializedName("strCategory")
    public String category;

    @ColumnInfo(name = "area")
    @SerializedName("strArea")
    public String area;

    @ColumnInfo(name = "instructions")
    @SerializedName("strInstructions")
    public String instructions;

    @ColumnInfo(name = "thumbnail")
    @SerializedName("strMealThumb")
    public String image;

    @ColumnInfo(name = "youtube")
    @SerializedName("strYoutube")
    public String youtube;

    public String getThumbnail() {
        return this.image + "/preview";
    }

    public List<Meal.Ingredient> getIngredients() {
        ArrayList<Meal.Ingredient> ingredients = new ArrayList<Ingredient>();
        if (!this.ingredient1.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient1, this.measure1));
        if (!this.ingredient2.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient2, this.measure2));
        if (!this.ingredient3.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient3, this.measure3));
        if (!this.ingredient4.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient4, this.measure4));
        if (!this.ingredient5.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient5, this.measure5));
        if (!this.ingredient6.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient6, this.measure6));
        if (!this.ingredient7.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient7, this.measure7));
        if (!this.ingredient8.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient8, this.measure8));
        if (!this.ingredient9.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient9, this.measure9));
        if (!this.ingredient10.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient10, this.measure10));
        if (!this.ingredient11.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient11, this.measure11));
        if (!this.ingredient12.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient12, this.measure12));
        if (!this.ingredient13.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient13, this.measure13));
        if (!this.ingredient14.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient14, this.measure14));
        if (!this.ingredient15.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient15, this.measure15));
        if (!this.ingredient16.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient16, this.measure16));
        if (!this.ingredient17.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient17, this.measure17));
        if (!this.ingredient18.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient18, this.measure18));
        if (!this.ingredient19.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient19, this.measure19));
        if (!this.ingredient20.isEmpty())
            ingredients.add(new Meal.Ingredient(this.ingredient20, this.measure20));

        return ingredients;
    }

    @SerializedName("strIngredient1")
    public String ingredient1 = "";

    @SerializedName("strIngredient2")
    public String ingredient2 = "";

    @SerializedName("strIngredient3")
    public String ingredient3 = "";

    @SerializedName("strIngredient4")
    public String ingredient4 = "";

    @SerializedName("strIngredient5")
    public String ingredient5 = "";

    @SerializedName("strIngredient6")
    public String ingredient6 = "";

    @SerializedName("strIngredient7")
    public String ingredient7 = "";

    @SerializedName("strIngredient8")
    public String ingredient8 = "";

    @SerializedName("strIngredient9")
    public String ingredient9 = "";

    @SerializedName("strIngredient10")
    public String ingredient10 = "";

    @SerializedName("strIngredient11")
    public String ingredient11 = "";

    @SerializedName("strIngredient12")
    public String ingredient12 = "";

    @SerializedName("strIngredient13")
    public String ingredient13 = "";

    @SerializedName("strIngredient14")
    public String ingredient14 = "";

    @SerializedName("strIngredient15")
    public String ingredient15 = "";

    @SerializedName("strIngredient16")
    public String ingredient16 = "";

    @SerializedName("strIngredient17")
    public String ingredient17 = "";

    @SerializedName("strIngredient18")
    public String ingredient18 = "";

    @SerializedName("strIngredient19")
    public String ingredient19 = "";

    @SerializedName("strIngredient20")
    public String ingredient20 = "";

    @SerializedName("strMeasure1")
    public String measure1 = "";

    @SerializedName("strMeasure2")
    public String measure2 = "";

    @SerializedName("strMeasure3")
    public String measure3 = "";

    @SerializedName("strMeasure4")
    public String measure4 = "";

    @SerializedName("strMeasure5")
    public String measure5 = "";

    @SerializedName("strMeasure6")
    public String measure6 = "";

    @SerializedName("strMeasure7")
    public String measure7 = "";

    @SerializedName("strMeasure8")
    public String measure8 = "";

    @SerializedName("strMeasure9")
    public String measure9 = "";

    @SerializedName("strMeasure10")
    public String measure10 = "";

    @SerializedName("strMeasure11")
    public String measure11 = "";

    @SerializedName("strMeasure12")
    public String measure12 = "";

    @SerializedName("strMeasure13")
    public String measure13 = "";

    @SerializedName("strMeasure14")
    public String measure14 = "";

    @SerializedName("strMeasure15")
    public String measure15 = "";

    @SerializedName("strMeasure16")
    public String measure16 = "";

    @SerializedName("strMeasure17")
    public String measure17 = "";

    @SerializedName("strMeasure18")
    public String measure18 = "";

    @SerializedName("strMeasure19")
    public String measure19 = "";

    @SerializedName("strMeasure20")
    public String measure20 = "";

    public class Ingredient {
        public String name;
        public String measurement;

        public Ingredient(String name, String measurement) {
            this.name = name;
            this.measurement = measurement;
        }
    }
}
