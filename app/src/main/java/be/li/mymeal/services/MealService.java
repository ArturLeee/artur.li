package be.li.mymeal.services;

import com.google.gson.Gson;

import java.util.List;

import be.li.mymeal.contracts.ICallback;
import be.li.mymeal.entities.Category;
import be.li.mymeal.entities.Ingredient;
import be.li.mymeal.entities.Meal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The {@link MealService} coordinates interaction between the application and meals.
 */
public class MealService {
    private IApiService apiService;
    private Gson gson = new Gson();

    public MealService() {
        apiService = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IApiService.class);
    }

    /**
     * Fetch a random meal from the API.
     *
     * @param onSuccess Called when the API call is successful.
     * @param onFailure Called when something goes wrong (network level, parsing response, ...).
     */
    public void getRandomMeal(ICallback<Meal> onSuccess, ICallback<Throwable> onFailure) {
        executeCall(apiService.getRandomMeal(), mealResponse -> onSuccess.Run(mealResponse.meals.get(0)), onFailure);
    }

    /**
     * Fetch all categories from the API.
     *
     * @param onSuccess Called when the API call is successful.
     * @param onFailure Called when something goes wrong (network level, parsing response, ...).
     */
    public void getCategories(ICallback<List<Category>> onSuccess, ICallback<Throwable> onFailure) {
        executeCall(apiService.getCategories(), categoryResponse -> onSuccess.Run(categoryResponse.categories), onFailure);
    }

    public void getMealsByCategory(String category, ICallback<List<Meal>> onSuccess, ICallback<Throwable> onFailure) {
        executeCall(apiService.getMealsByCategory(category), mealResponse -> {
            if (mealResponse.meals == null) {
                onFailure.Run(new Exception("Could not find category '" + category + "'!"));
            } else {
                onSuccess.Run(mealResponse.meals);
            }
        }, onFailure);
    }

    public void getMealsByIngredient(String ingredient, ICallback<List<Meal>> onSuccess, ICallback<Throwable> onFailure) {
        executeCall(apiService.getMealsByIngredient(ingredient.replaceAll("\\s", "_").toLowerCase()), mealResponse -> {
            if (mealResponse.meals == null) {
                onFailure.Run(new Exception("Could not find ingredient '" + ingredient + "'!"));
            } else {
                onSuccess.Run(mealResponse.meals);
            }
        }, onFailure);
    }

    public void getIngredients(ICallback<List<Ingredient>> onSuccess, ICallback<Throwable> onFailure) {
        executeCall(apiService.getIngredients(), ingredientResponse -> {
            onSuccess.Run(ingredientResponse.ingredients);
        }, onFailure);
    }

    public void getMealById(String id, ICallback<Meal> onSuccess, ICallback<Throwable> onFailure) {
        executeCall(apiService.getMealById(id), mealResponse -> {
            if (mealResponse.meals.size() != 1) {
                onFailure.Run(new Exception("Could not find meal"));
            } else {
                onSuccess.Run(mealResponse.meals.get(0));
            }
        }, onFailure);
    }

    /**
     * Execute a given Call.
     *
     * @param call The call to execute.
     * @param onSuccess The handler called when the request is executed without errors.
     * @param onFailure The handler called when an error occurs.
     * @param <TResponse> The type of response to expect.
     */
    private <TResponse> void executeCall(Call<TResponse> call, ICallback<TResponse> onSuccess, ICallback<Throwable> onFailure) {
        call.enqueue(new Callback<TResponse>() {
            @Override
            public void onResponse(Call<TResponse> call, Response<TResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.Run(response.body());
                } else {
                    onFailure.Run(new Exception("Could not process response."));
                }
            }

            @Override
            public void onFailure(Call<TResponse> call, Throwable t) {
                onFailure.Run(t);
            }
        });
    }
}
