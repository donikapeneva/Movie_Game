package com.example.dpene.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dpene.database.model.PlayerManager;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private PlayerManager playerManager;
    private TextView level;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;
    private ImageView levelHogwarts;
    private ImageView pathHogwartsToPrison;
    private ImageView levelPrison;
    private ImageView pathPrisonToPirate;
    private ImageView levelPirate;

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
        this.levelPrison = (ImageView) findViewById(R.id.prison_break);
        this.levelPirate = (ImageView) findViewById(R.id.ship);

        this.pathHogwartsToPrison = (ImageView) findViewById(R.id.path_to_prison);
        this.pathPrisonToPirate = (ImageView) findViewById(R.id.path_to_ship);

        levelHogwarts.setOnClickListener(this);
        levelPrison.setOnClickListener(this);
        levelPirate.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        showHearts();
        this.level.setText("Level " + playerManager.getLevel());

        switch(playerManager.getLevel()){
            case 3: pathPrisonToPirate.setVisibility(View.VISIBLE);
            case 2: pathHogwartsToPrison.setVisibility(View.VISIBLE);
                break;

        }

    }

    private void showHearts() {
        switch (playerManager.getLives()) {
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

    @Override
    public void onClick(View v) {

        int clickedLevel;
        switch (v.getId()) {
            default:
            case R.id.hogwarts_castle:
                clickedLevel = 1;
                break;
            case R.id.prison_break:
                clickedLevel = 2;
                break;
            case R.id.ship:
                clickedLevel = 3;
                break;
        }

        int currentLevel = playerManager.getLevel();

        if(playerManager.getReachedQuestionId() == 0){
            Toast.makeText(MapActivity.this, "You have answered all the questions", Toast.LENGTH_SHORT).show();
        } else {
            if (clickedLevel < currentLevel) {
                Toast.makeText(MapActivity.this, "You have already passed this level", Toast.LENGTH_SHORT).show();
            } else if (clickedLevel > currentLevel) {
                Toast.makeText(MapActivity.this, "You haven't reached that level yet", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(MapActivity.this, RegularQuestionActivity.class));
            }
        }
    }
}
