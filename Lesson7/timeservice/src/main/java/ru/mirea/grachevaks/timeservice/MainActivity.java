    package ru.mirea.grachevaks.timeservice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import ru.mirea.grachevaks.timeservice.databinding.ActivityMainBinding;

    public class MainActivity extends AppCompatActivity {
        private ActivityMainBinding binding;
        private final String host = "time.nist.gov";
        private final int port = 13;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            binding.getTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GetTimeTask().execute();
                }
            });
        }

        private class GetTimeTask extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... params) {
                String timeResult = "";
                try {
                    Socket socket = new Socket(host, port);
                    BufferedReader reader = SocketUtils.getReader(socket);
                    reader.readLine();
                    String rawLine = reader.readLine();
                    if (rawLine != null) {
                        String[] parts = rawLine.split(" ");
                        if (parts.length > 2) {
                            String date = parts[1];
                            String time = parts[2];
                            timeResult = "Дата: " + date + "\nВремя (UTC): " + time;
                        } else {
                            timeResult = "Ошибка разбора строки";
                        }
                    } else {
                        timeResult = "Нет данных";
                    }
                    socket.close();
                } catch (IOException e) {
                    timeResult = "Ошибка: " + e.getMessage();
                }
                return timeResult;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                binding.timeTextView.setText(result);
            }
        }
    }
