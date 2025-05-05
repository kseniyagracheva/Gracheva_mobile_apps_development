package ru.mirea.grachevaks.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import ru.mirea.grachevaks.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final AtomicInteger counter = new AtomicInteger(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();

        binding.ThreadTextView.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("Номер моей группы: БСБО-09-22, номер по списку: 8, мой любимый фильм: Амели");

        binding.ThreadTextView.append("\nНовое имя потока: " + mainThread.getName());

        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

        binding.ThreadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lessons = binding.ThreadEditText1.getText().toString();
                String days = binding.ThreadEditText2.getText().toString();
                if (lessons.isEmpty() || days.isEmpty()) {
                    binding.ThreadTextView2.setText("Введите кол-во пар и дней");
                    return;
                }

                Runnable worker = new Runnable() {
                    @Override
                    public void run() {
                        int threadNum = counter.getAndIncrement();
                        Log.d("ThreadProject", String.format(
                                "Запущен поток №%d студентом группы БСБО-09-22 номер по списку 8",
                                threadNum
                        ));

                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    return;
                                }
                            }
                        }
                        Log.d("ThreadProject", "Выполнен поток №" + threadNum);

                        int lessonsNum = Integer.parseInt(lessons);
                        int daysNum = Integer.parseInt(days);
                        double avgLessonsNum = daysNum != 0 ? (double) lessonsNum / daysNum : 0.0;

                        handler.post(() -> binding.ThreadTextView2.setText(String.format("Среднее кол-во пар в день: %.2f", avgLessonsNum)));
                    }
                };
                new Thread(worker).start();
            }
        });
    }
}