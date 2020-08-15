package be.li.mymeal.ui.random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.net.UnknownHostException;

import be.li.mymeal.R;
import be.li.mymeal.databinding.FragmentRandomMealBinding;
import be.li.mymeal.entities.Meal;
import be.li.mymeal.services.MealService;
import be.li.mymeal.ui.meals.MealActivity;

public class RandomMealFragment extends Fragment {
    private MealService mealService = new MealService();
    private FragmentRandomMealBinding binding;
    private Meal currentMeal;
    private boolean isMenuOpen = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_random_meal, container, false);
        this.binding = DataBindingUtil.bind(root);
        binding.swipeToRefresh.setOnRefreshListener(this::onRefresh);
        binding.openMeal.setOnClickListener(v -> {
            if (currentMeal != null) {
                getContext().startActivity(MealActivity.forMeal(this.getContext(), currentMeal));
            }
        });

        // Trigger initial page load:
        binding.swipeToRefresh.setRefreshing(true);
        this.onRefresh();

        return root;
    }

    private void onRefresh() {
        mealService.getRandomMeal(this::onMealsReceived, this::onMealsFailure);
    }

    private void onMealsReceived(Meal meal) {
        this.currentMeal = meal;
        this.binding.mealName.setText(meal.name);
        Picasso.get()
                .load(meal.getThumbnail())
                .fit()
                .into(this.binding.mealPreview);

        this.binding.swipeToRefresh.setRefreshing(false);
    }

    private void onMealsFailure(Throwable throwable) {
        if (throwable instanceof UnknownHostException) {
            Snackbar.make(getView(), R.string.error_network, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(getView(), throwable.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
        }

        this.binding.swipeToRefresh.setRefreshing(false);
    }
}