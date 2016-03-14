package com.example.dpene.database;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.dpene.database.model.PlayerManager;

import java.util.logging.Handler;

public class LoadingActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int fullProgressBar = 100;
    private PlayerManager playerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        playerManager = PlayerManager.getInstance(this);

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

                if(playerManager.getLevel() == 1){
                    startActivity(new Intent(LoadingActivity.this, LetterActivity.class));
                }
                else{
                    startActivity(new Intent(LoadingActivity.this, MapActivity.class));
                }
            }
        }).start();





    }
}
