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

        //implementaciq za progressbar-a
        progressBar = (ProgressBar) findViewById(R.id.loading_page_progressbar);

//        new Thread(new Runnable() {
 //           public void run() {
        while (progressStatus < 100) {

           // progressBar.incrementProgressBy(20);
            progressBar.setProgress(20);

        }




                Intent nextActivity = new Intent(this, LetterActivity.class);
                startActivity(nextActivity);
//            }
//        }
//            }
//        }).start();
//    }


    }
}
