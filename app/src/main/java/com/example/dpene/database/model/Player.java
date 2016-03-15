package com.example.dpene.database.model;

public class Player {

    private static final int WON_LIVES = 2;

    private String name;
    private String password;
    private String email;
    private int idOfLevel;
    private int reachedQuestionId;
    private int lives;

    public Player(String email, String username, String password) {
        this.email = email;
        this.name = username;
        this.password = password;
    }

    public Player(String email, String username, String password, int reachedLevel, int reachedQuestionId, int lives) {
        this(email, username, password);
        this.idOfLevel = reachedLevel;
        this.reachedQuestionId = reachedQuestionId;
        this.lives = lives;
    }

    public void setReachedQuestionId(int nextQuestionId){
        this.reachedQuestionId = nextQuestionId;
    }

    public void setIdOfLevel(int nextLevelId){
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
    public int getIdOfLevel() {
        return idOfLevel;
    }

    public int getReachedQuestionId(){
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
        if(levelId != this.idOfLevel){
            return true;
        } else {
            return false;
        }
    }

    public int getLives() {
        return this.lives;
    }
}
