package ru.mirea.grachevaks.mireaproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.grachevaks.mireaproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        binding.appBarMain.fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .setAnchorView(R.id.fab).show();
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_data,
                R.id.nav_web,
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_music_service,
                R.id.nav_compass,
                R.id.nav_camera_note,
                R.id.nav_voice_recorder,
                R.id.nav_profile,
                R.id.nav_notes)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        binding.appBarMain.contentMain.button.setOnClickListener(v -> loadIpAndWeather());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void loadIpAndWeather() {
        binding.appBarMain.contentMain.textIp.setText("Загрузка IP...");
        binding.appBarMain.contentMain.textCity.setText("");
        binding.appBarMain.contentMain.textRegion.setText("");
        binding.appBarMain.contentMain.textCountry.setText("");
        binding.appBarMain.contentMain.textWeather.setText("");
        new DownloadIpInfoTask().execute("https://ipinfo.io/json");
    }

    // Загрузка IP и геоданных
    private class DownloadIpInfoTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();
                    connection.disconnect();
                    return new JSONObject(sb.toString());
                } else {
                    connection.disconnect();
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            if (json != null) {
                String ip = json.optString("ip", "N/A");
                String city = json.optString("city", "N/A");
                String region = json.optString("region", "N/A");
                String country = json.optString("country", "N/A");
                String loc = json.optString("loc", null);

                binding.appBarMain.contentMain.textIp.setText("IP: " + ip);
                binding.appBarMain.contentMain.textCity.setText("Город: " + city);
                binding.appBarMain.contentMain.textRegion.setText("Регион: " + region);
                binding.appBarMain.contentMain.textCountry.setText("Страна: " + country);

                if (loc != null && loc.contains(",")) {
                    String[] coords = loc.split(",");
                    latitude = coords[0];
                    longitude = coords[1];
                    new DownloadWeatherTask().execute(latitude, longitude);
                } else {
                    binding.appBarMain.contentMain.textWeather.setText("Нет координат для прогноза.");
                }
            } else {
                binding.appBarMain.contentMain.textIp.setText("Ошибка загрузки IP");
            }
        }
    }

    // Загрузка погоды по координатам
    private class DownloadWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String lat = params[0];
            String lon = params[1];
            String urlString = "https://api.open-meteo.com/v1/forecast?latitude=" + lat +
                    "&longitude=" + lon + "&current_weather=true";
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();
                    connection.disconnect();
                    return sb.toString();
                } else {
                    connection.disconnect();
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject currentWeather = json.getJSONObject("current_weather");
                    double temperature = currentWeather.getDouble("temperature");
                    double windspeed = currentWeather.getDouble("windspeed");
                    String weatherText = "Температура: " + temperature + "°C\n" +
                            "Ветер: " + windspeed + " м/с";
                    binding.appBarMain.contentMain.textWeather.setText(weatherText);
                } catch (Exception e) {
                    binding.appBarMain.contentMain.textWeather.setText("Ошибка разбора погоды");
                }
            } else {
                binding.appBarMain.contentMain.textWeather.setText("Ошибка загрузки погоды");
            }
        }
    }
}
