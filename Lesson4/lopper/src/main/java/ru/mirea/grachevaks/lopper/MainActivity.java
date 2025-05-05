package ru.mirea.grachevaks.lopper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.grachevaks.lopper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyLooper myLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String res = msg.getData().getString("result");
                Log.d("MainActivity", res);
            }
        };

        myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        binding.SendButton.setOnClickListener(v -> {
            String age = binding.AgeEditText.getText().toString();
            String job = binding.JobEditText.getText().toString();

            Message msg = Message.obtain();
            Bundle b = new Bundle();
            b.putString("AGE", age);
            b.putString("JOB", job);
            msg.setData(b);

            myLooper.mHandler.sendMessage(msg);
        });
    }
}