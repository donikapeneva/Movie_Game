package com.example.dpene.database.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dpene.database.model.DatabaseHelper;
import com.example.dpene.database.model.RegularQuestion;
import com.example.dpene.database.model.RegularQuestionManager;

/**
 * Created by Golemanovaa on 13.3.2016 г..
 */
public class RegularQuestionDAO implements IRegularQuestionDAO {

    private static RegularQuestionDAO instanceRegQDAO= null;
    private Context context;
    private DatabaseHelper dh;

    private RegularQuestionDAO(Context context){
        this.context = context;
        this.dh = DatabaseHelper.getInstance(context);
    }

    public static RegularQuestionDAO getInstance(Context context){
        if(instanceRegQDAO == null) {
            instanceRegQDAO = new RegularQuestionDAO(context);
        }
        return instanceRegQDAO;
    }

    @Override
    public long addRegularQuestions() {
        SQLiteDatabase db = dh.getWritableDatabase();
        long regQuestionId = -1;

        for(RegularQuestion rq : RegularQuestionManager.questions){
            ContentValues values = new ContentValues();

            values.put(dh.QUESTION_TEXT, rq.getQuestion());
            values.put(dh.RIGHT_ANS, rq.getRightAnswer());

            String[] wrongAns = rq.getWrongAnswers();
            values.put(dh.WRONG_ANS_1, wrongAns[0]);
            values.put(dh.WRONG_ANS_2, wrongAns[1]);
            values.put(dh.WRONG_ANS_3, wrongAns[2]);

            values.put(dh.NEXT_QUESTION, rq.getNextQuestion());
            values.put(dh.LEVEL_ID, rq.getLevelId());

            regQuestionId = db.insert(dh.TABLE_QUESTION, null, values);
            rq.setQuestionId((int) regQuestionId);
        }
        db.close();

        return regQuestionId;
    }

    @Override
    public RegularQuestion getRegularQuestion(int regQuestionId) {
        SQLiteDatabase db = dh.getReadableDatabase();

        //because the player keeps the id of the reached question
        String selectQuery = "SELECT * FROM " + dh.TABLE_QUESTION
                + " WHERE " + dh.UID_QUESTION + " = \"" + regQuestionId + "\"";

        RegularQuestion regularQuestion = null;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) { //returns false if the Cursor is empty
            String question = cursor.getString(cursor.getColumnIndex(dh.QUESTION_TEXT));
            String rightAnswer = cursor.getString(cursor.getColumnIndex(dh.RIGHT_ANS));
            String wrong1 = cursor.getString(cursor.getColumnIndex(dh.WRONG_ANS_1));
            String wrong2 = cursor.getString(cursor.getColumnIndex(dh.WRONG_ANS_2));
            String wrong3 = cursor.getString(cursor.getColumnIndex(dh.WRONG_ANS_3));
            int levelId = cursor.getInt(cursor.getColumnIndex(dh.LEVEL_ID));
            int nextQuestion = cursor.getInt(cursor.getColumnIndex(dh.NEXT_QUESTION));
            regularQuestion = new RegularQuestion(question, rightAnswer, wrong1, wrong2, wrong3, levelId, nextQuestion);
        }

        cursor.close();
        db.close();

        return regularQuestion; // this will be null if the cursor is empty
    }
}
