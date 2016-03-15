package com.example.dpene.database.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

        String question = cursor.getString(cursor.getColumnIndex(dh.LOGIC_QUESTION));
        String answer = cursor.getString(cursor.getColumnIndex(dh.LOGIC_ANSWER));

//        String question = "What do you want from meee??";
//        String answer = "...";

        LogicQuestion logicQuestion = new LogicQuestion(question, answer);

        db.close();
        return logicQuestion;
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
