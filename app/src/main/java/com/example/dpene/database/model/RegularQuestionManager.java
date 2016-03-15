package com.example.dpene.database.model;

import android.content.Context;

import com.example.dpene.database.model.dao.RegularQuestionDAO;

import java.util.HashSet;

/**
 * Created by dpene on 3/15/2016.
 */
public class RegularQuestionManager {

    public static HashSet<RegularQuestion> questions = new HashSet<>();
    static{
        RegularQuestion q1 = new RegularQuestion("What are Harry's friend's names?", "Ron & Hermonie", "Jack & Kate",
                "Crab & Goil", "Ross & Rachel", 1, 2);
        questions.add(q1);

        RegularQuestion q2 = new RegularQuestion("Who gave Harry his scar?", "Voldemort", "Malfoy",
                "Dudley", "The Whomping Willow", 1, 3);
        questions.add(q2);

        RegularQuestion q3 = new RegularQuestion("What house is Harry in?", "Gryffindor", "Slytherin",
                "Ravenclaw", "Hufflepuff", 1, 4);
        questions.add(q3);

        RegularQuestion q4 = new RegularQuestion("Where do wizards buy their things?", "Diagon Ally", "Wizarding Lane",
                "Mystic Road", "Magic Street", 1, 5);
        questions.add(q4);

        RegularQuestion q5 = new RegularQuestion("Who bought Hedwig for Harry?", "Rubeus Hagrid", "Professor McGonagall",
                "Vernon Dursley", "Professor Dubledore", 1, 6);
        questions.add(q5);

        RegularQuestion q6 = new RegularQuestion("How many ae the Harry Potter movies?", "8", "7", "5", "6", 1, 7);
        questions.add(q6);

        RegularQuestion q7 = new RegularQuestion("What is Voldemort's real name?", "Tom Riddle", "Albus Dubledore",
                "Lucius Malfoy", "Peter Parker", 1, 8);
        questions.add(q7);

        RegularQuestion q8 = new RegularQuestion("How many horcruxes has Voldemort created?", "7", "3", "6", "5", 1, 9);
        questions.add(q8);

        RegularQuestion q9 = new RegularQuestion("What is Voldemort's real name?", "Tom Riddle", "Albus Dubledore",
                "Lucius Malfoy", "Peter Parker", 1, 10);
        questions.add(q9);

        RegularQuestion q10 = new RegularQuestion("What is the name of Harry's uncle?", "Sirius Black", "Dudley Dursley",
                "James Potter", "Uncle Ben", 1, 11);
        questions.add(q10);

        RegularQuestion q11 = new RegularQuestion("What is Azkaban?", "Prison", "Shop",
                "One of Hagrid'weird creatures", "Wizarding School", 1, 12);
        questions.add(q11);

        RegularQuestion q12 = new RegularQuestion("What is the name of the main character in Prison Break?", "Michael Scofield", "Brad Bellick",
                "Veronica Donavan", "John Smith", 2, 13);
        questions.add(q12);

        RegularQuestion q13 = new RegularQuestion("For what reason did Michael go to prison?", "To free his brother", "He killed two people",
                "He was wrongly accused of murder", "He wworked there", 2, 14);
        questions.add(q13);

        RegularQuestion q14 = new RegularQuestion("What is the naame of Michael's brother?", "Lincoln", "James",
                "Robert", "Brad", 2, 15);
        questions.add(q14);

        RegularQuestion q15 = new RegularQuestion("WHow many seasons has Prison Break?", "4", "6", "5", "3", 2, null);
        questions.add(q15);
    }
    private static RegularQuestionManager ourInstance = null;

    public static RegularQuestionManager getInstance() {
        if(ourInstance == null){
            ourInstance = new RegularQuestionManager();
        }
        return ourInstance;
    }

    private RegularQuestionManager() {

    }

    public RegularQuestion getRegularQuestion(Context context, int id){
       return RegularQuestionDAO.getInstance(context).getRegularQuestion(id);
    }
}
