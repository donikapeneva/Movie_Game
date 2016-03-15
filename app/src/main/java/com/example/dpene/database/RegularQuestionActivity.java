package com.example.dpene.database;

import android.content.Intent;
import android.os.AsyncTask;
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

import java.util.ArrayList;
import java.util.Collections;

public class RegularQuestionActivity extends AppCompatActivity implements View.OnClickListener {

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
    Button clicked;

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

        ShowQuestionTask sqt = new ShowQuestionTask(false);
        sqt.execute();

        showHearts();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegularQuestionActivity.this, MapActivity.class));
    }

    @Override
    public void onClick(View view) {
       clicked = (Button) view;
        if (clicked.getText().toString().equals(rightAnswer)) {
            //CHANGE THE COLOR OF THE BUTTON TO GREEN (for right answer)
            clicked.setBackgroundResource(R.color.rightAnswer);

            //UPDATE REACHED QUESTION OF THE PLAYER
            Integer nextQuestionId = this.regularQuestion.getNextQuestion();
            playerManager.setReachedQuestionId(nextQuestionId);

            if(nextQuestionId == 0){
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
                final int reachedQuestionLevel = this.regularQuestion.getLevelId();
                if (playerManager.goToNextLevel(reachedQuestionLevel)) {
                    //PLAYER GOES TO NEXT LEVEL
                    playerManager.setIdOfLevel(reachedQuestionLevel);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                Intent nextActivity = null;
                                switch(reachedQuestionLevel){
                                    case 2: nextActivity = new Intent(RegularQuestionActivity.this, GoToSecondLevelActivity.class);
                                        break;
                                    case 3: nextActivity = new Intent(RegularQuestionActivity.this, GoToThirdLevelActivity.class);
                                        break;
                                }
                                startActivity(nextActivity);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    //SHOW THE NEXT QUESTION
                    showNextQuestion();
                }
            }
        } else {
            //CHANGE THE COLOR OF THE BUTTON RED (wrong answer)
            clicked.setBackgroundResource(R.color.wrongAnswer);

            //UPDATE REACHED QUESTION OF THE PLAYER
            Integer nextQuestionId = this.regularQuestion.getNextQuestion();
            playerManager.setReachedQuestionId(nextQuestionId);

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
                showHearts();
                showNextQuestion();
            }
        }

    }

    public void showNextQuestion(){
        this.regularQuestion =  RegularQuestionManager.getInstance().getRegularQuestion(this, playerManager.getReachedQuestionId());
        ShowQuestionTask task = new ShowQuestionTask(true);
        task.execute();
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

    public class ShowQuestionTask extends AsyncTask<Void, Void, Void> {

        ArrayList<String> answers;
        boolean changeColor;

        public ShowQuestionTask(boolean changeColor){
           this.changeColor = changeColor;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(1000);

                rightAnswer = regularQuestion.getRightAnswer();

                this.answers = new ArrayList<String>();
                answers.add(rightAnswer);
                String[] wrongAnswers = regularQuestion.getWrongAnswers();
                for (String ans : wrongAnswers) {
                    answers.add(ans);
                }

                Collections.shuffle(answers);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(changeColor)
                clicked.setBackgroundResource(R.color.regularQButton);
            
            regQuestionTextView.setText(regularQuestion.getQuestion());

            int firstAns = 0;
            answer1_button.setText(answers.get(firstAns++));
            answer2_button.setText(answers.get(firstAns++));
            answer3_button.setText(answers.get(firstAns++));
            answer4_button.setText(answers.get(firstAns));
        }
    }

}
