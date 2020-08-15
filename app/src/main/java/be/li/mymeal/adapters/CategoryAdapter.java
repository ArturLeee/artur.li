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
import be.li.mymeal.adapters.viewholders.CategoryItemViewHolder;
import be.li.mymeal.entities.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryItemViewHolder> {
    private List<Category> categories = new ArrayList<>(0);
    private final NavController navController;

    public void setCategories(List<Category> categories) {
        this.categories = categories;

        this.notifyDataSetChanged();
    }

    public CategoryAdapter(NavController navController) {
        this.navController = navController;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);

        return new CategoryItemViewHolder(view, navController);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        Category category = this.categories.get(position);

        holder.setCategory(category);
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }
}
