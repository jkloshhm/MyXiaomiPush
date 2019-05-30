package com.xiaomi.jackpush;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class CustomActivity extends Activity {

    Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(CustomActivity.this,MainActivity.class));
                finish();
            }
        };

        mTimer.schedule(timerTask, 5000);
    }
}

