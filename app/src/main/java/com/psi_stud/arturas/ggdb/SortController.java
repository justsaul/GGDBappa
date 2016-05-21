package com.psi_stud.arturas.ggdb;

import java.util.ArrayList;

/**
 * Created by Laurynas on 5/21/2016.
 */
public class SortController {

    public static ArrayList<Game> sortGamesByPopularity() {
        SQLService service = new SQLService();
        ArrayList<Game> gamesList = service.gamesList;

        Game temp;
        for(int i=0; i < gamesList.size()-1; i++){
            for(int j=1; j < gamesList.size()-i; j++){
                if(gamesList.get(j-1).getViews() < gamesList.get(j).getViews()){
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

    public static ArrayList<Game> sortGamesByGenre() {
        SQLService service = new SQLService();
        ArrayList<Game> gamesList = service.gamesList;

        Game temp;
        for(int i=0; i < gamesList.size()-1; i++){

            for(int j=1; j < gamesList.size()-i; j++){
                if(gamesList.get(j-1).getGenre().compareTo(gamesList.get(j).getGenre()) > 0){
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

    public static ArrayList<Game> sortGamesByRating() {
        SQLService service = new SQLService();
        ArrayList<Game> gamesList = service.gamesList;

        Game temp;
        for(int i=0; i < gamesList.size()-1; i++){
            for(int j=1; j < gamesList.size()-i; j++){
                if(gamesList.get(j-1).getRating() < gamesList.get(j).getRating()){
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
