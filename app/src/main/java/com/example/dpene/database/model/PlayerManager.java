package com.example.dpene.database.model;

import android.content.Context;

import com.example.dpene.database.model.dao.PlayerDAO;

public class PlayerManager {

    private static Player player;
    private static PlayerManager instance = null;
    private PlayerDAO playerDAO;

    private PlayerManager(Context context) {
        this.playerDAO = new PlayerDAO(context);
    }

    public static PlayerManager getInstance(Context context) {
        if (instance == null)
            instance = new PlayerManager(context);
        return instance;
    }

    // kogato se izvika metoda se inicializira i player-a, login vinagi minava prez metoda
    public boolean login(String username, String password){
        this.player = playerDAO.checkLogin(username, password);
        return playerDAO.checkLogin(username, password) != null;
    }

    public boolean validateUsername(String username){
       return username != null && username.length() >= 3 || username != "";
    }

    public boolean checkUsername(String username) {
        return this.playerDAO.checkUsername(username);
    }

    public boolean checkEmail(String email) {
        return this.playerDAO.checkUserEmail(email);
    }

    public boolean validateEmail(String email){
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return email != null && email.matches(pattern);
    }

    public boolean strongPasword(String password){
        String pattern = "(?=.*[0-9])(?=.*[a-z]).{5,10}";
        return (password != null && password.matches(pattern));
    }

    public long registerPlayer(String email, String username, String password) {
        return this.playerDAO.addPlayer(new Player(email, username, password));
    }

    public Player getPlayer(){
        return this.player;
    }

}
