package be.li.mymeal.ui.category;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.net.UnknownHostException;

import be.li.mymeal.R;
import be.li.mymeal.adapters.CategoryAdapter;
import be.li.mymeal.databinding.FragmentCategoriesBinding;
import be.li.mymeal.services.MealService;

public class CategoriesFragment extends Fragment {
    private MealService mealService = new MealService();
    FragmentCategoriesBinding binding;
    CategoryAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        binding = DataBindingUtil.bind(root);

        binding.listCategories.setHasFixedSize(true);
        binding.listCategories.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new CategoryAdapter(NavHostFragment.findNavController(this));
        binding.listCategories.setAdapter(adapter);
        binding.swipeToRefresh.setOnRefreshListener(this::refresh);

        binding.swipeToRefresh.setRefreshing(true);
        this.refresh();

        return root;
    }

    private void refresh() {
        this.mealService.getCategories(categories -> {
            adapter.setCategories(categories);
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