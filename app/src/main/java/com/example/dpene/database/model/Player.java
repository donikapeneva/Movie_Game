package com.example.dpene.database.model;

import com.example.dpene.database.model.dao.RegularQuestionDAO;

import java.util.List;

public class Player {

    private static final int WON_LIVES = 2;
    private static final int MAX_LIVES = 3;

    private String name;
    private String password;
    private String email;

    // reachedQ i reachedL defaultno = 1
    private long idOfLevel;
    private int reachedQuestion;
    private long reachedQuestionId;
    private int lives;

    public Player(String email, String username, String password) {
        this.email = email;
        this.name = username;
        this.password = password;
        this.lives = MAX_LIVES;
    }

    public Player(String email, String username, String password, int reachedLevel, int reachedQuestion) {
        this(email, username, password);
        this.idOfLevel = reachedLevel;
        this.reachedQuestion = reachedQuestion;
    }

    public void setReachedQuestionId(long nextQuestionId){
        this.reachedQuestionId = nextQuestionId;
    }

    public void setIdOfLevel(long nextLevelId){
        this.idOfLevel = nextLevelId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
    public long getIdOfLevel() {
        return idOfLevel;
    }

    public int getReachedQuestion() {
        return this.reachedQuestion;
    }

    public long getReachedQuestionId(){
        return this.reachedQuestionId;
    }

    public int getReachedLevel() {
        return (int) idOfLevel;
    }

    public boolean loseLifeAndGoToLogicQuestion() {
        if (this.lives == 0) {
            return true;
        } else {
            this.lives--;
            return false;
        }
    }

    public void winLives(){
        this.lives = WON_LIVES;
    }

    public boolean goToNextLevel(long levelId) {
        if(levelId != idOfLevel){
            return true;
        } else {
            return false;
        }
    }

    public int getLives() {
        return lives;
    }
}
