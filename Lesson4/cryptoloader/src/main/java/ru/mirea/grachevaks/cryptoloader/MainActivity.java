package ru.mirea.grachevaks.cryptoloader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import ru.mirea.grachevaks.cryptoloader.databinding.ActivityMainBinding;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public final String TAG = this.getClass().getSimpleName();
    private final int LoaderID = 1234;

    private ActivityMainBinding binding;
    private SecretKey secretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        secretKey = generateKey();

        binding.buttonEncrypt.setOnClickListener(this::onClickButton);
    }
    public void onClickButton(View view) {
        String phrase = binding.editTextPhrase.getText().toString();
        if (phrase.isEmpty()) {
            Toast.makeText(this, "Введите фразу", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            byte[] encrypted = encryptMsg(phrase, secretKey);

            Bundle bundle = new Bundle();
            bundle.putByteArray(MyLoader.ARG_WORD, encrypted);
            bundle.putByteArray(MyLoader.ARG_KEY, secretKey.getEncoded());

            LoaderManager.getInstance(this).restartLoader(LoaderID, bundle, this);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка шифрования", Toast.LENGTH_SHORT).show();
        }
    }
    public static SecretKey generateKey() {
        try {
            java.security.SecureRandom sr = java.security.SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            javax.crypto.KeyGenerator kg = javax.crypto.KeyGenerator.getInstance("AES");
            kg.init(256, sr);
            return new javax.crypto.spec.SecretKeySpec(kg.generateKey().getEncoded(), "AES");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static byte[] encryptMsg(String message, SecretKey secret) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secret);
            return cipher.doFinal(message.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == LoaderID) {
            Toast.makeText(this, "onCreateLoader:" + id, Toast.LENGTH_SHORT).show();
            return new MyLoader(this, args);
        }
        throw new IllegalArgumentException("Invalid loader id");
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished: " + data);
            Toast.makeText(this, "Дешифровано: " + data, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }
}
