package ru.mirea.grachevaks.lesson6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.grachevaks.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences preferences;

    private static final String PREF_NAME = "user_settings";
    private static final String KEY_GROUP = "group_number";
    private static final String KEY_NUMBER = "list_number";
    private static final String KEY_FAVORITE = "favorite_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        binding.EditTextGroup.setText(preferences.getString(KEY_GROUP, ""));
        binding.EditTextNumList.setText(preferences.getString(KEY_NUMBER, ""));
        binding.EditTextFavMovie.setText(preferences.getString(KEY_FAVORITE, ""));

        binding.ButtonSend.setOnClickListener(v -> {
            String group = binding.EditTextGroup.getText().toString();
            String number = binding.EditTextNumList.getText().toString();
            String favorite = binding.EditTextFavMovie.getText().toString();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_GROUP, group);
            editor.putString(KEY_NUMBER, number);
            editor.putString(KEY_FAVORITE, favorite);
            editor.apply();

            Toast.makeText(this, "Данные сохранены!", Toast.LENGTH_SHORT).show();
        });
    }
}
