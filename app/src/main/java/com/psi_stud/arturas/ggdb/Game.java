package com.psi_stud.arturas.ggdb;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Arturas on 2015-12-16.
 */
public class Game {
    private int gameID;
    private String name;
    private float rating;
    private String description;
    private int views;
    private String genre;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Game(int gameID){
        this.gameID = gameID;
    }

    public Game(int gameID, String name){
        this.gameID = gameID;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {

        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGameID() {
        return gameID;
    }

    public void loadGame(){
        SQLService service = new SQLService();
        ArrayList<Game> gamesList = service.gamesList;

        for (Game game : gamesList) {
            if (game.getGameID() == gameID) {
                name = game.getName();
                description = game.getDescription();
                rating = game.getRating();
                genre = game.getGenre();
                views = game.getViews();
                age = game.getAge();
            }
        }
        /*String hostIP = "10.3.3.125";
        String port = "49170";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String ConnectionString = "jdbc:jtds:sqlserver://" + hostIP + ":" + port + "/GGDB;user=admin;password=troll;"; //>><<AB001
            con = DriverManager.getConnection(ConnectionString, "admin", "troll");
            stat = con.createStatement();
            String queryString = "select * from dbo.Games where ID = "+gameID+";";
            rs = stat.executeQuery(queryString);
            while (rs.next()) {
                name = rs.getString(2);
                description = rs.getString(6);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }

    @Override
    public String toString() {
        return name;
    }
}
