package com.example.dpene.database.model;

import android.content.Context;

import com.example.dpene.database.model.dao.LogicQuestionDAO;

public class LogicQuestionManager {

    private static LogicQuestionManager instance;
    private LogicQuestionDAO logicQuestionDAO;

    private LogicQuestionManager(Context context) {
        this.logicQuestionDAO = new LogicQuestionDAO(context);
    }

    public static LogicQuestionManager getInstance(Context context) {
        if (instance == null) {
            instance = new LogicQuestionManager(context);
        }
        return instance;
    }

    public LogicQuestion getLogicQuestion() {
        return this.logicQuestionDAO.getLogicQuestion();
    }


}
