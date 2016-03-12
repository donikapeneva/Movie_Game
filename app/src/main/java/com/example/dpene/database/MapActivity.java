package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dpene.database.model.RegularQuestion;

public class MapActivity extends AppCompatActivity {

    private ImageView levelHogwarts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        this.levelHogwarts = (ImageView) findViewById(R.id.hogwarts_castle);
        levelHogwarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MapActivity.this, RegularQuestionActivity.class);
                startActivity(nextActivity);

            }
        });

    }
}
