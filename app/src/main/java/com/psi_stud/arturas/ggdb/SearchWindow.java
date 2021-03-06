package com.psi_stud.arturas.ggdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchWindow extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    Button btn;
    private EditText searchText;
    ListView listas;
    ArrayAdapter adapter;
    ArrayList<Game> itemListTest; //listas i kuri bus ideti zaidimu pavadinimai
    ArrayList<Game> filteredTest; //listas kuri rodys po searcho
    int gameIDNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // test game
        filteredTest = new ArrayList();
        itemListTest = new ArrayList(); // cia reiktu paduoti zaidimu pavadinimus
        //fillList();
        //------------
        Game x = new Game(123, "x");
        Game y = new Game(1234, "y");

        itemListTest.add(x);
        itemListTest.add(y);
        //------------
        //
        listas = (ListView) findViewById(R.id.listView);
        btn = (Button) findViewById(R.id.button3);
        searchText = (EditText) findViewById(R.id.editText);
        adapter = new ArrayAdapter<Game>(this, R.layout.activity_listview, filteredTest);
        listas.setAdapter(adapter);
        btn.setOnClickListener(this);
        listas.setClickable(true);
        listas.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String text = searchText.getText().toString();
        filteredTest.clear();
        for (int i = 0; i < itemListTest.size(); i++) {
            String id = (String) itemListTest.get(i).getName();
            String noCaseId = id.toLowerCase();
            if (noCaseId.contains(text)) {
                filteredTest.add(itemListTest.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Game selectedGame = filteredTest.get(position);
        //gameIDNow = selectedGame.getGameID(); // paimam id zaidimo
        //btn.setText(selectedGame.getName());
        //startActivity(new Intent(this, GameProfile.class)); // siunciam id i gameprofile klase, kazkodel neveikia.
        Intent intent = new Intent(this, GameWindow.class);
        Bundle b = new Bundle();
        b.putInt("gameID", selectedGame.getGameID());
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt("gameID", gameIDNow);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    /*public void fillList(){
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
            String queryString = "select ID, Name from dbo.Games;";
            rs = stat.executeQuery(queryString);
            while (rs.next()) {
                System.out.println(rs.getInt(1)+" "+rs.getString(2));
                Game temp = new Game(rs.getInt(1),rs.getString(2));
                gamesList.add(temp);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/

}