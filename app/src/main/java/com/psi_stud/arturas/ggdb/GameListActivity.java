package com.psi_stud.arturas.ggdb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class GameListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    //Button btn;
    //private EditText searchText/;
    ListView listas;
    ArrayAdapter adapter;
    ArrayList<Game> gamesList; //listas i kuri bus ideti zaidimu pavadinimai
    private Spinner sortSpinner;
    //ArrayList<Game> filteredTest; //listas kuri rodys po searcho
    int newsIDNow;
    int ageOfUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int userAge;
        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userAge = saved_values.getInt("age", -1);
        ageOfUser = userAge;
        System.out.println(ageOfUser);

        setContentView(R.layout.activity_game_list);

        // test game
        //filteredTest = new ArrayList();
        gamesList = new ArrayList(); // cia reiktu paduoti zaidimu pavadinimus
        fillList();
        //Game temp = new Game(1, "test");
        //gamesList.add(temp);
        //
        listas = (ListView) findViewById(R.id.listView);
        //btn = (Button) findViewById(R.id.button3);
        //searchText = (EditText) findViewById(R.id.editText);
        adapter = new ArrayAdapter<Game>(this, R.layout.activity_listview, gamesList);
        listas.setAdapter(adapter);
        //btn.setOnClickListener(this);
        listas.setClickable(true);
        listas.setOnItemClickListener(this);

        Spinner sortSpinner = (Spinner) findViewById(R.id.sortSpinner);
        sortSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Game selectedGame = gamesList.get(position);
        Intent intent = new Intent(this, GameActivity.class);
        Bundle b = new Bundle();
        b.putInt("gameID", selectedGame.getGameID());
        intent.putExtras(b);

        if(gamesList.get(position).getAge() == 0) {
            intent = new Intent(getApplicationContext(), GameActivity.class);
            b = new Bundle();
            b.putInt("gameID", gamesList.get(position).getGameID());
            intent.putExtras(b);
            startActivity(intent);
        } else if(ageOfUser >= gamesList.get(position).getAge()) {
            startActivity(intent);
        }else {
            Intent intentErrorMessage = new Intent(getApplicationContext(), MessageActivity.class);
            Bundle bErrMessage = new Bundle();
            if(ageOfUser == -1) {
                bErrMessage.putString("ErrorMessage", "Prisijunkite, siam pasirinkimui reikia jusu amziaus");
                intentErrorMessage.putExtras(bErrMessage);
            } else {
                bErrMessage.putString("ErrorMessage", "Jusu amzius nera tinkamas siam zaidimui");
                intentErrorMessage.putExtras(bErrMessage);
            }
            startActivity(intentErrorMessage);
        }
    }

    public void fillList(){
        Game gameEntity = new Game();
        gamesList = gameEntity.getGamesList();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

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
        adapter.clear();
        adapter.addAll(gamesList);
        adapter.notifyDataSetChanged();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
