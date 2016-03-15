package com.example.dpene.database.model;

import android.content.Context;

import com.example.dpene.database.model.dao.LogicQuestionDAO;

import java.util.HashSet;

public class LogicQuestionManager {

    public static HashSet<LogicQuestion> questions = new HashSet<>();
    static{
        questions.add(new LogicQuestion("Are you stupid?", "yes"));
        questions.add(new LogicQuestion("Are you stupid_2", "yes_2"));
        questions.add(new LogicQuestion("Are you stupid_3", "yes_3"));
        questions.add(new LogicQuestion("WTF?", "wtf_1"));
        questions.add(new LogicQuestion("WTF2?", "wtf_2"));
        questions.add(new LogicQuestion("WTF3?", "wtf_3"));
        questions.add(new LogicQuestion("M?", "m_1"));
        questions.add(new LogicQuestion("M2?", "m_2"));
        questions.add(new LogicQuestion("M3?", "m_3"));
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
