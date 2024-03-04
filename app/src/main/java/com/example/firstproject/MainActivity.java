package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;
import androidx.annotation.NonNull;


public class MainActivity extends AppCompatActivity {

    CameraManager cameraManager;
    String cameraId;
    boolean isFlashOn = false;

    Vibrator vibrator;

    private TextView textViewLatitude, textViewLongitude;

    LocationManager locationManager;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }


        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        textViewLatitude = findViewById(R.id.latitude);
        textViewLongitude = findViewById(R.id.longitude);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }



    public void toggleVibration(View v) {
        vibrator.vibrate(1000);
    }

    public void toggleFlashlight(View v) {
        try {
            if (!isFlashOn) {
                cameraManager.setTorchMode(cameraId, true);
                isFlashOn = true;
            } else {
                cameraManager.setTorchMode(cameraId, false);
                isFlashOn = false;
            }
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void getLocation(View v) {
        // Проверяем разрешения, запросим их, если не предоставлены
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            startLocationUpdates();
        }
    }

    private void startLocationUpdates() {
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                String lat = "Широта: " + latitude;
                String lon = "Долгота: " + longitude;
                textViewLatitude.setText(lat);
                textViewLongitude.setText(lon);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

        };

        // Запрос обновлений местоположения
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение предоставлено, начнем получение координат
                startLocationUpdates();
            } else {
                // Разрешение не предоставлено, обработайте ситуацию соответственно
                Toast.makeText(this, "Для получения местоположения требуется разрешение", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startSecondPage(View v){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
    public void startThirdPage(View v){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

}