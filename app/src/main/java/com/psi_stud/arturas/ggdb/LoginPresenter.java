package com.psi_stud.arturas.ggdb;

import android.os.StrictMode;
import android.util.Log;

import net.sourceforge.jtds.jdbc.DateTime;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Arturas on 2015-12-14.
 */
public class LoginPresenter {
    private String username;
    private String password;
    private User user;

    public int ageOfLoggedInUser = 0;

    public LoginPresenter(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean validate() {
        //String hostIP = "10.3.3.125";
        //String port = "49170";
        //Connection con = null;
        //Statement stat = null;
        //ResultSet rs = null;
        //SQLService service = new SQLService();

        SQLService sqlS = new SQLService();

        ArrayList<User> userList;
        userList = new ArrayList();
        userList = sqlS.userList;

        boolean result = false;


        for(int i = 0; i < userList.size(); i++) {
            if(username.equals(userList.get(i).getUsername()) &&
                    password.equals(userList.get(i).getPassword())) {
                ageOfLoggedInUser = userList.get(i).age;
                authenticate();
                return true;
            } else {
                System.out.println("nesutapo");
            }
        }

        /*try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String ConnectionString = "jdbc:jtds:sqlserver://" + hostIP + ":" + port + "/GGDB;user=admin;password=troll;"; //>><<AB001
            con = DriverManager.getConnection(ConnectionString, "admin", "troll");
            stat = con.createStatement();

            String queryString = "select * from dbo.Users where Users.Username = '" + username + "' and Users.Password = '" + password + "';";
            //service.queryDB(queryString);
            rs = stat.executeQuery(queryString);
            while (rs.next()) {
                System.out.println(rs.getString(5) + " " + rs.getString(6));
                user = new User(rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),"","","");
                user.setIsLogedIn(true);
                result = true;

                System.out.println(result);
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            result =  false;
        }*/
        /*
        user = new User(rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),"","","");
        user.setIsLogedIn(true);

        if (username == "admin" && password == "admin" ) {
            result = true;
            System.out.println("True");
        } else {
            result = false;
            System.out.println("True");
        }
        */
        //return result;
        return result;
    }

    public void authenticate() {
    }

    public User getUser() {
      //  user.age = ageOfLoggedInUser;
        return user;
    }
}
