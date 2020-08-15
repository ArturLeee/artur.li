package be.li.mymeal.adapters.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import be.li.mymeal.R;
import be.li.mymeal.databinding.CategoryListItemBinding;
import be.li.mymeal.entities.Category;
import be.li.mymeal.ui.category.CategoriesFragmentDirections;

public class CategoryItemViewHolder extends RecyclerView.ViewHolder {
    private CategoryListItemBinding binding;
    private final NavController navController;

    public CategoryItemViewHolder(@NonNull View root, @NonNull NavController navController) {
        super(root);

        binding = DataBindingUtil.bind(root);
        this.navController = navController;
    }

    public void setCategory(Category category) {
        binding.categoryName.setText(category.name);
        binding.categoryDescription.setText(category.description);
        Picasso
            .get()
            .load(category.thumbnail)
            .resizeDimen(R.dimen.list_item, R.dimen.list_item)
            .into(binding.categoryThumbnail);

        binding.getRoot().setOnClickListener(v -> {
            CategoriesFragmentDirections.CategoriesToMealsByCategoryFragment action = CategoriesFragmentDirections.categoriesToMealsByCategoryFragment(category.name);

            navController.navigate(action);
        });
    }
}
