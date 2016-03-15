package com.example.dpene.database.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dpene.database.model.DatabaseHelper;
import com.example.dpene.database.model.LogicQuestion;
import com.example.dpene.database.model.LogicQuestionManager;

import java.util.ArrayList;
import java.util.List;

public class LogicQuestionDAO implements ILogicQuestionDAO {

    private static LogicQuestionDAO instance;
    private DatabaseHelper dh;

    private LogicQuestionDAO(Context context) {
        this.dh = DatabaseHelper.getInstance(context);
    }

    public static LogicQuestionDAO getInstance(Context context) {
        if (instance == null) {
            return new LogicQuestionDAO(context);
        }
        return instance;
    }


    public LogicQuestion getLogicQuestion() {

        int min = 1;
        int max = this.getAllLogicQuestions().size() - 1;
        int randomQuestion = min + (int) (Math.random() * ((max - min) + min));

        SQLiteDatabase db = dh.getReadableDatabase();

        String query = "SELECT * FROM " + dh.TABLE_LOGIC_QUESTION
                + " WHERE " + dh.UID_LOGIC_QUESTION + " = \"" + randomQuestion + "\"";


        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

//        String question = cursor.getString(cursor.getColumnIndex(dh.LOGIC_QUESTION));
//        String answer = cursor.getString(cursor.getColumnIndex(dh.LOGIC_ANSWER));

        String question = "What do you want from meee??";
        String answer = "...";

        LogicQuestion logicQuestion = new LogicQuestion(question, answer);

        Log.e("PLS", logicQuestion.getQuestion() + "");

        db.close();
        return logicQuestion;
    }

    @Override
    public long addRegularQuestion(LogicQuestion regQuestion) {

        SQLiteDatabase db = dh.getWritableDatabase();

        for (LogicQuestion q : LogicQuestionManager.questions) {
            ContentValues values = new ContentValues();

            values.put(dh.LOGIC_QUESTION, regQuestion.getQuestion());
            values.put(dh.RIGHT_ANS, regQuestion.getRightAnswer());

            String[] wrongAns = regQuestion.getWrongAnswers();
            values.put(dh.WRONG_ANS_1, wrongAns[0]);
            values.put(dh.WRONG_ANS_2, wrongAns[1]);
            values.put(dh.WRONG_ANS_3, wrongAns[2]);

            values.put(dh.NEXT_QUESTION, regQuestion.getNextQuestion());
            values.put(dh.LEVEL_ID, regQuestion.getLevelId());

            long regQuestionId = db.insert(dh.TABLE_QUESTION, null, values);
        }
        db.close();

        regQuestion.setQuestionId(regQuestionId);
        return regQuestionId;

    }

    public List<LogicQuestion> getAllLogicQuestions() {
        ArrayList<LogicQuestion> listOfQuestions = new ArrayList<>();
        String query = "SELECT * FROM " + dh.TABLE_LOGIC_QUESTION;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                String question = c.getString(c.getColumnIndex(dh.LOGIC_QUESTION));
                String answer = c.getString(c.getColumnIndex(dh.LOGIC_ANSWER));

                LogicQuestion logicQuestion = new LogicQuestion(question, answer);
                listOfQuestions.add(logicQuestion);
            } while (c.moveToNext());
        }

        db.close();
        return listOfQuestions;
    }

}
