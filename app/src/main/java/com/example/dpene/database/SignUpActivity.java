package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dpene.database.model.UserManager;

public class SignUpActivity extends AppCompatActivity {

    private UserManager manager;
    private EditText email;
    private EditText username;
    private EditText password;
    private EditText repeatedPassword;

    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        manager = UserManager.getInstance();

        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repeatedPassword = (EditText) findViewById(R.id.repeat_password);

        signUp = (Button) findViewById(R.id.sing_up_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(manager.registerNewUser(email.getText().toString(), username.getText().toString(),
                        password.getText().toString(), repeatedPassword.getText().toString())) {
                    Intent nextActivity = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(nextActivity);
                } else {
                    Toast.makeText(SignUpActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
