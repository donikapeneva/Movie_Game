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

    @Override
    protected void onResume() {
        super.onResume();
        showHearts();
        this.level.setText("Level " + playerManager.getLevel());
    }

    private void showHearts(){
        switch(playerManager.getLives()){
            case 3:
                heart1.setVisibility(View.VISIBLE);
                heart2.setVisibility(View.VISIBLE);
                heart3.setVisibility(View.VISIBLE);
                break;
            case 2:
                heart1.setVisibility(View.INVISIBLE);
                heart2.setVisibility(View.VISIBLE);
                heart3.setVisibility(View.VISIBLE);
                break;
            case 1:
                heart1.setVisibility(View.INVISIBLE);
                heart2.setVisibility(View.INVISIBLE);
                heart3.setVisibility(View.VISIBLE);
                break;
            case 0:
                heart1.setVisibility(View.INVISIBLE);
                heart2.setVisibility(View.INVISIBLE);
                heart3.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
