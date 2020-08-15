package be.li.mymeal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.li.mymeal.R;
import be.li.mymeal.adapters.viewholders.IngredientItemViewHolder;
import be.li.mymeal.entities.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientItemViewHolder> {
    private List<Ingredient> ingredients = new ArrayList<>(0);
    private final NavController navController;

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;

        this.notifyDataSetChanged();
    }

    public IngredientsAdapter(NavController navController) {
        this.navController = navController;
    }

    @NonNull
    @Override
    public IngredientItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.ingredient_list_item, parent, false);

        return new IngredientItemViewHolder(view, this.navController);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientItemViewHolder holder, int position) {
        Ingredient ingredient = this.ingredients.get(position);

        holder.setIngredient(ingredient);
    }

    @Override
    public int getItemCount() {
        return this.ingredients.size();
    }
}
