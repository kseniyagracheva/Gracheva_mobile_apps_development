package ru.mirea.grachevaks.mireaproject.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.mirea.grachevaks.mireaproject.databinding.FragmentCameraNoteBinding;

public class CameraNoteFragment extends Fragment {
    private FragmentCameraNoteBinding binding;
    private Uri imageUri;
    private boolean isWork = false;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private static final int REQUEST_CODE_PERMISSION = 101;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCameraNoteBinding.inflate(inflater, container, false);

        checkCameraPermission();

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK) {
                        binding.imageViewPhoto.setImageURI(imageUri);
                    }
                });

        binding.imageViewPhoto.setOnClickListener(v -> {
            if (isWork) {
                openCamera();
            } else {
                checkCameraPermission();
            }
        });

        binding.buttonSave.setOnClickListener(v -> {
            String note = binding.editTextNote.getText().toString();
            if (imageUri == null) {
                Toast.makeText(requireContext(), "Сделайте фото!", Toast.LENGTH_SHORT).show();
                return;
            }
            // Здесь можно сохранить заметку в БД, список, файл и т.д.
            Toast.makeText(requireContext(), "Заметка сохранена:\n" + note, Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }

    private void checkCameraPermission() {
        int cameraPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA);
        isWork = cameraPermission == PackageManager.PERMISSION_GRANTED;
        if (!isWork) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE_PERMISSION);
        }
    }

    private void openCamera() {
        try {
            File photoFile = createImageFile();
            String authorities = requireContext().getPackageName() + ".fileprovider";
            imageUri = FileProvider.getUriForFile(requireContext(), authorities, photoFile);

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            cameraLauncher.launch(cameraIntent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }
}
