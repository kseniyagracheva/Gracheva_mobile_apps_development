package ru.mirea.grachevaks.mireaproject.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ru.mirea.grachevaks.mireaproject.R;

public class FilesFragment extends Fragment {

    private static final String FILE_NAME = "notes.txt";

    private TextView textViewContent;
    private FloatingActionButton fabAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_files, container, false);

        textViewContent = view.findViewById(R.id.textViewContent);
        fabAdd = view.findViewById(R.id.fabAdd);

        loadFromFile();

        fabAdd.setOnClickListener(v -> showInputDialog());

        return view;
    }

    private void showInputDialog() {
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        input.setMinLines(3);
        input.setHint("Введите новую запись");

        new AlertDialog.Builder(getContext())
                .setTitle("Создать запись")
                .setView(input)
                .setPositiveButton("Сохранить", (dialog, which) -> {
                    String text = input.getText().toString().trim();
                    if (!text.isEmpty()) {
                        saveToFile(text);
                        loadFromFile();
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    private void saveToFile(String text) {
        try (FileOutputStream fos = requireContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try (FileInputStream fis = requireContext().openFileInput(FILE_NAME)) {
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            String content = new String(buffer);
            textViewContent.setText(content);
        } catch (IOException e) {
            textViewContent.setText("Файл пуст или не найден");
        }
    }
}
