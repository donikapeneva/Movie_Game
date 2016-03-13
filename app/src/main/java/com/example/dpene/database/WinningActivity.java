package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Map;

public class WinningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);

        //TODO the xml of the activity, some message perhaps

        //get him to the entirely uncovered map when he taps on the screen
        Intent nextActivity = new Intent(this, MapActivity.class);
        startActivity(nextActivity);

    }
}
