package be.li.mymeal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.li.mymeal.R;
import be.li.mymeal.adapters.viewholders.MealItemViewHolder;
import be.li.mymeal.entities.Meal;

public class MealsAdapter extends RecyclerView.Adapter<MealItemViewHolder> {
    private List<Meal> meals = new ArrayList<>(0);

    public void setMeals(List<Meal> meals) {
        this.meals = meals;

        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.meal_list_item, parent, false);

        return new MealItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealItemViewHolder holder, int position) {
        Meal meal = this.meals.get(position);

        holder.setMeal(meal);
    }

    @Override
    public int getItemCount() {
        return this.meals.size();
    }
}
