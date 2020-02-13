package com.example.user.MediCare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.user.MediCare.R;

public class SplashScreen_Activity extends AppCompatActivity {

    private ProgressBar progressBar ;
    int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_);

        setTitle(" MediCare ");

        progressBar = findViewById(R.id.progressBar_id);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startOn();

            }
        });

        thread.start();

    }

    private void doWork() {

        for (progress=10;progress<=100;progress = progress+10){

            try {
                Thread.sleep(250);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
    public void startOn (){
        startActivity(new Intent(SplashScreen_Activity.this, FirstActivity.class));
        finish();
    }
}
