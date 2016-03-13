package com.example.dpene.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dpene.database.model.Player;
import com.example.dpene.database.model.RegularQuestion;
import com.example.dpene.database.model.dao.PlayerDAO;
import com.example.dpene.database.model.dao.RegularQuestionDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RegularQuestionActivity extends AppCompatActivity {

    private TextView regQuestionTextView;
    private Button answer1_button;
    private Button answer2_button;
    private Button answer3_button;
    private Button answer4_button;
    private Player currentPlayer;
    private RegularQuestionDAO regularQuestionDAO;
    private RegularQuestion regularQuestion;
    private String rightAnswer;
    private PlayerDAO playerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_question);


        regQuestionTextView = (TextView) findViewById(R.id.regular_question_text);
        answer1_button = (Button) findViewById(R.id.answer1_button);
        answer2_button = (Button) findViewById(R.id.answer2_button);
        answer3_button = (Button) findViewById(R.id.answer3_button);
        answer4_button = (Button) findViewById(R.id.answer4_button);

       // playerDAO =  new PlayerDAO(this);
       // this.currentPlayer = playerDAO.getPlayer(playerUsername);

        regularQuestionDAO = new RegularQuestionDAO(this);
        this.regularQuestion = regularQuestionDAO.getRegularQuestion(currentPlayer.getReachedQuestionId());

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
        answer1_button.setText(answers.get(firstAns++));
        answer1_button.setText(answers.get(firstAns++));
        answer1_button.setText(answers.get(firstAns));

    }

    public void onClick(View view){
        Button clicked = (Button) view;
        if(clicked.getText().equals(rightAnswer)){
            //TODO dialog fragment to show him it was the right answer
            long nextQuestionId = this.regularQuestion.getNextQuestion();
            currentPlayer.setReachedQuestionId(nextQuestionId);
            if(nextQuestionId == 1){
                //TODO Activity for winning the whole game
            } else {
                long reachedQuestionLevel = this.regularQuestion.getLevelId();

                if (currentPlayer.goToNextLevel(reachedQuestionLevel)) {
                    currentPlayer.setIdOfLevel(reachedQuestionLevel);
                    //TODO show player that he goes a level up
                    Intent nextActivity = new Intent(this, MapActivity.class);
                    startActivity(nextActivity);
                }
            }

        } else {
            boolean goToLogicalQuestion = currentPlayer.loseLifeAndGoToLogicQuestion();
            if(goToLogicalQuestion){
                Intent nextActivity = new Intent(this, SaveLifeActivity.class);
                startActivity(nextActivity);
            } else {
                //TODO show the player that he has lost a life
            }
        }
    }
}
