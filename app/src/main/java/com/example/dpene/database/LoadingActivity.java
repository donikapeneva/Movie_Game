package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

//        implementaciq za progressbar-a


        Intent nextActivity = new Intent(this, LetterActivity.class);
        startActivity(nextActivity);

    }
}
