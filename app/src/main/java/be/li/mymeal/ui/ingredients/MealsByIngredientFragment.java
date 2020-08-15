package be.li.mymeal.ui.ingredients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.net.UnknownHostException;

import be.li.mymeal.R;
import be.li.mymeal.adapters.MealsAdapter;
import be.li.mymeal.databinding.FragmentMealsByIngredientBinding;
import be.li.mymeal.services.MealService;

public class MealsByIngredientFragment extends Fragment {
    private MealService mealService = new MealService();
    private FragmentMealsByIngredientBinding binding;
    private MealsAdapter adapter;
    private String ingredient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_meals_by_ingredient, container, false);
        binding = DataBindingUtil.bind(root);

        binding.listMeals.setHasFixedSize(true);
        binding.listMeals.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new MealsAdapter();
        binding.listMeals.setAdapter(adapter);
        binding.swipeToRefresh.setOnRefreshListener(this::refresh);

        // Once the view is created, we'll retrieve the meals by ingredient.
        binding.swipeToRefresh.setRefreshing(true);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ingredient = MealsByIngredientFragmentArgs.fromBundle(getArguments()).getIngredient();
        this.setToolbarTitle(ingredient);

        this.refresh();
    }

    private void refresh() {
        mealService.getMealsByIngredient(this.ingredient, meals -> {
            adapter.setMeals(meals);
            binding.swipeToRefresh.setRefreshing(false);
        }, throwable -> {
            if (throwable instanceof UnknownHostException) {
                Snackbar.make(getView(), R.string.error_network, Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(getView(), throwable.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }

            binding.swipeToRefresh.setRefreshing(false);
        });
    }

    private void setToolbarTitle(String ingredient) {
        Toolbar toolbar = this.requireActivity().findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.title_meals_by_ingredient, ingredient));
        }
    }
}
