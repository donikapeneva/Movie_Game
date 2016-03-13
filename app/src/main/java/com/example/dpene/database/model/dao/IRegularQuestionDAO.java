package com.example.dpene.database.model.dao;

import com.example.dpene.database.model.RegularQuestion;

/**
 * Created by Golemanovaa on 13.3.2016 г..
 */
public interface IRegularQuestionDAO {

    long addRegularQuestion(RegularQuestion regQuestion);
    RegularQuestion getRegularQuestion(long regQuestionId);

}
