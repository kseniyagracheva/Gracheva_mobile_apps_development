package ru.mirea.grachevaks.internalfilestorage;


import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

import ru.mirea.grachevaks.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "memorable_event.txt";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.SaveDataButton.setOnClickListener(v -> {
            String eventText = binding.EditTextEvent.getText().toString().trim();
            if (!eventText.isEmpty()) {
                saveToFile(eventText);
            }
        });
    }

    private void saveToFile(String text) {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
