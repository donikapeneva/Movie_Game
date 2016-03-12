package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dpene.database.model.UserManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button logIn;
    private Button signUp;
    private UserManager manager;
    private static final int REQUEST_CODE_FOR_SIGN_UP = 1;
    private static final int REQUEST_CODE_FOR_LOADING = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.manager = UserManager.getInstance();

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
                if(manager.existsUser(username.getText().toString()) && manager.rightPassword( username.getText().toString(), password.getText().toString())) {
                    nextActivity = new Intent(LoginActivity.this, LoadingActivity.class);
                    nextActivity.putExtra("username", this.username.getText().toString());
                    startActivity(nextActivity);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.sign_in_reference_button:
                nextActivity = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(nextActivity);
                break;
        }

    }
}

