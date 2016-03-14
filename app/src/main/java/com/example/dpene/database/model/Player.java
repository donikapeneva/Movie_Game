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

    //TODO see if we need this method and where do we make the validation
    //we can make it with throws exception, and then with error in the activity
    private void setName(String name) {
        try {
            if (this.name == null || this.name.length() < 3 || this.name == "") {
                throw new IncorrectInputException();
            }
        } catch (IncorrectInputException e) {
            if (this.name == null || this.name == "") {
            } else {
            }
            this.setName(name);
        }
    }

    public long getIdOfLevel() {
        return idOfLevel;
    }

    //TODO do we use this method
    public String getName() {
        return this.name;
    }

    //TODO do we use this method
    public String getEmail() {
        return this.email;
    }

    //TODO see if we need this method and where do we make the validation
    //we can make it with throws exception, and then with error in the activity
    private void setPassword() {
        try {
            String pattern = "(?=.*[0-9])(?=.*[a-z]).{5,10}";
            if (this.password == null || !this.password.matches(pattern)) {
                throw new IncorrectInputException();
            }
        } catch (IncorrectInputException e) {
            this.setPassword();
        }
    }


    public String getPassword() {
        return this.password;
    }

    public int getReachedQuestion() {
        return this.reachedQuestion;
    }

    public long getReachedQuestionId(){
        return this.reachedQuestionId;
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

    public void setReachedQuestionId(long nextQuestionId){
        this.reachedQuestionId = nextQuestionId;
    }

    public void setIdOfLevel(long nextLevelId){
        this.idOfLevel = nextLevelId;
    }

    public int getReachedLevel() {
        return (int) idOfLevel;
    }
}
