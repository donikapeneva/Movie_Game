package com.example.dpene.database.model;

import java.util.HashSet;

/**
 * Created by dpene on 3/15/2016.
 */
public class RegularQuestionManager {

    public static HashSet<RegularQuestion> questions;
    private static RegularQuestionManager ourInstance = new RegularQuestionManager();

    public static RegularQuestionManager getInstance() {
        return ourInstance;
    }

    private RegularQuestionManager() {
        //RegularQuestion q1 = new RegularQuestion();
        //15 times for all static questions
        //questions.add(q1);
    }
}
