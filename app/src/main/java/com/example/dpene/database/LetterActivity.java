package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LetterActivity extends AppCompatActivity {

    private Button tap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        this.tap = (Button) findViewById(R.id.hogwarts_letter_button);
        this.tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(LetterActivity.this, MenuActivity.class);
                startActivity(nextActivity);
            }
        });

    }
}
