package be.li.mymeal.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import be.li.mymeal.entities.Meal;

@Dao
public abstract class MealDao {
    @Query("SELECT * FROM meal")
    public abstract LiveData<List<Meal>> getAll();

    @Insert
    public abstract void insert(Meal... meals);

    @Delete
    public abstract void delete(Meal meal);

    @Query("SELECT * FROM meal WHERE id = :id")
    public abstract Meal findById(String id);

    @Query("SELECT * FROM meal WHERE id = :id")
    public abstract LiveData<Meal> trackById(String id);

    public boolean exists(@NonNull Meal meal) {
        return findById(meal.id) != null;
    }

    @Transaction
    public void insertIfNotExists(@NonNull Meal meal) {
        if (!exists(meal)) {
            insert(meal);
        }
    }
}
