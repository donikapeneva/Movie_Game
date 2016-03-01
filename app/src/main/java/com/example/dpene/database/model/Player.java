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
    private int indexOfReachedLevel;
    private int reachedQuestion;
    private List<Level> levels;
    private int lives;


    public Player(List<Level> levels) {
        System.out.println("Enter a name:");
        this.setName();

        System.out.println("Enter a password between 5 and 10 symbols \n(must contain letters AND numbers)");
        this.setPassword();

        System.out.println("Let's play a game...");
        this.levels = levels;
        this.indexOfReachedLevel = 0;
        this.reachedLevel = levels.get(indexOfReachedLevel);
        this.reachedQuestion = 0;

        this.lives = MAX_LIVES;
        System.out.println("You have " + MAX_LIVES + " lives.");
    }

    // NO SCANNER in classes
    private void setName() {
        Scanner sc = new Scanner(System.in);
        try {
            this.name = sc.next();
            if (this.name == null || this.name.length() < 3 || this.name == "") {
                throw new IncorrectInputException();
            }
        } catch (IncorrectInputException e) {
            if (this.name == null || this.name == "") {
                System.out.println("Name cannot be blank!");
            } else {
                System.out.println("Name cannot be less than 3 symbols!");
            }
            this.setName();
        }
    }

    private void setEmail() {

    }

    public String getName(){
        return this.name;
    }

    public String getEmail() { return  this.email; }

    private void setPassword() {
        this.createPassword();
        this.repeatPassword();
    }

    public String getPassword(){
        return this.password;
    }

    private void createPassword() {
        Scanner sc = new Scanner(System.in);
        try {
            this.password = sc.next();
            String pattern = "(?=.*[0-9])(?=.*[a-z]).{5,10}";
            if (this.password == null || !this.password.matches(pattern)) {
                throw new IncorrectInputException();
            }
        } catch (IncorrectInputException e) {
            System.out.println("Wrong input for password! Try again:");
            this.setPassword();
        }
    }

    private void repeatPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Repeat password:");
        String password = sc.next();
        try {
            if (!this.password.equals(password)) {
                throw new IncorrectInputException();
            }
        } catch (IncorrectInputException e) {
            System.out.println("Passwords don't match!");
            this.repeatPassword();
        }
    }

    public Level getLevel() {
        return this.reachedLevel;
    }

    public int getReachedQuestion() {
        return this.reachedQuestion;
    }

    public void answerQuestion(Question question) {
        Scanner sc = new Scanner(System.in);
        // za celta na demoto go pravim s int
        System.out.println("Pick an answer for 1 to 3:");
        int answer = sc.nextInt();
        // v demoto verniq otgovor e 3
        if (answer == 3) {
            System.out.println("Correct answer!");
            this.goAhead();
        } else {
            System.out.println("Wrong answer!");
            System.out.println();
            this.loseLife();
            this.goAhead();
        }
        System.out.println();

    }

    private void loseLife() {
        if (this.lives == 0) {
            System.out.println("You have no more lives! \n If you answer this question you will get 2 more lives \n BUT if you don't the game will be over");
            this.answerLogicQuestion();
        } else {
            System.out.println("You lose a life!");
            this.lives--;
            System.out.println("Lives left: " + this.lives);
        }
    }

    private void goAhead() {
        if (this.reachedQuestion == this.reachedLevel.getQuestionSize() - 1) {
            this.levelUp();
        } else {
            this.reachedQuestion++;
        }
    }

    private void levelUp() {
        if (this.indexOfReachedLevel == this.levels.size() - 1) {
            System.out.println("***************************************************");
            System.out.println("GG WP OMG \nKoi e bok? \n" + this.name + " e \nYEEEY \n Ai ch!!");
            System.out.println("***************************************************");
            System.exit(0);
        } else {
            this.indexOfReachedLevel++;
            this.reachedLevel = this.levels.get(this.indexOfReachedLevel);
            this.reachedQuestion = 0;
        }
    }

    private void answerLogicQuestion() {
        Question logicQuestion = LogicQuestion.LOGIC_QUESTIONS.get(LogicQuestion.INDEX_OF_QUESTION_TO_ASK++);
        System.out.println(logicQuestion);
        Scanner sc = new Scanner(System.in);
        System.out.println("Your answer is:");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase(logicQuestion.getRightAnswer())) {
            System.out.println("Correct! You get 2 lives. Enjoy them!!");
            this.lives = WON_LIVES;
        } else {
            System.out.println("Sorry, dude.. get lost \n-----GAMEOVER-----");
            System.out.println("-----------YOU MUST START FROM THE BEGINNING-------------------");
            this.indexOfReachedLevel = 0;
            this.reachedLevel = levels.get(indexOfReachedLevel);
            this.reachedQuestion = 0;
            this.lives = MAX_LIVES;
        }


    }


}
