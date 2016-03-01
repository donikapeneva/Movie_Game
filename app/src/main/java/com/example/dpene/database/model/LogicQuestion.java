package com.example.dpene.database.model;

import java.util.ArrayList;

public class LogicQuestion extends Question{
    static ArrayList<LogicQuestion> LOGIC_QUESTIONS = new ArrayList<LogicQuestion>();
    static int INDEX_OF_QUESTION_TO_ASK = 0;

    public LogicQuestion(String question, String rightAnswer){
        super(question, rightAnswer);
    }

    public static void createLogicalQuestons() {

        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 1?", "1"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 2?", "2"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 3?", "3"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 4?", "4"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 5?", "5"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 6?", "6"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 7?", "7"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 8?", "8"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 9?", "9"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 10?", "10"));
        LOGIC_QUESTIONS.add(new LogicQuestion("Logic 11?", "11"));

        System.out.println("Logical Questions created.");
    }

}
