package com.psi_stud.arturas.ggdb;


public class GamePresenter {
    public boolean isGameCensored(int gameId) {
        boolean value = false;
        if(gameId > 0) {
            return true;
        }
        return value;
    }
}
