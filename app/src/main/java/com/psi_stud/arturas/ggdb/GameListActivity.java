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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GameListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    //Button btn;
    //private EditText searchText;
    ListView listas;
    ArrayAdapter adapter;
    ArrayList<Game> itemListTest; //listas i kuri bus ideti zaidimu pavadinimai
    private Spinner sortSpinner;
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

        Spinner sortSpinner = (Spinner) findViewById(R.id.sortSpinner);

        // Spinner click listener
        sortSpinner.setOnItemSelectedListener(this);
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
        SQLService service = new SQLService();
        itemListTest = service.gamesList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Pasirinkta: " + item, Toast.LENGTH_LONG).show();
        ArrayList<Game> sortedList;
        switch (item) {
            case "Populiarumą":
                sortedList = SortController.sortGamesByPopularity();
                updateGamesList(sortedList);
                break;
            case "Naudotojų įvertinimą":
                sortedList = SortController.sortGamesByRating();
                updateGamesList(sortedList);
                break;
            case "Žanrus":
                sortedList = SortController.sortGamesByGenre();
                updateGamesList(sortedList);
                break;
        }
    }

    private void updateGamesList(ArrayList<Game> gamesList) {
        listas = (ListView) findViewById(R.id.listView);

        adapter.clear();
        adapter.addAll(gamesList);
        adapter.notifyDataSetChanged();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
