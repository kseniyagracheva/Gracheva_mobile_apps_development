package ru.mirea.grachevaks.firebaseauth;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.grachevaks.firebaseauth.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Инициализация Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Кнопка "Создать аккаунт"
        binding.emailCreateAccountButton.setOnClickListener(v -> {
            createAccount(
                    binding.fieldEmail.getText().toString(),
                    binding.fieldPassword.getText().toString()
            );
        });

        // Кнопка "Войти"
        binding.emailSignInButton.setOnClickListener(v -> {
            signIn(
                    binding.fieldEmail.getText().toString(),
                    binding.fieldPassword.getText().toString()
            );
        });

        // Кнопка "Выйти"
        binding.signOutButton.setOnClickListener(v -> signOut());

        // Кнопка "Верифицировать email"
        binding.verifyEmailButton.setOnClickListener(v -> sendEmailVerification());
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            binding.statusTextView.setText(getString(
                    R.string.emailpassword_status_fmt, user.getEmail(), user.isEmailVerified()));
            binding.detailTextView.setText(getString(
                    R.string.firebase_status_fmt, user.getUid()));
            binding.emailPasswordButtons.setVisibility(View.GONE);
            binding.emailPasswordFields.setVisibility(View.GONE);
            binding.signedInButtons.setVisibility(View.VISIBLE);
            binding.verifyEmailButton.setEnabled(!user.isEmailVerified());
        } else {
            binding.statusTextView.setText(R.string.signed_out);
            binding.detailTextView.setText(null);
            binding.emailPasswordButtons.setVisibility(View.VISIBLE);
            binding.emailPasswordFields.setVisibility(View.VISIBLE);
            binding.signedInButtons.setVisibility(View.GONE);
        }
    }

    private void createAccount(String email, String password) {
        if (!validateForm()) return;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void signIn(String email, String password) {
        if (!validateForm()) return;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                        updateUI(null);
                        binding.statusTextView.setText(R.string.auth_failed);
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void sendEmailVerification() {
        binding.verifyEmailButton.setEnabled(false);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(this, task -> {
                        binding.verifyEmailButton.setEnabled(true);
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = binding.fieldEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            binding.fieldEmail.setError("Required.");
            valid = false;
        } else {
            binding.fieldEmail.setError(null);
        }

        String password = binding.fieldPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            binding.fieldPassword.setError("Required.");
            valid = false;
        } else {
            binding.fieldPassword.setError(null);
        }
        return valid;
    }
}
