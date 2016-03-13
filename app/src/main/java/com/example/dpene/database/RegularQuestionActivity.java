package com.example.dpene.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.dpene.database.model.Player;
import com.example.dpene.database.model.RegularQuestion;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_question);

        regQuestionTextView = (TextView) findViewById(R.id.regular_question_text);
        answer1_button = (Button) findViewById(R.id.answer1_button);
        answer2_button = (Button) findViewById(R.id.answer2_button);
        answer3_button = (Button) findViewById(R.id.answer3_button);
        answer4_button = (Button) findViewById(R.id.answer4_button);

        //TODO initialize currentPlayer
       /* regularQuestionDAO = new RegularQuestionDAO(this);
        RegularQuestion regularQuestion = regularQuestionDAO.getRegularQuestion(currentPlayer.getReachedQuestionId());

        regQuestionTextView.setText(regularQuestion.getQuestion());

        ArrayList<String> answers = new ArrayList<String>();
        answers.add(regularQuestion.getRightAnswer());
        String[] wrongAnswers = regularQuestion.getWrongAnswers();
        for(int i = 0; i < wrongAnswers.length; i++){
            answers.add(wrongAnswers[i]);
        }*/
        
        String[] wrongAnswers = {"1", "2", "3", "4"};
        ArrayList<String> answers = new ArrayList<String>();
        for(int i = 0; i < wrongAnswers.length; i++){
            answers.add(wrongAnswers[i]);
        }
        Integer[] cases = {1,2,3,4};
        Collections.shuffle(Arrays.asList(cases));
        for(int i = 0; i <= answers.size(); i++){
            int r = cases[i];
            switch(r){
                case 1: answer1_button.setText(answers.get(i));
                    break;
                case 2: answer2_button.setText(answers.get(i));
                    break;
                case 3: answer3_button.setText(answers.get(i));
                    break;
                case 4: answer4_button.setText(answers.get(i));
                    break;
            }
        }


    }
}
