package com.example.dpene.database.model;

import android.widget.Toast;

import com.example.dpene.database.SignUpActivity;

import java.util.TreeMap;

public class UserManager {

    private static UserManager instance = null;
    private TreeMap<String, Player> users;

    private UserManager() {
        this.users = new TreeMap<String, Player>();
    }

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();
        return instance;
    }

    public boolean existsUser(String username) {
        //String username = text ot poleto username
        return this.users.containsKey(username);
    }

    public boolean existsEmail(String email) {
        //String email = text ot poleto email
        for (Player user : this.users.values()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean rightPassword(String username, String password) {
        if (!existsUser(username)) {
            return false;
        } else {
            //map<String, User>, zatova imame getPassword
            return this.users.get(username).getPassword().equals(password);
        }
    }


    public boolean registerNewUser(String email, String username, String password, String repeatPassword) {

        // ili vmesto if-ove i exceptioni.. tezi proverki da gi pravim v activity-to
        if (existsUser(username)) {
            //tuk moje da hvurlq exception + Toast

            return false;
        }
        if (existsEmail(email)) {
            // tuk pak exception
            return false;
        }
        if (!password.equals(repeatPassword)) {
            // pak exception
            return false;
        }
        this.users.put(username,new Player(email, username, password));
        return true;
    }

}
