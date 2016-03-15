package com.example.dpene.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dpene.database.fragments.Communicator;
import com.example.dpene.database.fragments.LoseLifeFragment;
import com.example.dpene.database.model.PlayerManager;
import com.example.dpene.database.model.RegularQuestion;
import com.example.dpene.database.model.RegularQuestionManager;
import com.example.dpene.database.model.dao.RegularQuestionDAO;

import java.util.ArrayList;
import java.util.Collections;

public class RegularQuestionActivity extends AppCompatActivity implements View.OnClickListener, Communicator {

    private TextView regQuestionTextView;
    private Button answer1_button;
    private Button answer2_button;
    private Button answer3_button;
    private Button answer4_button;
    private RegularQuestion regularQuestion;
    private String rightAnswer;
    private PlayerManager playerManager;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;

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

        heart1 = (ImageView) findViewById(R.id.heart1_reg_question);
        heart2 = (ImageView) findViewById(R.id.heart2_reg_question);
        heart3 = (ImageView) findViewById(R.id.heart3_reg_question);



        this.playerManager = PlayerManager.getInstance(this);
        this.regularQuestion = RegularQuestionManager.getInstance().getRegularQuestion(this, playerManager.getReachedQuestionId());

        this.randomizer(regularQuestion);

        showHearts();

    }

    @Override
    public void onClick(View view) {
        Button clicked = (Button) view;
        if (clicked.getText().toString().equals(rightAnswer)) {
            //CHANGE THE COLOR OF THE BUTTON TO GREEN (for right answer)
            clicked.setBackgroundResource(R.color.rightAnswer);

            //UPDATE REACHED QUESTION OF THE PLAYER
            Integer nextQuestionId = this.regularQuestion.getNextQuestion();
            playerManager.setReachedQuestionId(nextQuestionId);

            if(nextQuestionId == null){
                //PLAYER WON THE GAME
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            Intent winning = new Intent(RegularQuestionActivity.this, WinningActivity.class);
                            startActivity(winning);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                int reachedQuestionLevel = this.regularQuestion.getLevelId();
                if (playerManager.goToNextLevel(reachedQuestionLevel)) {
                    //PLAYER GOES TO NEXT LEVEL
                    playerManager.setIdOfLevel(reachedQuestionLevel);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                //TODO show player that he goes a level up, Fragment
                              //  Intent nextActivity = new Intent(RegularQuestionActivity.this, MapActivity.class);
                              //  startActivity(nextActivity);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    //SHOW THE NEXT QUESTION
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                //------Async Task
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        } else {
            //CHANGE THE COLOR OF THE BUTTON RED (wrong answer)
            clicked.setBackgroundResource(R.color.wrongAnswer);

            //PLAYER LOSES LIFE, IF HE DOESN'T HAVE LIVES TO LOSE HE SHOULD ANSWER TO A LOGICAL QUESTION
            boolean goToLogicalQuestion = playerManager.loseLifeAndGoToLogicQuestion();
            if(goToLogicalQuestion){
                //SENDS THE PLAYER TO AN ACTIVITY, WHICH WILL SEND HIM TO LOGICAL QUESTION
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            Intent nextActivity = new Intent(RegularQuestionActivity.this, SaveLifeActivity.class);
                            startActivity(nextActivity);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
            // SHOW THE PLAYER THAT HE HAS LOST A LIFE THEN HE GOES TO THE NEXT QUESTION
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            LoseLifeFragment loseLife = new LoseLifeFragment();
                            loseLife.show(getSupportFragmentManager(), "loseLifeDialog");
                            //--------------Async Task
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }

    }

    public void showNextQuestion(){
        this.regularQuestion =  RegularQuestionManager.getInstance().getRegularQuestion(this, playerManager.getReachedQuestionId());
        randomizer(regularQuestion);
    }

    public void randomizer(RegularQuestion rq){

        this.regQuestionTextView.setText(rq.getQuestion());

        this.rightAnswer = rq.getRightAnswer();

        ArrayList<String> answers = new ArrayList<String>();
        answers.add(rightAnswer);
        String[] wrongAnswers = rq.getWrongAnswers();
        for (String ans : wrongAnswers) {
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
    public void communicate() {
        this.showHearts();
    }


    private void showHearts(){
        switch(playerManager.getLives()){
            case 3:
                heart1.setVisibility(View.VISIBLE);
                heart2.setVisibility(View.VISIBLE);
                heart3.setVisibility(View.VISIBLE);
                break;
            case 2:
                heart1.setVisibility(View.INVISIBLE);
                heart2.setVisibility(View.VISIBLE);
                heart3.setVisibility(View.VISIBLE);
                break;
            case 1:
                heart1.setVisibility(View.INVISIBLE);
                heart2.setVisibility(View.INVISIBLE);
                heart3.setVisibility(View.VISIBLE);
                break;
            case 0:
                heart1.setVisibility(View.INVISIBLE);
                heart2.setVisibility(View.INVISIBLE);
                heart3.setVisibility(View.INVISIBLE);
                break;
        }
    }

}
