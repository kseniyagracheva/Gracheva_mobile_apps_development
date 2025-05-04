package ru.mirea.grachevaks.lesson4;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.grachevaks.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.songTitle.setText("More More More");

        mediaPlayer = MediaPlayer.create(this, R.raw.more_more_more);

        binding.playPauseButton.setOnClickListener(v -> {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.more_more_more);
                isPlaying = true;
                binding.playPauseButton.setText("Пауза");
                // mediaPlayer.start();
            } else if (isPlaying) {
                isPlaying = false;
                binding.playPauseButton.setText("Играть");
                mediaPlayer.pause();
            } else {
                isPlaying = true;
                binding.playPauseButton.setText("Пауза");
                mediaPlayer.start();
            }
        });

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
