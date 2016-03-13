package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RebornActivity extends AppCompatActivity {

    private Button tapToContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reborn);

        this.tapToContinue = (Button) findViewById(R.id.reborn_button);
        this.tapToContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO kum MapActivity ili kum reachedRegularQuestion :?
                startActivity(new Intent(RebornActivity.this, MapActivity.class));
            }
        });
    }
}
