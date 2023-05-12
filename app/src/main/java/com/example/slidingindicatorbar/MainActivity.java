package com.example.slidingindicatorbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.zaaach.widget.SlidingIndicatorBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private float ratio = 0;
    private boolean reversed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlidingIndicatorBar indicatorBar = findViewById(R.id.indicator_bar);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (ratio >= 1) {
                    reversed = true;
                }
                if (ratio <= 0) {
                    reversed = false;
                }
                ratio += reversed ? -0.1f : 0.1f;
                runOnUiThread(() -> {
                    indicatorBar.setBendingRatio(ratio);
                });
            }
        }, 800, 60);
    }
}