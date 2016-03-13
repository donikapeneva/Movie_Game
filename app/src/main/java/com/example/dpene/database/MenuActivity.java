package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mapButton;
    private Button exitButton;
    private Button switchUserButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.mapButton = (Button) findViewById(R.id.map_button);
        this.mapButton.setOnClickListener(this);
        this.exitButton = (Button) findViewById(R.id.exit_button);
        this.exitButton.setOnClickListener(this);
        this.switchUserButton = (Button) findViewById(R.id.switch_user_button);
        this.switchUserButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent nextActivity;
        switch(v.getId()){
            default:
            case R.id.map_button:
                nextActivity = new Intent(this, MapActivity.class);
                break;
            case R.id.exit_button:
                //tuk ne trqbva li da mu zadadem da izlzia ot cqloto prilojenie, a ne da se vrushta kum logIn
                // EXIT
                nextActivity = new Intent(this, LoginActivity.class);
                break;
            case R.id.switch_user_button:
                // logika za izlizane
                nextActivity = new Intent(this, LoginActivity.class);
                break;
        }
        startActivity(nextActivity);
    }
}