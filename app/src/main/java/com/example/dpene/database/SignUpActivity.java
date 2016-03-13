package com.example.dpene.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dpene.database.model.PlayerManager;

public class SignUpActivity extends AppCompatActivity {

    private PlayerManager manager;
    private EditText email;
    private EditText username;
    private EditText password;
    private EditText repeatedPassword;

    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        manager = PlayerManager.getInstance(this);

        Bundle data = getIntent().getExtras();

        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        username.setText(data != null ? data.getString("username") : "");
        password = (EditText) findViewById(R.id.password);
        repeatedPassword = (EditText) findViewById(R.id.repeat_password);

        signUp = (Button) findViewById(R.id.sing_up_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isCorrect = true;

                if (manager.checkEmail(email.getText().toString())) {
                    email.setError("This email is already used");
                    isCorrect = false;
                }

                if (manager.checkUsername(username.getText().toString())) {
                    username.setError("This username is already taken");
                    isCorrect = false;
                }

                if (!password.getText().toString().equals(repeatedPassword.getText().toString())) {
                    repeatedPassword.setError("The passwords don't match");
                    isCorrect = false;
                }

                //TODO
                if (isCorrect) {
                    if (manager.registerPlayer(email.getText().toString(), username.getText().toString(),
                            password.getText().toString()) < 0) {
                        Toast.makeText(SignUpActivity.this, "-1", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(SignUpActivity.this, "Successful registration", Toast.LENGTH_SHORT).show();
                    finish();
//                    Intent nextActivity = new Intent(SignUpActivity.this, LoadingActivity.class);
//                    startActivity(nextActivity);
                }
            }

        });


    }
}
