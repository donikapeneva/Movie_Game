package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dpene.database.model.PlayerManager;

public class MapActivity extends AppCompatActivity {

    private PlayerManager playerManager;
    private TextView level;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;
    private ImageView levelHogwarts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        this.playerManager = PlayerManager.getInstance(this);

        this.level = (TextView) findViewById(R.id.level);
        this.level.setText("Level " + playerManager.getPlayer().getIdOfLevel());

        this.heart1 = (ImageView) findViewById(R.id.heart1);
        this.heart2 = (ImageView) findViewById(R.id.heart2);
        this.heart3 = (ImageView) findViewById(R.id.heart3);

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
