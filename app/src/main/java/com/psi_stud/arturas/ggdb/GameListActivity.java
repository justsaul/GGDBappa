package com.psi_stud.arturas.ggdb;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GameListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //Button btn;
    //private EditText searchText;
    ListView listas;
    ArrayAdapter adapter;
    ArrayList<Game> itemListTest; //listas i kuri bus ideti zaidimu pavadinimai
    //ArrayList<Game> filteredTest; //listas kuri rodys po searcho
    int newsIDNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

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
        adapter = new ArrayAdapter<Game>(this, R.layout.activity_listview, itemListTest);
        listas.setAdapter(adapter);
        //btn.setOnClickListener(this);
        listas.setClickable(true);
        listas.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Game selectedGame = itemListTest.get(position);
        Intent intent = new Intent(this, GameActivity.class);
        Bundle b = new Bundle();
        b.putInt("gameID", selectedGame.getGameID());
        intent.putExtras(b);
        startActivity(intent);
    }

    public void fillList(){
        Game temp = new Game(1, "test");
        itemListTest.add(temp);
    }

}
