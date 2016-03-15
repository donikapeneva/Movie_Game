package com.example.dpene.database.model;

public class RegularQuestion extends Question {

    private String[] wrongAnswers;
    private int levelId;
    private Integer nextQuestion;
    private int questionId; //it could be needed, if not we'll remove it

    public RegularQuestion(String question, String rightAnswer, String wrong1, String wrong2, String wrong3, int levelId, Integer nextQuestion){
        super(question, rightAnswer);
        this.wrongAnswers = new String[3];
        this.wrongAnswers[0] = wrong1;
        this.wrongAnswers[1] = wrong2;
        this.wrongAnswers[2] = wrong3;
        this.levelId = levelId;
        this.nextQuestion = nextQuestion;
    }

    public String[] getWrongAnswers(){
        return this.wrongAnswers;
    }

    public int getLevelId(){
        return this.levelId;
    }

    public Integer getNextQuestion(){
        return nextQuestion;
    }

    public void setQuestionId(int id){
        this.questionId = id;
    }
}
