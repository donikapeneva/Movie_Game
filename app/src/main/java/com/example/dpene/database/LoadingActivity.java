package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.logging.Handler;

public class LoadingActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Handler handler;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

//        implementaciq za progressbar-a
//        progressBar = (ProgressBar) findViewById(R.id.loading_page_progressbar);

//        new Thread(new Runnable() {
//            public void run() {
//        while (true) {
//            if (progressStatus < 100) {
//                progressStatus += 1;
//
//                progressBar.setProgress(progressStatus);
//
//                try {
//                    // Sleep for 200 milliseconds.
//                    //Just to display the progress slowly
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Intent nextActivity = new Intent(this, LetterActivity.class);
//                startActivity(nextActivity);
//            }
//        }
//            }
//        }).start();
//    }


    }
}
