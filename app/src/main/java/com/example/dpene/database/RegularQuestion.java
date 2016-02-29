package com.example.dpene.database;

public class RegularQuestion extends Question {

    private String[] otherAnswers;

    public RegularQuestion(String question, String rightAnswer, String other1, String other2){
        super(question, rightAnswer);
        this.otherAnswers = new String[2];
        this.otherAnswers[0] = other1;
        this.otherAnswers[1] = other2;
    }

    @Override
    public String toString() {
        System.out.println(super.toString());
        // otgovorite shte se izkarvat razburkani, no za demoto ne sme go realizirali
        return "\t1: " + this.otherAnswers[0] + "\n\t2: " + this.otherAnswers[1] + "\n\t3: " + super.getRightAnswer();

    }

}
