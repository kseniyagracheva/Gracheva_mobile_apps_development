package ru.mirea.grachevaks.mireaproject.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ru.mirea.grachevaks.mireaproject.databinding.FragmentVoiceRecorderBinding;

public class VoiceRecorderFragment extends Fragment {
    private boolean isRecording = false;
    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    private String currentFilePath = null;

    private ArrayList<String> recordList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private FragmentVoiceRecorderBinding binding;

    // Флаг, чтобы запустить запись после получения разрешений
    private boolean pendingStartRecording = false;

    private final ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                Boolean audioGranted = result.getOrDefault(Manifest.permission.RECORD_AUDIO, false);
                Boolean storageGranted = result.getOrDefault(Manifest.permission.WRITE_EXTERNAL_STORAGE, false);
                if (audioGranted != null && storageGranted != null && audioGranted && storageGranted) {
                    Toast.makeText(requireContext(), "Разрешения получены", Toast.LENGTH_SHORT).show();
                    if (pendingStartRecording) {
                        pendingStartRecording = false;
                        startRecording();
                        binding.btnRecord.setText("Остановить запись");
                        isRecording = true;
                    }
                } else {
                    Toast.makeText(requireContext(), "Для работы приложения нужны разрешения", Toast.LENGTH_LONG).show();
                    pendingStartRecording = false;
                }
            });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVoiceRecorderBinding.inflate(inflater, container, false);

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, recordList);
        binding.listViewRecords.setAdapter(adapter);

        binding.btnRecord.setOnClickListener(v -> {
            if (!hasPermissions()) {
                pendingStartRecording = true;  // Отметить, что нужно начать запись после разрешений
                requestPermissions();
                return;
            }
            toggleRecording();
        });

        binding.listViewRecords.setOnItemClickListener((parent, view1, position, id) -> {
            String filePath = recordList.get(position);
            playRecording(filePath);
        });

        loadRecordings();

        return binding.getRoot();
    }

    private boolean hasPermissions() {
        int audioPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO);
        return audioPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        requestPermissionLauncher.launch(new String[]{
                Manifest.permission.RECORD_AUDIO
        });
    }

    private void toggleRecording() {
        if (!isRecording) {
            startRecording();
            binding.btnRecord.setText("Остановить запись");
        } else {
            stopRecording();
            binding.btnRecord.setText("Начать запись");
        }
        isRecording = !isRecording;
    }

    private void startRecording() {
        File dir = requireContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        if (dir == null) {
            Toast.makeText(requireContext(), "Ошибка доступа к хранилищу", Toast.LENGTH_SHORT).show();
            return;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "record_" + timeStamp + ".3gp";
        currentFilePath = new File(dir, fileName).getAbsolutePath();

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(currentFilePath);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(requireContext(), "Запись начата", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(requireContext(), "Ошибка записи", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        try {
            recorder.stop();
            recorder.release();
            recorder = null;
            recordList.add(currentFilePath);
            adapter.notifyDataSetChanged();
            Toast.makeText(requireContext(), "Запись сохранена", Toast.LENGTH_SHORT).show();
        } catch (RuntimeException e) {
            Toast.makeText(requireContext(), "Ошибка остановки записи", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void playRecording(String filePath) {
        if (player != null) {
            player.release();
            player = null;
        }
        player = new MediaPlayer();
        try {
            player.setDataSource(filePath);
            player.prepare();
            player.start();
            Toast.makeText(requireContext(), "Воспроизведение...", Toast.LENGTH_SHORT).show();
            player.setOnCompletionListener(mp -> {
                player.release();
                player = null;
                Toast.makeText(requireContext(), "Воспроизведение завершено", Toast.LENGTH_SHORT).show();
            });
        } catch (IOException e) {
            Toast.makeText(requireContext(), "Ошибка воспроизведения", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void loadRecordings() {
        File dir = requireContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        if (dir == null) return;
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".3gp"));
        recordList.clear();
        if (files != null) {
            for (File file : files) {
                recordList.add(file.getAbsolutePath());
            }
        }
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
