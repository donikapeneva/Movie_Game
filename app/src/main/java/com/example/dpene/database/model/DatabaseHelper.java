package com.example.dpene.database.model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    private static final String DATABASE_NAME = "MOVIE_GAME_DATABASE";
    private static final int DATABASE_VERSION = 6;

    // tables
    public static final String TABLE_PLAYER = "player";
    public static final String TABLE_LEVEL = "level";
    public static final String TABLE_QUESTION = "question";
    public static final String TABLE_LOGIC_QUESTION = "logic_question";

    // id-s
    public static final String UID_PLAYER = "_id";
    public static final String UID_LEVEL = "level_id";
    public static final String UID_QUESTION = "question_id";
    public static final String UID_LOGIC_QUESTION = "logic_question_id";

    // players table
    public static final String NAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PL_LEVEL = "level";
    public static final String PL_QUESTION = "question";

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_PLAYER + " ("
            + UID_PLAYER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT UNIQUE, "
            + PASSWORD + " TEXT, "
            + EMAIL + " TEXT UNIQUE, "
            + PL_LEVEL + " INTEGER DEFAULT 1, "
            + PL_QUESTION + " INTEGER DEFAULT 1, "
            + "FOREIGN KEY(" + PL_LEVEL + ") REFERENCES " + TABLE_LEVEL + "(" + UID_LEVEL + "), "
            + "FOREIGN KEY(" + PL_QUESTION + ") REFERENCES " + TABLE_QUESTION + "(" + UID_QUESTION + ")"
            + ");";


    // level table
    private static final String LEVEL_DESCRIPTION = "description";
    private static final String NEXT_LEVEL = "next_level";

    private static final String CREATE_TABLE_LEVEL = "CREATE TABLE " + TABLE_LEVEL + " ("
            + UID_LEVEL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LEVEL_DESCRIPTION + " TEXT, "
            + NEXT_LEVEL + " INTEGER, "
            + "FOREIGN KEY(" + NEXT_LEVEL + ") REFERENCES " + TABLE_LEVEL + "(" + UID_LEVEL + ") "
            + ");";

    // question table
    public static final String NEXT_QUESTION = "next_question";
    public static final String LEVEL_ID = "level_id";
    public static final String QUESTION_TEXT = "question_text";
    public static final String RIGHT_ANS = "right_answer";
    public static final String WRONG_ANS_1 = "wrong_answer_1";
    public static final String WRONG_ANS_2 = "wrong_answer_2";
    public static final String WRONG_ANS_3 = "wrong_answer_3";

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
    public static final String LOGIC_QUESTION = "logic_question";
    public static final String LOGIC_ANSWER = "logic_answer";

    private static final String CREATE_TABLE_LOGIC_QUESTION = "CREATE TABLE " + TABLE_LOGIC_QUESTION + " ("
            + UID_LOGIC_QUESTION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LOGIC_QUESTION + " TEXT, "
            + LOGIC_ANSWER + " TEXT "
            + ");";


    private static final String INSERT_INTO_QUESTION = "INSERT INTO " + TABLE_QUESTION
            + " (" + NEXT_QUESTION + ", " + LEVEL_ID + ", " + QUESTION_TEXT + ", " + RIGHT_ANS + ", "
            + WRONG_ANS_1 + ", " + WRONG_ANS_2 + ", " + WRONG_ANS_3 + ") VALUES" +
            "  (2, 1, What are Harry's friend's names?, Ron & Hermonie, Jack & Kate, Crab & Goil, Ross & Rachel)," +
            "  (3, 1, Who gave Harry his scar?, Voldemort, Malfoy, Dudley, The Whomping Willow)," +
            "  (4, 1, What house is Harry in?, Gryffindor, Slytherin, Ravenclaw, Hufflepuff)," +
            "  (5, 1, Where do wizards buy their things?, Diagon Ally, Wizarding Lane, Magic Street, Mystic Road)," +
            "  (6, 1, Who bought Hedwig for Harry?, Rubeus Hagrid, Professor McGonagall, Vernon Dursley, Professor Dubledore)," +
            "  (7, 1, How many ae the Harry Potter movies?, 8, 7, 5, 6)," +
            "  (8, 1, What is Voldemort's real name?, Tom Riddle, Albus Dumbledore, Lucius Malfoy, Peter Parker)," +
            "  (9, 1, How many horcruxes has Voldemort created?, 7, 3, 6, 5)," +
            "  (10, 1, What is the naame of Harry's uncle?, Sirius Black, Dudley Dursley, James Potter, Uncle Ben)," +
            "  (11, 1, What is Azkaban?, Prison, Shop, One of Hagrid'weird creatures, Wizarding School)," +
            "  (12, 2, What is the name of the main character in Prison Break?, Michael Scofield, Brad Bellick, Veronica Donavan, John Smith)," +
            "  (13, 2, For what reason did Michael go to prison?, To free his brother, He killed two people, He was wrongly accused of murder, He wworked there)," +
            "  (14, 2, What is the naame of Michael's brother?, Lincoln, Jaes, Robert, Brad)," +
            "  (1, 2, How many seasons has Prison Break?, 4, 6, 5, 3);";
    //long cannot be null, so the last question will point to the first question

    private static final String INSERT_INTO_LOGIC_QUESTION = "INSERT INTO " + TABLE_LOGIC_QUESTION
            + " (" + LOGIC_QUESTION + ", " + LOGIC_ANSWER  + ") VALUES" +
            "  (Are you stupid?, yes)," +
            "  (Are you stupid_2?, yes_2)," +
            "  (Are you stupid_3?, yes_3)," +
            "  (WTF?, wtf_1)," +
            "  (WTF?, wtf_2)," +
            "  (WTF?, wtf_3)," +
            "  (M?, m_1)," +
            "  (M?, m_2)," +
            "  (M?, m_3);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context){
        if(instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_LEVEL);
            db.execSQL(CREATE_TABLE_QUESTION);
            db.execSQL(CREATE_TABLE_LOGIC_QUESTION);
            db.execSQL(INSERT_INTO_QUESTION);
            db.execSQL(INSERT_INTO_LOGIC_QUESTION);
        } catch (SQLException e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIC_QUESTION);

        onCreate(db);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
