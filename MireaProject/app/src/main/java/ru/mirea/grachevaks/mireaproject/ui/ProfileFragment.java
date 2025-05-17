package ru.mirea.grachevaks.mireaproject.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.mirea.grachevaks.mireaproject.R;

public class ProfileFragment extends Fragment {

    private EditText editName, editAge, editPie;
    private Button buttonSave;

    private static final String PREFS_NAME = "profile_prefs";
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editName = view.findViewById(R.id.editName);
        editAge = view.findViewById(R.id.editAge);
        editPie = view.findViewById(R.id.editPie);
        buttonSave = view.findViewById(R.id.buttonSaveProfile);

        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        loadProfile();

        buttonSave.setOnClickListener(v -> saveProfile());

        return view;
    }

    private void saveProfile() {
        String name = editName.getText().toString().trim();
        String ageStr = editAge.getText().toString().trim();
        String pieStr = editPie.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            editName.setError("Введите имя");
            return;
        }

        if (TextUtils.isEmpty(ageStr)) {
            editAge.setError("Введите возраст");
            return;
        }

        if (TextUtils.isEmpty(pieStr)) {
            editPie.setError("Введите название пирога");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            editAge.setError("Возраст должен быть числом");
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putInt("age", age);
        editor.putString("pie", pieStr);
        editor.apply();

        Toast.makeText(getContext(), "Данные сохранены", Toast.LENGTH_SHORT).show();
    }

    private void loadProfile() {
        String name = sharedPreferences.getString("name", "");
        int age = sharedPreferences.getInt("age", 0);
        String pie = sharedPreferences.getString("pie", "");

        if (!name.isEmpty()) {
            editName.setText(name);
        }
        if (age != 0) {
            editAge.setText(String.valueOf(age));
        }
        if (!pie.isEmpty()) {
            editPie.setText(pie);
        }
    }
}
