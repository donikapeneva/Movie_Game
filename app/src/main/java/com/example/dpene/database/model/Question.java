package com.example.dpene.database.model;

public class Question {
    private String question;
    private String rightAnswer;

    public Question(String question, String rightAnswer){
        this.question =  question;
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return this.question;
    }

    public String getRightAnswer(){
        return this.rightAnswer;
    }
}