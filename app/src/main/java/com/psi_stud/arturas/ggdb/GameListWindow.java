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

public class GameListWindow extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    //Button btn;
    //private EditText searchText/;
    ListView listas;
    ArrayAdapter adapter;
    ArrayList<Game> gamesList; //listas i kuri bus ideti zaidimu pavadinimai
    private Spinner sortSpinner;
    //ArrayList<Game> filteredTest; //listas kuri rodys po searcho
    int newsIDNow;
    int ageOfUser;
    int userAge;
    SharedPreferences saved_values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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

        GamePresenter gamePresenter = new GamePresenter();
        UserPresenter userPresenter = new UserPresenter();

        userAge = saved_values.getInt("age", -1);
        ageOfUser = userAge;
        if(gamePresenter.isGameCensored(gamesList.get(position).getAge())) {
            if(userPresenter.isUserSignedUp(ageOfUser)) {
                if(userPresenter.isAppropriateAge(ageOfUser, gamesList.get(position).getAge())) {
                    Intent intent = new Intent(getApplicationContext(), GameWindow.class);
                    Bundle b = new Bundle();
                    b.putInt("gameID", gamesList.get(position).getGameID());
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Intent intentErrorMessage = new Intent(getApplicationContext(), MessageWindow.class);
                    Bundle bErrMessage = new Bundle();
                    bErrMessage.putString("ErrorMessage", "Jusu amzius nera tinkamas siam zaidimui");
                    intentErrorMessage.putExtras(bErrMessage);
                    startActivity(intentErrorMessage);
                }
            } else {
                Intent intent = new Intent(getApplicationContext(), LoginWindow.class);
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(getApplicationContext(), GameWindow.class);
            Bundle b = new Bundle();
            b.putInt("gameID", gamesList.get(position).getGameID());
            intent.putExtras(b);
            startActivity(intent);
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
                sortedList = SortPresenter.sortGamesByPopularity();
                updateGamesList(sortedList);
                break;
            case "Naudotojų įvertinimą":
                sortedList = SortPresenter.sortGamesByRating();
                updateGamesList(sortedList);
                break;
            case "Žanrus":
                sortedList = SortPresenter.sortGamesByGenre();
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