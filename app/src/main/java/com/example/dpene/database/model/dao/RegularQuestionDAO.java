package com.example.dpene.database.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dpene.database.model.DatabaseHelper;
import com.example.dpene.database.model.RegularQuestion;

/**
 * Created by Golemanovaa on 13.3.2016 г..
 */
public class RegularQuestionDAO implements IRegularQuestionDAO {


    private Context context;
    private DatabaseHelper dh;

    public RegularQuestionDAO(Context context){
        this.context = context;
        this.dh = DatabaseHelper.getInstance(context);
    }

    @Override
    public long addRegularQuestion(RegularQuestion regQuestion) {
        SQLiteDatabase db = dh.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(dh.QUESTION_TEXT, regQuestion.getQuestion());
        values.put(dh.RIGHT_ANS, regQuestion.getRightAnswer());

        String[] wrongAns = regQuestion.getWrongAnswers();
        values.put(dh.WRONG_ANS_1, wrongAns[0]);
        values.put(dh.WRONG_ANS_2, wrongAns[1]);
        values.put(dh.WRONG_ANS_3, wrongAns[2]);

        values.put(dh.NEXT_QUESTION, regQuestion.getNextQuestion());
        values.put(dh.LEVEL_ID, regQuestion.getLevelId());

        long regQuestionId = db.insert(dh.TABLE_QUESTION, null, values);
        db.close();

        regQuestion.setQuestionId(regQuestionId);
        return regQuestionId;
    }

    @Override
    public RegularQuestion getRegularQuestion(long regQuestionId) {
        SQLiteDatabase db = dh.getReadableDatabase();

        //because the player keeps the id of the reached question
        String selectQuery = "SELECT * FROM " + dh.TABLE_QUESTION
                + " WHERE " + dh.UID_QUESTION + " = \"" + regQuestionId + "\"";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        //String question, String rightAnswer, String wrong1, String wrong2, String wrong3, long levelId, long nextQ
        String question = cursor.getString(cursor.getColumnIndex(dh.QUESTION_TEXT));
        String rightAnser = cursor.getString(cursor.getColumnIndex(dh.RIGHT_ANS));
        String wrong1 = cursor.getString(cursor.getColumnIndex(dh.WRONG_ANS_1));
        String wrong2 = cursor.getString(cursor.getColumnIndex(dh.WRONG_ANS_2));
        String wrong3 = cursor.getString(cursor.getColumnIndex(dh.WRONG_ANS_3));
        long levelId = cursor.getLong(cursor.getColumnIndex(dh.LEVEL_ID));
        long nextQuestion = cursor.getLong(cursor.getColumnIndex(dh.NEXT_QUESTION));

        RegularQuestion regularQuestion = new RegularQuestion(question, rightAnser, wrong1, wrong2, wrong3, levelId, nextQuestion);
        db.close();

        return regularQuestion;
    }
}
