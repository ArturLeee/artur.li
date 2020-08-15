package be.li.mymeal.ui.meals;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.Executors;

import be.li.mymeal.R;
import be.li.mymeal.databinding.ActivityMealBinding;
import be.li.mymeal.entities.Meal;
import be.li.mymeal.services.DatabaseService;
import be.li.mymeal.services.MealService;
import io.noties.markwon.Markwon;

public class MealActivity extends AppCompatActivity {
    private static final String MEAL_ID = "be.li.mymeal.ui.meals.MealActivity.MEAL_ID";
    private final MealService mealService = new MealService();
    private DatabaseService databaseService;
    private ActivityMealBinding binding;
    private String mealId;
    private Meal currentMeal;
    private boolean isMenuOpen = false;
    private boolean mealExists = false;

    /**
     * Generate an {@link Intent} that navigates to the {@link MealActivity} for the given meal.
     *
     * @param source The {@link Context} that starts the navigation.
     * @param meal   The {@link Meal} for which to show the {@link MealActivity}.
     */
    public static Intent forMeal(Context source, Meal meal) {
        Intent intent = new Intent(source, MealActivity.class);
        intent.putExtra(MEAL_ID, meal.id);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseService = DatabaseService.getInstance(getApplicationContext());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_meal);

        Intent intent = getIntent();

        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            mealId = Objects.requireNonNull(intent.getData()).getQueryParameter("i");
        } else if (!"".equals(intent.getStringExtra(MEAL_ID))) {
            mealId = intent.getStringExtra(MEAL_ID);
        }

        binding.swipeToRefresh.setOnRefreshListener(this::refresh);
        binding.openMenu.setOnClickListener(this::toggleMenu);
        binding.addOrRemoveFromSaved.setOnClickListener(this::addOrRemoveMeal);
        binding.openYoutube.setOnClickListener(this::openYoutube);

        binding.swipeToRefresh.setRefreshing(true);
        this.refresh();

        databaseService.mealDao().trackById(mealId).observe(this, meal -> {
            this.mealExists = (meal != null);
        });
    }

    private void refresh() {
        mealService.getMealById(mealId, meal -> {
            currentMeal = meal;
            binding.mealName.setText(meal.name);
            Markwon.create(this).setMarkdown(binding.mealInstructions, meal.instructions);
            if (meal.image.isEmpty()) {
                binding.mealPreview.setVisibility(View.GONE);
            } else {
                Picasso.get().load(meal.image).into(binding.mealPreview);
            }

            binding.swipeToRefresh.setRefreshing(false);
        }, throwable -> {
            if (throwable instanceof UnknownHostException) {
                Snackbar.make(binding.getRoot(), R.string.error_network, Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(binding.getRoot(), throwable.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }

            binding.swipeToRefresh.setRefreshing(false);
        });
    }

    private void toggleMenu(View v) {
        this.isMenuOpen = !this.isMenuOpen;

        if (this.isMenuOpen) {
            binding.openYoutube.animate().translationY(-getResources().getDimension(R.dimen.fab_menu_first));
            binding.addOrRemoveFromSaved.animate().translationY(-getResources().getDimension(R.dimen.fab_menu_second));
        } else {
            binding.openYoutube.animate().translationY(0);
            binding.addOrRemoveFromSaved.animate().translationY(0);
        }
    }

    private void openYoutube(View v) {
        if (currentMeal != null) {
            String id = currentMeal.youtube.substring("http://www.youtube.com/watch?v=".length() + 1);

            try {
                // First we try to start the Youtube app, if that fails (startActivity throws), we start a web intent opening the browser.
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
                startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentMeal.youtube));
                startActivity(webIntent);
            }
        }
    }

    private void addOrRemoveMeal(View v) {
        AlertDialog.Builder builder;
        if (mealExists) {
            builder = removeMeal();
        } else {
            builder = addMeal();
        }

        builder
            .setNegativeButton(R.string.confirm_no, (dialog, which) -> dialog.cancel())
            .create()
            .show();
    }

    private AlertDialog.Builder addMeal() {
        return new AlertDialog.Builder(MealActivity.this)
                .setMessage(R.string.confirm_add_meal)
                .setPositiveButton(R.string.confirm_yes, (dialog, which) -> {
                    // We cannot run DB operations on the main thread.
                    Executors.newSingleThreadExecutor().submit(() -> {
                        databaseService.mealDao().insertIfNotExists(currentMeal);

                        Snackbar
                            .make(binding.getRoot(), R.string.meal_saved, Snackbar.LENGTH_LONG)
                            .show();
                    });
                });
    }

    private AlertDialog.Builder removeMeal() {
        return new AlertDialog.Builder(MealActivity.this)
                .setMessage(R.string.confirm_remove_meal)
                .setPositiveButton(R.string.confirm_yes, (dialog, which) -> {
                    // We cannot run DB operations on the main thread.
                    Executors.newSingleThreadExecutor().submit(() -> {
                        databaseService.mealDao().delete(currentMeal);
                    });
                });
    }
}