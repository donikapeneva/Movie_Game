package com.example.dpene.database.model;

public class LogicQuestion extends Question{

    private int logicQuestionId;

    public LogicQuestion(String question, String rightAnswer){
        super(question, rightAnswer);
    }

    public void setLogicQuestionId(int logicQuestionId) {
        this.logicQuestionId = logicQuestionId;
    }
}
