package be.li.mymeal.ui.meals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import be.li.mymeal.R;
import be.li.mymeal.adapters.MealsAdapter;
import be.li.mymeal.databinding.FragmentMyMealsBinding;
import be.li.mymeal.ui.meals.viewmodels.MealsViewModel;

public class MyMealsFragment extends Fragment {
    private FragmentMyMealsBinding binding;
    private MealsAdapter adapter;
    private MealsViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_meals, container, false);
        binding = DataBindingUtil.bind(root);

        binding.listMeals.setHasFixedSize(true);
        binding.listMeals.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MealsAdapter();
        binding.listMeals.setAdapter(adapter);

        model = new ViewModelProvider(this).get(MealsViewModel.class);
        model.getMeals().observe(getViewLifecycleOwner(), meals -> {
            adapter.setMeals(meals);
            if (meals.size() == 0) {
                Snackbar
                        .make(getView(), R.string.error_no_meals, Snackbar.LENGTH_LONG)
                        .show();
            }
        });

        return root;
    }
}
