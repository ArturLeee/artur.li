package be.li.mymeal.adapters.viewholders;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import be.li.mymeal.databinding.MealListItemBinding;
import be.li.mymeal.entities.Meal;
import be.li.mymeal.ui.meals.MealActivity;

public class MealItemViewHolder extends RecyclerView.ViewHolder {
    private MealListItemBinding binding;

    public MealItemViewHolder(@NonNull View root) {
        super(root);

        binding = DataBindingUtil.bind(root);
    }

    public void setMeal(Meal meal) {
        binding.mealName.setText(meal.name);
        binding.mealCategories.setText(meal.category);

        Picasso
                .get()
                .load(meal.getThumbnail())
                .into(binding.mealThumbnail);

        binding.getRoot().setOnClickListener(v -> {
            Intent intent = MealActivity.forMeal(v.getContext(), meal);

            v.getContext().startActivity(intent);
        });
    }
}
