package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LetterActivity extends AppCompatActivity {

    private Button tapToContinue;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_2);

        this.tapToContinue = (Button) findViewById(R.id.letter_tap_to_continue_button);
        this.tapToContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(LetterActivity.this, MenuActivity.class);
                Bundle data = getIntent().getExtras();
                nextActivity.putExtra(LoginActivity.PLAYER_USERNAME, data.getString("playerUsername"));
                startActivity(nextActivity);
            }
        });

    }
}
