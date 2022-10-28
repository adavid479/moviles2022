package com.example.myfirstapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.myfirstapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intentLogIn = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intentLogIn);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);

    }
}
