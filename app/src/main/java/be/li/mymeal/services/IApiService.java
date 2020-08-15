package be.li.mymeal.services;

import be.li.mymeal.entities.CategoryResponse;
import be.li.mymeal.entities.IngredientResponse;
import be.li.mymeal.entities.MealResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The IApiService provides an interface for interacting with https://themealdb.com/ API.
 * The underlying code is generated by Retrofit and consumed by {@link MealService}.
 */
public interface IApiService {
    @GET("/api/json/v1/1/random.php")
    Call<MealResponse> getRandomMeal();

    @GET("/api/json/v1/1/categories.php")
    Call<CategoryResponse> getCategories();

    @GET("/api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);

    @GET("/api/json/v1/1/list.php?i=list")
    Call<IngredientResponse> getIngredients();

    @GET("/api/json/v1/1/filter.php")
    Call<MealResponse> getMealsByIngredient(@Query("i") String ingredient);

    @GET("/api/json/v1/1/lookup.php")
    Call<MealResponse> getMealById(@Query("i") String id);
}
