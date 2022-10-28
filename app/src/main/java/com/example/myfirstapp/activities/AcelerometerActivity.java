package com.example.myfirstapp.activities;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;

public class AcelerometerActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometer);

        TextView txtExistsAcelerometer = findViewById(R.id.txtExistsAcelerometer);
        TextView txtXyz = findViewById(R.id.txtXyz);
        Button btnChangeStatus = findViewById(R.id.btnChangeStatus);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(sensor == null)
            txtExistsAcelerometer.setText("Your phone does not have this function");
        else {
            txtExistsAcelerometer.setText("Sensor OK");
            sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    int y = (int) sensorEvent.values[1]*10;
                    txtXyz.setText(String.valueOf(y) + "grados");
                    if(y <= 30)
                        txtXyz.setTextColor(Color.RED);
                    else
                        txtXyz.setTextColor(Color.BLACK);

                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
        }

        btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status == 0){
                    start();
                    status = 1;
                    btnChangeStatus.setText("DETENER");
                }else{
                    stop();
                    status = 0;
                    btnChangeStatus.setText("INICIAR");
                }
            }
        });
    }

    private void start(){
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}
