package ru.mirea.grachevaks.favoritebooks;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_share);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView textViewFavBook = findViewById(R.id.TextViewFavBook2);
            TextView textViewQuote = findViewById(R.id.TextViewQuote);

            String book_name = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quote_name = extras.getString(MainActivity.QUOTES_KEY);

            textViewFavBook.setText(String.format("Любимая книга: %s", book_name));
            textViewQuote.setText(String.format("Цитата: %s", quote_name));
        }
    }

    public void SendBookData(View view){
        EditText editTextBook = findViewById(R.id.EditTextEnterBook);
        EditText editTextQuote = findViewById(R.id.EditTexEnterQuote);

        String book = editTextBook.getText().toString();
        String quote = editTextQuote.getText().toString();

        String result = String.format(book,quote);

        Intent data = new Intent();
        data.putExtra(MainActivity.USER_MESSAGE, result);
        setResult(Activity.RESULT_OK,data);
        finish();
    }
}