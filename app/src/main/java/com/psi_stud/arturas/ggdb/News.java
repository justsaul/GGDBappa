package com.psi_stud.arturas.ggdb;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Mindaugas on 2015-12-16.
 */
public class News {
    private int newsID;
    private String title;
    private String content;
    private String author;

    public News(int newsID) {this.newsID = newsID; }
    public News(int newsID, String title) {this.newsID = newsID; this.title = title; }
    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author) { this.author = author; }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title) { this.title= title; }

    public void loadNews(){
        String hostIP = "10.3.3.125";
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
            String queryString = "select * from dbo.News where ID = "+newsID+";";
            rs = stat.executeQuery(queryString);
            while (rs.next()) {
                title = rs.getString(2);
                author = rs.getString(3);
                content = rs.getString(4);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNewsID() {
        return newsID;
    }

    @Override
    public String toString() {
        return title;
    }
}
