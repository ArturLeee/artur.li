package be.li.mymeal.ui.meals.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import be.li.mymeal.entities.Meal;
import be.li.mymeal.services.DatabaseService;

public class MealsViewModel extends AndroidViewModel {
    private MutableLiveData<List<Meal>> meals;

    public MealsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Meal>> getMeals() {
        return DatabaseService
                .getInstance(getApplication().getApplicationContext())
                .mealDao()
                .getAll();
    }
}
