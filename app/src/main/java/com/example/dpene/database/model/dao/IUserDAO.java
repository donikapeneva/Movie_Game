package com.example.dpene.database.model.dao;

import com.example.dpene.database.model.Player;

import java.util.List;

public interface IUserDAO {

    long addUser(Player user);
    Player getUser(String username);
    Player getUser(long id);
    List<Player> getAllUsers();

    // ako imame takiva opcii:
//    void deleteUser(Player user);
//    long updateUser(Player user);

    boolean checkUsername(String username);
    boolean checkUserEmail(String email);
    Player checkLogin (String email, String password);

}
