package be.li.mymeal.ui.ingredients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.net.UnknownHostException;

import be.li.mymeal.R;
import be.li.mymeal.adapters.IngredientsAdapter;
import be.li.mymeal.databinding.FragmentIngredientsBinding;
import be.li.mymeal.services.MealService;

public class IngredientsFragment extends Fragment {
    private MealService mealService = new MealService();
    private FragmentIngredientsBinding binding;
    private IngredientsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ingredients, container, false);
        binding = DataBindingUtil.bind(root);

        binding.listIngredients.setHasFixedSize(true);
        binding.listIngredients.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new IngredientsAdapter(NavHostFragment.findNavController(this));
        binding.listIngredients.setAdapter(adapter);
        binding.swipeToRefresh.setOnRefreshListener(this::refresh);

        binding.swipeToRefresh.setRefreshing(true);
        this.refresh();

        return root;
    }

    private void refresh() {
        this.mealService.getIngredients(ingredients -> {
            adapter.setIngredients(ingredients);

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
}
