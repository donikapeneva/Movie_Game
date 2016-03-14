package com.example.dpene.database;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dpene.database.model.Player;
import com.example.dpene.database.model.PlayerManager;
import com.example.dpene.database.model.RegularQuestion;
import com.example.dpene.database.model.dao.PlayerDAO;
import com.example.dpene.database.model.dao.RegularQuestionDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RegularQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView regQuestionTextView;
    private Button answer1_button;
    private Button answer2_button;
    private Button answer3_button;
    private Button answer4_button;
    private RegularQuestionDAO regularQuestionDAO;
    private RegularQuestion regularQuestion;
    private String rightAnswer;
    private PlayerManager playerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_question);


        regQuestionTextView = (TextView) findViewById(R.id.regular_question_text);
        answer1_button = (Button) findViewById(R.id.answer1_button);
        answer1_button.setOnClickListener(this);
        answer2_button = (Button) findViewById(R.id.answer2_button);
        answer2_button.setOnClickListener(this);
        answer3_button = (Button) findViewById(R.id.answer3_button);
        answer3_button.setOnClickListener(this);
        answer4_button = (Button) findViewById(R.id.answer4_button);
        answer4_button.setOnClickListener(this);

       // this.playerManager = PlayerManager.getInstance(this);

      //  regularQuestionDAO = new RegularQuestionDAO(this);
      //  this.regularQuestion = regularQuestionDAO.getRegularQuestion(playerManager.getReachedQuestionId());
            this.regularQuestion = new RegularQuestion("This is question?", "I am the right Answer", "I am the wrong :(", "Wrong 2 :((",
                    "You fools i am the right answer", 1, 2);

        regQuestionTextView.setText(regularQuestion.getQuestion());

        this.rightAnswer = regularQuestion.getRightAnswer();

        ArrayList<String> answers = new ArrayList<String>();
        answers.add(rightAnswer);
        String[] wrongAnswers = regularQuestion.getWrongAnswers();
        for(String ans : wrongAnswers){
            answers.add(ans);
        }

        Collections.shuffle(answers);
        int firstAns = 0;
        answer1_button.setText(answers.get(firstAns++));
        answer2_button.setText(answers.get(firstAns++));
        answer3_button.setText(answers.get(firstAns++));
        answer4_button.setText(answers.get(firstAns));

    }

    @Override
    public void onClick(View view){
        Button clicked = (Button) view;
        if(clicked.getText().equals(rightAnswer)){
            //change the color of the button to show the player it was the right answer
            clicked.setBackgroundResource(R.color.rightAnswer);

            long nextQuestionId = this.regularQuestion.getNextQuestion();
            playerManager.setReachedQuestionId(nextQuestionId);

            if(nextQuestionId == 1){
                Intent winning = new Intent(this, WinningActivity.class);
                startActivity(winning);
            } else {
                long reachedQuestionLevel = this.regularQuestion.getLevelId();
                if (playerManager.goToNextLevel(reachedQuestionLevel)) {
                    playerManager.setIdOfLevel(reachedQuestionLevel);
                    //TODO show player that he goes a level up
                    Intent nextActivity = new Intent(this, MapActivity.class);
                    startActivity(nextActivity);
                } else {
                    Intent nextQuestion = new Intent(this, RegularQuestionActivity.class);
                    startActivity(nextQuestion);
                }
            }

        } else {
            //red color for wrong answer
            clicked.setBackgroundResource(R.color.wrongAnswer);

            boolean goToLogicalQuestion = playerManager.loseLifeAndGoToLogicQuestion();
            if(goToLogicalQuestion){
                Intent nextActivity = new Intent(this, SaveLifeActivity.class);
                startActivity(nextActivity);
            } else {
                //TODO show the player that he has lost a life
            }
        }
    }
}
