package com.psi_stud.arturas.ggdb;

import android.os.StrictMode;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Arturas on 2015-12-14.
 */
public class Login {
    private String username;
    private String password;
    private User user;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean Init() {
        String hostIP = "10.3.3.125";
        String port = "49170";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        //SQLService service = new SQLService();
        boolean result = false;

        try {
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
        }
        return result;
    }

    public User getUser() {
        return user;
    }
}
