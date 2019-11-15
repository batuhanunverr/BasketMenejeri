package com.example.basketmenejeri;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class GifPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_page);

        final Handler handler = new Handler();
        Timer timer;


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        verileriCek();

                    }

                    private void verileriCek() {
                        Intent i = new Intent(GifPage.this , LoginP.class);
                        startActivity(i);

                    }
                });
            }
        };

        timer = new Timer();

        timer.schedule(timerTask,1000);

    }
}
