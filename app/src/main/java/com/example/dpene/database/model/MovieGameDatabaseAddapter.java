package com.example.dpene.database.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieGameDatabaseAddapter {

    private MovieGameHelper helper;

    MovieGameDatabaseAddapter(Context context) {
        this.helper = new MovieGameHelper(context);
    }


    // creates user, returns negative if something went wrong
    public long createUser(Player player) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieGameHelper.NAME, player.getName());
        // TODO: cript password
        values.put(MovieGameHelper.PASSWORD, player.getPassword());
        // TODO: create method for email
//        values.put(MovieGameHelper.EMAIL, player.getEmail());

        //Git -> Some Changes in Player, Abstract Class Question
//        values.put(MovieGameHelper.LEVEL, player.getLevel().getNumberOfLevel());
        values.put(MovieGameHelper.QUESTION, player.getReachedQuestion());

        long userId = db.insert(MovieGameHelper.TABLE_USER, null, values);
        return userId;
    }


    // creates levels
    public long createLevel(String description, int nextLevel) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieGameHelper.DESCRIPTION, description);
        values.put(MovieGameHelper.NEXT_LEVEL, nextLevel);

        long levelId = db.insert(MovieGameHelper.TABLE_LEVEL, null, values);
        return levelId;
    }


    // creates questions
    public long createRegularQuestions(String question, String rightAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, int nextQuestion, int level) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieGameHelper.QUESTION_TEXT, question);
        values.put(MovieGameHelper.RIGHT_ANS, rightAnswer);
        values.put(MovieGameHelper.WRONG_ANS_1, wrongAnswer1);
        values.put(MovieGameHelper.WRONG_ANS_2, wrongAnswer2);
        values.put(MovieGameHelper.WRONG_ANS_3, wrongAnswer3);
        values.put(MovieGameHelper.NEXT_QUESTION, nextQuestion);
        values.put(MovieGameHelper.LEVEL_ID, level);

        long regQuestionId = db.insert(MovieGameHelper.TABLE_QUESTION, null, values);
        return regQuestionId;
    }

    // Logic questions
    public long createLogicQuestions(String logicQuestion, String logicAnswer) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieGameHelper.LOGIC_QUESTION, logicQuestion);
        values.put(MovieGameHelper.LOGIC_ANSWER, logicAnswer);

        long logicQuestionId = db.insert(MovieGameHelper.TABLE_LOGIC_QUESTION, null, values);
        return logicQuestionId;
    }

    // update user <<<
    public void updateName(String oldName, String newName) {
        //UPDATE <TABLE_NAME> SET Name = 'newName' where Name=? oldNAme
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieGameHelper.NAME, newName);
        String[] whereArgs = {oldName};
        db.update(MovieGameHelper.TABLE_USER, contentValues,
                MovieGameHelper.NAME + " =? ", whereArgs);
    }

    // get user and more options
    // ...

    static class MovieGameHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "MOVIE_GAME_DATABASE";
        private static final int DATABASE_VERSION = 1;

        // tables
        private static final String TABLE_USER = "users";
        private static final String TABLE_LEVEL = "level";
        private static final String TABLE_QUESTION = "question";
        private static final String TABLE_LOGIC_QUESTION = "logic_question";

        // id-s
        private static final String UID_USERNAME = "_id";
        private static final String UID_LEVEL = "level_id";
        private static final String UID_QUESTION = "question_id";
        private static final String UID_LOGIC_QUESTION = "logic_question_id";

        // users table
        private static final String NAME = "username";
        private static final String PASSWORD = "password";
        private static final String EMAIL = "email";
        private static final String LEVEL = "level";
        private static final String QUESTION = "question";

        private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " ("
                + UID_USERNAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT UNIQUE, "
                + PASSWORD + " TEXT, "
                + EMAIL + " TEXT UNIQUE, "
                + LEVEL + " INTEGER, "
                + QUESTION + " INTEGER, "
                + "FOREIGN KEY(" + LEVEL + ") REFERENCES " + TABLE_LEVEL + "(" + UID_LEVEL + "), "
                + "FOREIGN KEY(" + QUESTION + ") REFERENCES " + TABLE_QUESTION + "(" + UID_QUESTION + ")"
                + ");";


        // level table
        private static final String DESCRIPTION = "description";
        private static final String NEXT_LEVEL = "next_level";

        private static final String CREATE_TABLE_LEVEL = "CREATE TABLE " + TABLE_LEVEL + " ("
                + UID_LEVEL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DESCRIPTION + " TEXT, "
                + NEXT_LEVEL + " INTEGER, "
                + "FOREIGN KEY(" + NEXT_LEVEL + ") REFERENCES " + TABLE_LEVEL + "(" + UID_LEVEL + ") "
                + ");";

        // question table
        private static final String NEXT_QUESTION = "next_question";
        private static final String LEVEL_ID = "level_id";
        private static final String QUESTION_TEXT = "question_text";
        private static final String RIGHT_ANS = "right_answer";
        private static final String WRONG_ANS_1 = "wrong_answer_1";
        private static final String WRONG_ANS_2 = "wrong_answer_2";
        private static final String WRONG_ANS_3 = "wrong_answer_3";

        private static final String CREATE_TABLE_QUESTION = "CREATE TABLE " + TABLE_QUESTION + " ("
                + UID_QUESTION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NEXT_QUESTION + " INTEGER, "
                + LEVEL_ID + " INTEGER, "
                + QUESTION_TEXT + " TEXT, "
                + RIGHT_ANS + " TEXT, "
                + WRONG_ANS_1 + " TEXT, "
                + WRONG_ANS_2 + " TEXT, "
                + WRONG_ANS_3 + " TEXT, "
                + "FOREIGN KEY(" + NEXT_QUESTION + ") REFERENCES " + TABLE_QUESTION + "(" + UID_QUESTION + "), "
                + "FOREIGN KEY(" + LEVEL_ID + ") REFERENCES " + TABLE_LEVEL + "(" + UID_LEVEL + ") "
                + ");";

        // logic question table
        private static final String LOGIC_QUESTION = "logic_question";
        private static final String LOGIC_ANSWER = "logic_answer";

        private static final String CREATE_TABLE_LOGIC_QUESTION = "CREATE TABLE " + TABLE_LOGIC_QUESTION + " ("
                + UID_LOGIC_QUESTION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LOGIC_QUESTION + " TEXT, "
                + LOGIC_ANSWER + " TEXT "
                + ");";

        private Context context;

        public MovieGameHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_USER);
                db.execSQL(CREATE_TABLE_LEVEL);
                db.execSQL(CREATE_TABLE_QUESTION);
                db.execSQL(CREATE_TABLE_LOGIC_QUESTION);
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVEL);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIC_QUESTION);

            onCreate(db);
        }
    }

}
