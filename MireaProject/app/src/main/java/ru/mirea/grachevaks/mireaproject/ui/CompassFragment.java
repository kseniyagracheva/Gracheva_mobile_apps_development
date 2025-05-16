package ru.mirea.grachevaks.mireaproject.ui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import ru.mirea.grachevaks.mireaproject.R;

public class CompassFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;

    private float[] gravity;
    private float[] geomagnetic;

    private ImageView compassArrow;
    private TextView directionText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);
        compassArrow = view.findViewById(R.id.compass_arrow);
        directionText = view.findViewById(R.id.direction_text);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            gravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            geomagnetic = event.values;

        if (gravity != null && geomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, gravity, geomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimuthRad = orientation[0];
                float azimuthDeg = (float)Math.toDegrees(azimuthRad);
                if (azimuthDeg < 0) azimuthDeg += 360;

                // Повернуть стрелку
                compassArrow.setRotation(azimuthDeg);

                // Определить направление
                String direction = getDirectionText(azimuthDeg);
                directionText.setText("Пирог находится в этом направлении: " + direction);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    private String getDirectionText(float azimuth) {
        if (azimuth >= 350 || azimuth <= 10) return "Север";
        if (azimuth > 10 && azimuth < 80) return "Северо-Восток";
        if (azimuth >= 80 && azimuth <= 100) return "Восток";
        if (azimuth > 100 && azimuth < 170) return "Юго-Восток";
        if (azimuth >= 170 && azimuth <= 190) return "Юг";
        if (azimuth > 190 && azimuth < 260) return "Юго-Запад";
        if (azimuth >= 260 && azimuth <= 280) return "Запад";
        if (azimuth > 280 && azimuth < 350) return "Северо-Запад";
        return "";
    }
}
