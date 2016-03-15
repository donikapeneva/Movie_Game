package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Map;

public class WinningActivity extends AppCompatActivity {

    private ImageButton finishGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);

        finishGame = (ImageButton) findViewById(R.id.win_button);
        finishGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WinningActivity.this, MapActivity.class));
            }
        });

    }
}
