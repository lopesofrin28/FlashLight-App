package com.example.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.flashlightapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
//        getSupportActionBar().show();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.button.getText().toString().equals("Turn On")) {
                    binding.button.setText(R.string.turnOff);
                    binding.flashimage.setImageResource(R.drawable.flashlight_on);
                    changeLightState(true);
                }else {
                    binding.button.setText(R.string.turnon);
                    binding.flashimage.setImageResource(R.drawable.flashlight_off);
                    changeLightState(false);
                }
            }
        });
    }

    private void changeLightState(boolean state) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            String camId =null;
            try {
                camId= cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(camId,state);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
//                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText(R.string.turnon);

    }
}