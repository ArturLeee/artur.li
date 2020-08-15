package be.li.mymeal.adapters.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import be.li.mymeal.databinding.IngredientListItemBinding;
import be.li.mymeal.entities.Ingredient;
import be.li.mymeal.ui.ingredients.IngredientsFragmentDirections;

public class IngredientItemViewHolder extends RecyclerView.ViewHolder {
    private final IngredientListItemBinding binding;
    private final NavController navController;

    public IngredientItemViewHolder(@NonNull View root, NavController navController) {
        super(root);

        binding = DataBindingUtil.bind(root);
        this.navController = navController;
    }

    public void setIngredient(Ingredient ingredient) {
        binding.ingredientName.setText(ingredient.name);
        binding.ingredientDescription.setText(ingredient.description);

        binding.getRoot().setOnClickListener(v -> {
            IngredientsFragmentDirections.CategoriesToMealsByCategoryFragment action = IngredientsFragmentDirections.categoriesToMealsByCategoryFragment(ingredient.name);

            navController.navigate(action);
        });
    }
}
