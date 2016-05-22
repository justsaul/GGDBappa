package com.psi_stud.arturas.ggdb;

import java.util.ArrayList;

/**
 * Created by Laurynas on 5/21/2016.
 */
public class SortPresenter {

    public static ArrayList<Game> sortGamesByPopularity() {
        Game gameEntity = new Game();
        ArrayList<Game> gamesList = sortGames("views", gameEntity.getGamesList());

        return gamesList;
    }

    public static ArrayList<Game> sortGamesByGenre() {
        Game gameEntity = new Game();
        ArrayList<Game> gamesList = sortGames("genre", gameEntity.getGamesList());

        return gamesList;
    }

    public static ArrayList<Game> sortGamesByRating() {
        Game gameEntity = new Game();
        ArrayList<Game> gamesList = sortGames("rating", gameEntity.getGamesList());

        return gamesList;
    }

    private static ArrayList<Game> sortGames(String key, ArrayList<Game> gamesList) {
        Game temp;
        for(int i=0; i < gamesList.size()-1; i++){
            for(int j=1; j < gamesList.size()-i; j++){
                if((key.equals("rating") && gamesList.get(j-1).getRating() < gamesList.get(j).getRating())
                        || (key.equals("genre") && gamesList.get(j-1).getGenre().compareTo(gamesList.get(j).getGenre()) > 0)
                        || (key.equals("views") && gamesList.get(j-1).getViews() < gamesList.get(j).getViews())){
                    temp = gamesList.get(j-1);
                    gamesList.set(j-1, gamesList.get(j));
                    gamesList.set(j, temp);
                }
            }
        }
        for(int i=0; i < gamesList.size(); i++) {
            System.out.println(gamesList.get(i).getName());
        }
        return gamesList;
    }
}
