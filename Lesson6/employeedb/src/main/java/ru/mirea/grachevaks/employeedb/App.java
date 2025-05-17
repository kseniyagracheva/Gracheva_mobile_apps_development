package ru.mirea.grachevaks.employeedb;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static App instance;
    private SuperheroDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, SuperheroDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }
    public static App getInstance() {
        return instance;
    }
    public SuperheroDatabase getDatabase() {
        return database;
    }
}