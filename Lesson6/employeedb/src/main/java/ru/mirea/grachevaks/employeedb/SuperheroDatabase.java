package ru.mirea.grachevaks.employeedb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Superhero.class}, version = 1)
public abstract class  SuperheroDatabase extends RoomDatabase {
    public abstract SuperheroDao superheroDao();
}