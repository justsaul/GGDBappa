package com.psi_stud.arturas.ggdb;

import java.util.ArrayList;

public class SearchPresenter {

    public static ArrayList<Game> getgameList() {
        Game gameEntity = new Game();
        ArrayList<Game> gamesList = gameEntity.getGamesList();
        return gamesList;
    }

    public String[] getmStrings(){
        ArrayList<Game> gamesList = getgameList();
        String[] mStrings = new String[100];

        for (int i = 0; i < mStrings.length; i++) {
            mStrings[i] = "";
        }

        for (int i = 0; i < gamesList.size(); i++) {
            mStrings[i] = gamesList.get(i).getName();
        }

        return mStrings;
    }
}
