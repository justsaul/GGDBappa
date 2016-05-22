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

    public Game() {
    }

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
        for (Game game : getGamesList()) {
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

    public ArrayList<Game> getGamesList() {
        ArrayList<Game> gamesList = new ArrayList<Game>();

        Game temp = new Game(1, "test(age13)");
        temp.setDescription("Labai labai labai ilgas aprasymas");
        temp.setRating(5);
        temp.setViews(5);
        temp.setGenre("pirmas");
        temp.setAge(13);
        gamesList.add(temp);

        temp = new Game(2, "test2(age18)");
        temp.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        temp.setRating(4);
        temp.setViews(10);
        temp.setGenre("pirmas");
        temp.setAge(18);
        gamesList.add(temp);

        temp = new Game(3, "test3(age100)");
        temp.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        temp.setRating(3.5f);
        temp.setViews(110);
        temp.setGenre("antras");
        temp.setAge(100);
        gamesList.add(temp);

        temp = new Game(4, "test4(age0)");
        temp.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        temp.setRating(15f);
        temp.setViews(14);
        temp.setGenre("pirmas");
        temp.setAge(0);
        gamesList.add(temp);

        return gamesList;
    }
}
