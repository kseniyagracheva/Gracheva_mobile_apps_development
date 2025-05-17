package ru.mirea.grachevaks.securesharedpreferences;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ru.mirea.grachevaks.securesharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String PREFS_NAME = "secret_shared_prefs";
    private static final String KEY_POET_NAME = "poet_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadPoetName();

        binding.SendButton.setOnClickListener(v -> savePoetName());
    }

    private void savePoetName() {
        try {
            MasterKey mainKey = new MasterKey.Builder(this)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            EncryptedSharedPreferences encryptedPrefs = (EncryptedSharedPreferences) EncryptedSharedPreferences.create(
                    this,
                    PREFS_NAME,
                    mainKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            String poetName = binding.PoetNameText.getText().toString();

            encryptedPrefs.edit()
                    .putString(KEY_POET_NAME, poetName)
                    .apply();

            Toast.makeText(this, "Данные сохранены!", Toast.LENGTH_SHORT).show();

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadPoetName() {
        try {
            MasterKey mainKey = new MasterKey.Builder(this)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            EncryptedSharedPreferences encryptedPrefs = (EncryptedSharedPreferences) EncryptedSharedPreferences.create(
                    this,
                    PREFS_NAME,
                    mainKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            String poetName = encryptedPrefs.getString(KEY_POET_NAME, "Брюсов Валерий Яковлевич");
            binding.PoetNameText.setText(poetName);

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
