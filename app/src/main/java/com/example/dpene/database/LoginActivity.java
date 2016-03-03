package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button logIn;
    private Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);

        this.logIn = (Button) findViewById(R.id.login_button);
        this.logIn.setOnClickListener(this);
        this.signUp = (Button) findViewById(R.id.sign_in_reference_button);
        this.signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent nextActivity;
        switch (v.getId()) {
            default:
            case R.id.login_button:
                // check if user exist, valid password
                nextActivity = new Intent(LoginActivity.this, LoadingActivity.class);
                break;

            case R.id.sign_in_reference_button:
                nextActivity = new Intent(LoginActivity.this, SignUpActivity.class);
                break;
        }
        startActivity(nextActivity);
    }
}

