package com.example.dpene.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dpene.database.model.PlayerManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PLAYER_USERNAME = "playerUsername";
    private static final int REQUEST_CODE_FOR_SIGN_UP = 1;
    private EditText username;
    private EditText password;
    private Button logIn;
    private Button signUp;
    private PlayerManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.manager = PlayerManager.getInstance(this);

        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);

        this.logIn = (Button) findViewById(R.id.login_button);
        this.logIn.setOnClickListener(this);
        this.signUp = (Button) findViewById(R.id.sign_in_reference_button);
        this.signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
            case R.id.login_button:
                if (manager.login(this.username.getText().toString(), this.password.getText().toString())) {
                    Intent nextActivity = new Intent(LoginActivity.this, LoadingActivity.class);
                    startActivity(nextActivity);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.sign_in_reference_button:
                Intent nextActivity = new Intent(LoginActivity.this, SignUpActivity.class);
                nextActivity.putExtra("username", this.username.getText().toString());
                startActivityForResult(nextActivity, REQUEST_CODE_FOR_SIGN_UP);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_FOR_SIGN_UP) {
            if (resultCode == RESULT_OK) {
                this.username.setText(data.getExtras().getString("username"));
                this.password.setText(data.getExtras().getString("password"));
            }
        }
    }
}

