package com.example.dpene.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dpene.database.model.LogicQuestionManager;

public class LogicalQuestionActivity extends AppCompatActivity {


    private TextView question;
    private EditText answer;
    private Button answerButton;
    private LogicQuestionManager questionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logical_question);

        this.questionManager = LogicQuestionManager.getInstance(this);

        this.question = (TextView) findViewById(R.id.logical_question_text);
        this.question.setText(this.questionManager.getLogicQuestion().getQuestion());

        this.answer = (EditText) findViewById(R.id.logical_answer);
        this.answerButton = (Button) findViewById(R.id.logical_answer_button);
        this.answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer.getText().toString().trim().equals(questionManager.getLogicQuestion().getRightAnswer())) {
                    startActivity(new Intent(LogicalQuestionActivity.this, RebornActivity.class));
                    //TODO change lifes
                } else {
                    startActivity(new Intent(LogicalQuestionActivity.this, GameOverActivity.class));
                }
            }
        });
    }
}
