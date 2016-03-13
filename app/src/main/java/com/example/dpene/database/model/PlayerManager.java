package com.example.dpene.database.model;

import android.content.Context;

import com.example.dpene.database.model.dao.PlayerDAO;

public class PlayerManager {

    private static Player player;
    private static PlayerManager instance = null;
    private PlayerDAO playerDAO;
    private static final int STARTING_LEVEL = 0;
    private static final int STARTING_QUESTION = 0;

    private PlayerManager(Context context) {
        this.playerDAO = new PlayerDAO(context);
    }

    public static PlayerManager getInstance(Context context) {
        if (instance == null)
            instance = new PlayerManager(context);
        return instance;
    }

    public boolean login(String username, String password){
        // inicializirame player-a
        this.player = playerDAO.checkLogin(username, password);
        return playerDAO.checkLogin(username, password) != null;
    }

    public boolean checkUsername(String username) {
        return this.playerDAO.checkUsername(username);
    }

    public boolean checkEmail(String email) {
        return this.playerDAO.checkUserEmail(email);
    }

    public boolean rightPassword(String username, String password) {
        return this.playerDAO.getPlayer(username).getPassword().equals(password);
    }

    public long registerPlayer(String email, String username, String password) {
        return this.playerDAO.addPlayer(new Player(email, username, password, STARTING_LEVEL, STARTING_QUESTION));
    }

    public Player getPlayer(){
        return this.player;
    }

}
