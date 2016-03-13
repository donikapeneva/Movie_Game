package com.example.dpene.database.model;

import java.util.List;
import java.util.Scanner;

public class Player {


    private static final int WON_LIVES = 2;
    private static final int MAX_LIVES = 2;

    private String name;
    private String password;
    private String email;
    private Level reachedLevel;
    private int indexOfReachedLevel; //TODO change it to long idOfReachedLevel
    private int reachedQuestion; // TODO change it to long idOfReachedQuestion
    private List<Level> levels;
    private int lives;

    //TODO decide on a constructor
    public Player(String email, String username, String password, int reachedLevel, int reachedQuestion) {
        this.email = email;
        this.name = username;
        this.password = password;
        this.indexOfReachedLevel = reachedLevel;
        this.reachedQuestion = reachedQuestion;
    }

    public Player(List<Level> levels) {
        this.setName(name);

        //password between 5 and 10 symbols (must contain letters AND numbers)");
        this.setPassword();

        this.levels = levels;
        this.indexOfReachedLevel = 0;
        this.reachedLevel = levels.get(indexOfReachedLevel);
        this.reachedQuestion = 0;

        this.lives = MAX_LIVES;
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

    public Level getLevel() {
        return this.reachedLevel;
    }

    public int getReachedQuestion() {
        return this.reachedQuestion;
    }

    //TODO where do we use it and change implementation
    private void loseLife() {
        if (this.lives == 0) {
            //answer logic question
        } else {
            this.lives--;
        }
    }

    //TODO do we use that?
    private void goAhead() {
        if (this.reachedQuestion == this.reachedLevel.getQuestionSize() - 1) {
            this.levelUp();
        } else {
            this.reachedQuestion++;
        }
    }

    //TODO how and where do we use that
    private void levelUp() {
        if (this.indexOfReachedLevel == this.levels.size() - 1) {
        } else {
            this.indexOfReachedLevel++;
            this.reachedLevel = this.levels.get(this.indexOfReachedLevel);
            this.reachedQuestion = 0;
        }
    }


    public int getReachedLevel() {
        return indexOfReachedLevel;
    }
}
