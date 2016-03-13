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
                String uname = username.getText().toString();
                String upassword = password.getText().toString();
                String uemail = email.getText().toString();

                if(manager.validateEmail(uemail)) {
                    if (manager.checkEmail(uemail)) {
                        email.setError("This email is already used");
                        isCorrect = false;
                    }
                } else {
                    email.setError("Invalid email");
                    isCorrect = false;
                }

                if (manager.validateUsername(uname)) {
                    if (manager.checkUsername(uname)) {
                        username.setError("This username is already taken");
                        isCorrect = false;
                    }
                } else {
                    username.setError("Name's length must be bigger than 3");
                    isCorrect = false;
                }

                if (manager.strongPasword(upassword)) {
                    if (!upassword.equals(repeatedPassword.getText().toString())) {
                        repeatedPassword.setError("The passwords don't match");
                        isCorrect = false;
                    }
                } else {
                    password.setError("Please, insert strong password between 5 - 10 symbols");
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
                }
            }

        });


    }
}
