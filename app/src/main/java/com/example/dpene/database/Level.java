package com.example.dpene.database;

import java.util.ArrayList;

public class Level {

    private String name;
    private byte numberOfLevel;
    private ArrayList<RegularQuestion> questions;
    private int counterForQuestions;

    public Level(byte numberOfLevel){
        // nqmame proverki, zashtoto nie gi suzdavame, a ne potrebitelq
        // ako ima proverki, te shte budat level > 0 && level < maxLevel && level == existingLevel
        this.numberOfLevel = numberOfLevel;
        this.name = "Level " + this.numberOfLevel;
        this.questions = new ArrayList<RegularQuestion>();
    }

    public Question askQuestion(Player player){
        Question current = questions.get(player.getReachedQuestion());
        System.out.println(current);
        return current;

    }

    public void setQuestions(RegularQuestion rq){
        this.questions.add(rq);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getQuestionSize() {
        return this.questions.size();
    }

    public int getNumberOfLevel(){
        return this.numberOfLevel;
    }
}
