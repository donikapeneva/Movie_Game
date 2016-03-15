package com.example.dpene.database.model;

import android.content.Context;

import com.example.dpene.database.model.dao.LogicQuestionDAO;

import java.util.HashSet;

public class LogicQuestionManager {

    public static HashSet<LogicQuestion> questions = new HashSet<>();
    static{
        questions.add(new LogicQuestion("Kevin's parents had 5 sons total. Their names are Bab, Beb, Bib and Bob. What was the fifth child's name?", "Kevin"));
        questions.add(new LogicQuestion("On average how many birthdays does a man have?", "1"));
        questions.add(new LogicQuestion("If yoy write all the numbers from 300 to 400 how many times you have to write the number 3", "110"));
        
    }
    private static LogicQuestionManager instance;
    private LogicQuestionDAO logicQuestionDAO;
    private LogicQuestion currentQuestion;

    private LogicQuestionManager(Context context) {
        this.logicQuestionDAO = LogicQuestionDAO.getInstance(context);

    }

    public static LogicQuestionManager getInstance(Context context) {
        if (instance == null) {
            instance = new LogicQuestionManager(context);
        }
        return instance;
    }

    private void chooseQuestion(){
        currentQuestion = this.logicQuestionDAO.getLogicQuestion();
    }

    public String getQuestion() {
        chooseQuestion();
        return this.currentQuestion.getQuestion();
    }

    public String getAnswer(){
        return this.currentQuestion.getRightAnswer();
    }


}
