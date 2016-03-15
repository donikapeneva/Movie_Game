package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SaveLifeActivity extends AppCompatActivity {

    private Button tapToContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_life);

        tapToContinue = (Button) findViewById(R.id.continue_to_logic_question_button);
        tapToContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaveLifeActivity.this, LogicalQuestionActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
