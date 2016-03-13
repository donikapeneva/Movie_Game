package com.example.dpene.database.model.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dpene.database.model.DatabaseHelper;
import com.example.dpene.database.model.LogicQuestion;

import java.util.ArrayList;
import java.util.List;

public class LogicQuestionDAO {

    private DatabaseHelper dh;

    public LogicQuestionDAO(Context context) {
        this.dh = DatabaseHelper.getInstance(context);
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

    public LogicQuestion getLogicQuestion(){

        int min = 1;
        int max = this.getAllLogicQuestions().size() - 1;
        int randomQuestion = min + (int)(Math.random() * ((max - min) + min));

        String query = "SELECT * FROM " + dh.TABLE_LOGIC_QUESTION
                + " WHERE " + dh.UID_LOGIC_QUESTION + " = \"" + randomQuestion + "\"";

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        String question = cursor.getString(cursor.getColumnIndex(dh.LOGIC_QUESTION));
        String answer = cursor.getString(cursor.getColumnIndex(dh.LOGIC_ANSWER));

        LogicQuestion logicQuestion = new LogicQuestion(question, answer);
        db.close();
        return logicQuestion;
    }

}
