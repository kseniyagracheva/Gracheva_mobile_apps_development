package ru.mirea.grachevaks.data_thread;

import android.os.Bundle;
import ru.mirea.grachevaks.data_thread.databinding.ActivityMainBinding;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String report = "runn1 запускается первым,\n" +
                "runn2 запускается вторым сразу после завершения runn1\n" +
                "runn3 запускается последним с задержкой в 2 секунды\n" +
                "runOnUiThread - сразу запускает задачи в UI-поток\n" +
                "post - ставит задачи в очередь, ожидая завершения предыдущих\n" +
                "postDelayed - ставит задачи в очередь, как post, но еще устанавлиает\n" +
                "некотрую задержку.";

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn3");
                binding.tvInfo.postDelayed(() -> binding.tvInfo.setText(report), 2000);
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }
}