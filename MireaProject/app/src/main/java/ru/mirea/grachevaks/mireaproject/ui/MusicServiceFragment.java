package ru.mirea.grachevaks.mireaproject.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import ru.mirea.grachevaks.mireaproject.R;
import android.util.Log;

public class MusicServiceFragment extends Fragment {
    private static final String TAG = "MusicServiceFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView вызван");
        View view = inflater.inflate(R.layout.fragment_music_service, container, false);

        Button startBtn = view.findViewById(R.id.button_start_music);
        Button stopBtn = view.findViewById(R.id.button_stop_music);

        startBtn.setOnClickListener(v -> {
            Log.d(TAG, "Нажата кнопка Воспроизвести");
            Context context = requireContext();
            context.startService(new Intent(context, MusicPlayerService.class));
        });

        stopBtn.setOnClickListener(v -> {
            Log.d(TAG, "Нажата кнопка Остановить");
            Context context = requireContext();
            context.stopService(new Intent(context, MusicPlayerService.class));
        });

        return view;
    }
}

