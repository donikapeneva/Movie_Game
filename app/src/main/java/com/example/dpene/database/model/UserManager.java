package com.example.dpene.database.model;

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

    public void registerUser(String email, String username, String password) {
        this.users.put(username, new Player(email, username, password));
    }
}
