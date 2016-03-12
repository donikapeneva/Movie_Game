package com.example.dpene.database.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dpene.database.model.DatabaseHelper;
import com.example.dpene.database.model.Player;

import java.util.List;

public class UserDAO implements IUserDAO {

    private DatabaseHelper dh;
    private Context context;

    public UserDAO(Context context){
        this.context = context;
        this.dh = DatabaseHelper.getInstance(context);
    }


    @Override
    public long addUser(Player user) {

        SQLiteDatabase db = dh.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(dh.NAME, user.getName());
        values.put(dh.PASSWORD, user.getEmail());
        values.put(dh.EMAIL, user.getPassword());
        //TODO maybe = 0 ?
        values.put(dh.LEVEL, user.getLevel().getNumberOfLevel());
        values.put(dh.QUESTION, user.getReachedQuestion());

        long userId = db.insert(dh.TABLE_USER, null, values);
        db.close();
        return userId;


    }

    @Override
    public Player getUser(String username) {
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dh.TABLE_USER
                + "WHERE " + dh.NAME + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex(dh.NAME));
        String password = cursor.getString(cursor.getColumnIndex(dh.PASSWORD));
        String email = cursor.getString(cursor.getColumnIndex(dh.EMAIL));
        int level = cursor.getInt(cursor.getColumnIndex(dh.LEVEL));
        int question = cursor.getInt(cursor.getColumnIndex(dh.QUESTION));

        Player player = new Player(email, name, password, level, question);
        db.close();
        return player;
    }

    @Override
    public Player getUser(long id) {
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dh.TABLE_USER
                + " WHERE " + dh.UID_USERNAME + " = \"" + id + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        String username = c.getString(c.getColumnIndex(dh.NAME));
        String password = c.getString(c.getColumnIndex(dh.PASSWORD));
        String email = c.getString(c.getColumnIndex(dh.EMAIL));
        int level = c.getInt(c.getColumnIndex(dh.LEVEL));
        int question = c.getInt(c.getColumnIndex(dh.QUESTION));


        Player player = new Player(email, username, password, level, question);
        db.close();
        return player;
    }

    @Override
    public boolean checkUsername(String username) {
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dh.TABLE_USER
                + " WHERE " + dh.NAME + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor != null && cursor.moveToFirst()) {
            db.close();
            return true;
        }
        else{
            db.close();
            return false;
        }
    }

    @Override
    public boolean checkUserEmail(String email) {
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dh.TABLE_USER
                + " WHERE " + dh.EMAIL + " = \"" + email + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            db.close();
            return true;
        }
        else{
            db.close();
            return false;
        }
    }

    @Override
    public Player checkLogin(String username, String password) {
        SQLiteDatabase db = dh.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + dh.TABLE_USER
                + " WHERE " + dh.EMAIL + " = \"" + username
                + "\" AND " + dh.PASSWORD + " = \"" + password + "\"";

        Cursor c = db.rawQuery(selectQuery, null);


        Player user = null;

        if (c.moveToFirst()) {
            long id = c.getLong(c.getColumnIndex(dh.UID_USER));
            String uname = c.getString(c.getColumnIndex(dh.NAME));
            String upassword = c.getString(c.getColumnIndex(dh.PASSWORD));
            String uemail = c.getString(c.getColumnIndex(dh.EMAIL));
            int ulevel = c.getInt(c.getColumnIndex(dh.LEVEL));
            int uquestion = c.getInt(c.getColumnIndex(dh.QUESTION));

            user = new Player(uemail, upassword, uname, ulevel, uquestion);
            user.setUserId(id);
        }

        db.close();
        return user;
    }
}
