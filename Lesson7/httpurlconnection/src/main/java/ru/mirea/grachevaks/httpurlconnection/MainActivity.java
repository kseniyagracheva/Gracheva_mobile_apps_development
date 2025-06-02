package ru.mirea.grachevaks.httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.grachevaks.httpurlconnection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String IP_INFO_URL = "https://ipinfo.io/json";
    private final String WEATHER_URL = "https://api.open-meteo.com/v1/forecast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v -> checkNetworkAndLoadData());
    }

    private void checkNetworkAndLoadData() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager != null ?
                connectivityManager.getActiveNetworkInfo() : null;

        if (networkInfo != null && networkInfo.isConnected()) {
            new FetchDataTask().execute(IP_INFO_URL);
        } else {
            Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show();
        }
    }

    private class FetchDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            clearAllFields();
            binding.textIp.setText("Загрузка данных...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrlData(urls[0]);
            } catch (IOException e) {
                return "Ошибка: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject ipInfo = new JSONObject(result);

                // Обновление основных полей
                binding.textIp.setText("IP: " + ipInfo.getString("ip"));
                binding.textCity.setText("Город: " + ipInfo.optString("city"));
                binding.textRegion.setText("Регион: " + ipInfo.optString("region"));
                binding.textCountry.setText("Страна: " + ipInfo.optString("country"));

                // Загрузка погоды по координатам
                String[] coords = ipInfo.optString("loc", "0,0").split(",");
                if (coords.length == 2) {
                    String weatherUrl = WEATHER_URL +
                            "?latitude=" + coords[0] +
                            "&longitude=" + coords[1] +
                            "&current_weather=true";

                    new FetchWeatherTask().execute(weatherUrl);
                }
            } catch (JSONException e) {
                binding.textIp.setText("Ошибка разбора JSON");
            }
        }
    }

    private class FetchWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrlData(urls[0]);
            } catch (IOException e) {
                return "Ошибка погоды: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject weatherJson = new JSONObject(result);
                JSONObject currentWeather = weatherJson.getJSONObject("current_weather");

                String weatherInfo = String.format(
                        "Погода: %.1f°C",
                        currentWeather.getDouble("temperature")
                );

                binding.textWeather.setText(weatherInfo);
            } catch (JSONException e) {
                binding.textWeather.setText("Ошибка данных погоды");
            }
        }
    }

    private String downloadUrlData(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        try {
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(10000);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
            return "HTTP Error: " + connection.getResponseCode();
        } finally {
            connection.disconnect();
        }
    }

    private void clearAllFields() {
        binding.textIp.setText("");
        binding.textCity.setText("");
        binding.textRegion.setText("");
        binding.textCountry.setText("");
        binding.textWeather.setText("");
    }
}
