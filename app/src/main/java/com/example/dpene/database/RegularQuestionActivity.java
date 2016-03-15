package com.example.dpene.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private RegularQuestionDAO regularQuestionDAO; // TODO tova ne trqbva da e tuk
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

       // showHearts();

        this.playerManager = PlayerManager.getInstance(this);

        this.regularQuestion = RegularQuestionManager.getInstance().getRegularQuestion(this, playerManager.getReachedQuestionId());

        regQuestionTextView.setText(regularQuestion.getQuestion());

        this.rightAnswer = regularQuestion.getRightAnswer();

        ArrayList<String> answers = new ArrayList<String>();
        answers.add(rightAnswer);
        String[] wrongAnswers = regularQuestion.getWrongAnswers();
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
    public void onClick(View view) {
        Button clicked = (Button) view;
        if (clicked.getText().toString().equals(rightAnswer)) {
            //change the color of the button to show the player it was the right answer
            clicked.setBackgroundResource(R.color.rightAnswer);

            Integer nextQuestionId = this.regularQuestion.getNextQuestion();
            playerManager.setReachedQuestionId(nextQuestionId);

            if(nextQuestionId == null){
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
                    playerManager.setIdOfLevel(reachedQuestionLevel);
                    //TODO show player that he goes a level up
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                Intent nextActivity = new Intent(RegularQuestionActivity.this, MapActivity.class);
                                startActivity(nextActivity);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                Intent nextQuestion = new Intent(RegularQuestionActivity.this, RegularQuestionActivity.class);
                                startActivity(nextQuestion);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        } else {
            //red color for wrong answer
            clicked.setBackgroundResource(R.color.wrongAnswer);

            boolean goToLogicalQuestion = playerManager.loseLifeAndGoToLogicQuestion();
            if(goToLogicalQuestion){
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
            //TODO vremeto kum map-a ne trqbva da vaji tuk D:
            // kogato se startira minava vremeto za answerite i se vrushta na map-a

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            LoseLifeFragment loseLife = new LoseLifeFragment();
                            loseLife.show(getSupportFragmentManager(), "loseLifeDialog");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }

    }

    @Override
    public void communicate() {
        // TODO playerManager.loseLife();

       // this.showHearts();
    }


  /*  private void showHearts(){
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
    */
}
