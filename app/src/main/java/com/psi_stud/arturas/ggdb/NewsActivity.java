package com.psi_stud.arturas.ggdb;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class NewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //Button btn;
    //private EditText searchText;
    ListView listas;
    ArrayAdapter adapter;
    ArrayList<News> itemListTest; //listas i kuri bus ideti zaidimu pavadinimai
    //ArrayList<Game> filteredTest; //listas kuri rodys po searcho
    int newsIDNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // test game
        //filteredTest = new ArrayList();
        itemListTest = new ArrayList(); // cia reiktu paduoti zaidimu pavadinimus
        fillList();
        //Game temp = new Game(1, "test");
        //itemListTest.add(temp);
        //
        listas = (ListView) findViewById(R.id.listView);
        //btn = (Button) findViewById(R.id.button3);
        //searchText = (EditText) findViewById(R.id.editText);
        adapter = new ArrayAdapter<News>(this, R.layout.activity_listview, itemListTest);
        listas.setAdapter(adapter);
        //btn.setOnClickListener(this);
        listas.setClickable(true);
        listas.setOnItemClickListener(this);

    }

    //@Override
    //public void onClick(View view) {
    //  String text = searchText.getText().toString();
    //filteredTest.clear();
    //for (int i = 0; i < itemListTest.size(); i++) {
    //  String id = (String) itemListTest.get(i).getName();
    //String noCaseId = id.toLowerCase();
    //if (noCaseId.contains(text)) {
    //  filteredTest.add(itemListTest.get(i));
    //}
    //}
    //  adapter.notifyDataSetChanged();
    //}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News selectedNews = itemListTest.get(position);
        //gameIDNow = selectedGame.getGameID(); // paimam id zaidimo
        //btn.setText(selectedGame.getName());
        //startActivity(new Intent(this, GameProfile.class)); // siunciam id i gameprofile klase, kazkodel neveikia.
        Intent intent = new Intent(this, NewsContentActivity.class);
        Bundle b = new Bundle();
        b.putInt("newsID", selectedNews.getNewsID());
        intent.putExtras(b);
        startActivity(intent);
    }
    //@Override
    //public void onSaveInstanceState(Bundle savedInstanceState) {
    // Save the user's current game state
    //savedInstanceState.putInt("gameID", gameIDNow);
    // Always call the superclass so it can save the view hierarchy state
    //super.onSaveInstanceState(savedInstanceState);
    //}

    public void fillList(){
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
            String queryString = "select ID, Title from dbo.News;";
            rs = stat.executeQuery(queryString);
            while (rs.next()) {
                System.out.println(rs.getInt(1)+" "+rs.getString(2));
                News temp = new News(rs.getInt(1),rs.getString(2));
                itemListTest.add(temp);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}