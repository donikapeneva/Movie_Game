package com.example.dpene.database;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.logging.Handler;

public class LoadingActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int fullProgressBar = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        progressBar = (ProgressBar) findViewById(R.id.loading_page_progressbar);
        progressBar.setProgress(0);
        //set color
        progressBar.getProgressDrawable().setColorFilter(Color.argb(255, 255, 160, 38), android.graphics.PorterDuff.Mode.SRC_IN);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressBar.getProgress() < fullProgressBar){
                    progressBar.setProgress(progressBar.getProgress() + 10);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Intent nextActivity = new Intent(LoadingActivity.this, LetterActivity.class);
                Bundle data = getIntent().getExtras();
                nextActivity.putExtra(LoginActivity.PLAYER_USERNAME, data.getString("playerUsername"));
                startActivity(nextActivity);
            }
        }).start();





    }
}
