package ru.mirea.grachevaks.employeedb;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewHeroes = findViewById(R.id.listViewHeroes);

        SuperheroDatabase db = App.getInstance().getDatabase();

        // Добавление записей
        if (db.superheroDao().getAll().isEmpty()) {
            db.superheroDao().insert(new Superhero("Aquaman", "Water"));
            db.superheroDao().insert(new Superhero("Johnny Storm", "Fire"));
            db.superheroDao().insert(new Superhero("Air-Walker", "Air"));
            db.superheroDao().insert(new Superhero("Crystal Amakelin", "Electricity"));
        }

        List<Superhero> heroes = db.superheroDao().getAll();
        List<String> heroDescriptions = new ArrayList<>();

        for (Superhero h : heroes) {
            heroDescriptions.add(h.name + " — " + h.element);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                heroDescriptions
        );
        listViewHeroes.setAdapter(adapter);
    }
}