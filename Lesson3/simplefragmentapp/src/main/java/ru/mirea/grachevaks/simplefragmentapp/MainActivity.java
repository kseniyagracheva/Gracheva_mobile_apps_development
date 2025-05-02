package ru.mirea.grachevaks.simplefragmentapp;

import android.os.Bundle;
import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

    public class MainActivity extends AppCompatActivity {
    private Fragment fragment1, fragment2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            View mainView = findViewById(R.id.main);
            if (mainView != null) {
                ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });
            }

            if (savedInstanceState == null) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView1, new FirstFragment())
                            .replace(R.id.fragmentContainerView2, new SecondFragment())
                            .commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, new FirstFragment())
                            .commit();
                }
            }

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                Button btnFirstFragment = findViewById(R.id.btnFirstFragment);
                Button btnSecondFragment = findViewById(R.id.btnSecondFragment);

                btnFirstFragment.setOnClickListener(v -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, new FirstFragment())
                            .commit();
                });

                btnSecondFragment.setOnClickListener(v -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, new SecondFragment())
                            .commit();
                });
            }
        }
}