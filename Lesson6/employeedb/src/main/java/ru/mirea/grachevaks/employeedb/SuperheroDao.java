package ru.mirea.grachevaks.employeedb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SuperheroDao {
    @Insert
    void insert(Superhero superhero);
    @Query("SELECT * FROM superhero")
    List<Superhero> getAll();
    @Query("SELECT * FROM superhero WHERE id = :id")
    Superhero getById(long id);
    @Update
    void update(Superhero superhero);
    @Delete
    void delete(Superhero superhero);
}
