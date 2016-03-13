package com.example.dpene.database.model.dao;

import com.example.dpene.database.model.Player;

import java.util.List;

public interface IUserDAO {

    long addPlayer(Player player);
    Player getPlayer(String username);

    // za budeshti update-i na igrata
    List<Player> getAllPlayers();

    boolean checkUsername(String username);
    boolean checkUserEmail(String email);
    Player checkLogin (String username, String password);

}
