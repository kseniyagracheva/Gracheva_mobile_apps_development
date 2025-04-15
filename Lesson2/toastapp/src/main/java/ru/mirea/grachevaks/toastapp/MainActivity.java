package ru.mirea.grachevaks.toastapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtxtCountSymbols;
    private Button btnCountSymbols;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtxtCountSymbols = findViewById(R.id.CountSymbolsText);
        btnCountSymbols = findViewById(R.id.CountSymbolsButton);

        View.OnClickListener onClickCountSymbols = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Студент №8. Группа БСБО-09-22. Кол-во символов: "+edtxtCountSymbols.length(),
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        btnCountSymbols.setOnClickListener(onClickCountSymbols);


    }
}