package com.psi_stud.arturas.ggdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Arturas on 2015-12-16.
 */
public class SQLService {

    private Connection connection;
    String hostIP = "192.168.43.52";
    String port = "49170";

    public ArrayList<Game> gamesList;
    public ArrayList<User> userList;

    public SQLService(){
        /*loadDriver();
        openConnection();*/

        fillGamesList();
        fillUserList();
    }

    private void fillGamesList() {
        gamesList = new ArrayList();

        Game temp = new Game(1, "test(age13)");
        temp.setRating(5);
        temp.setViews(5);
        temp.setGenre("pirmas");
        temp.setAge(13);
        gamesList.add(temp);

        temp = new Game(2, "test2(age18)");
        temp.setRating(4);
        temp.setViews(10);
        temp.setGenre("pirmas");
        temp.setAge(18);
        gamesList.add(temp);

        temp = new Game(3, "test3(age100)");
        temp.setRating(3.5f);
        temp.setViews(110);
        temp.setGenre("antras");
        temp.setAge(100);
        gamesList.add(temp);

        temp = new Game(4, "test4(age0)");
        temp.setRating(15f);
        temp.setViews(14);
        temp.setGenre("pirmas");
        temp.setAge(0);
        gamesList.add(temp);
    }

    private void fillUserList() {
        userList = new ArrayList();

        User temp = new User("a", "a", 18);
        userList.add(temp);

        temp = new User("b", "b", 3);
        userList.add(temp);

    }


    private void loadDriver() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("ERROR: Rules not found!");
            System.exit(1);
        }
    }

    private void openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://" + hostIP + ":" + port + "/GGDB;", "admin", "troll") ;
        }
        catch (SQLException sqle) {
            System.out.println("ERROR: Can't connect to the database!");
            System.exit(1);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("ERROR: Can't close connection!");
        }
    }

    public int updateDB(String query) throws SQLException {
        Statement stmt = null;
        int count = -1;

        try {
            stmt = connection.createStatement();
            count = stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if(stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("ERROR: Unexpected SQL error!");
            }
        }

        return count;
    }

    public LinkedList<LinkedList<String>> queryDB(String query) {
        LinkedList<LinkedList<String>> result = new LinkedList<LinkedList<String>>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()){
                LinkedList<String> row = new LinkedList<String>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    String temp = rs.getString(i);
                    if(temp == null) temp = "";
                    row.add(temp);
                }
                result.add(row);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Couldn't execute query!");
        } finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("ERROR: Unexpected SQL error!");
            }
        }

        return result;
    }
}
