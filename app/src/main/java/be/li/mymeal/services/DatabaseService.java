package be.li.mymeal.services;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import be.li.mymeal.data.MealDao;
import be.li.mymeal.entities.Meal;

@Database(entities = {Meal.class}, version = 1, exportSchema = false)
public abstract class DatabaseService extends RoomDatabase {
    public abstract MealDao mealDao();

    private static DatabaseService instance = null;

    public static DatabaseService getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseService.class, "my_meal").build();
        }

        return instance;
    }
}
