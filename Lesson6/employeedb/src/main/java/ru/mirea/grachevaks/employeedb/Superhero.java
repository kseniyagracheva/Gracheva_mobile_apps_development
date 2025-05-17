package ru.mirea.grachevaks.employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Superhero {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String element;

    public Superhero(String name, String element) {
        this.name = name;
        this.element = element;
    }
}
