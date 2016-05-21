package com.psi_stud.arturas.ggdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private Game game;
    private TextView gameDesc, gameName, gameGenre, gameRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle b = getIntent().getExtras();
        String a = Integer.toString(b.getInt("gameID"));
        game = new Game(b.getInt("gameID"));

        game.loadGame();
        gameDesc = (TextView) findViewById(R.id.tvDesc);
        gameName = (TextView) findViewById(R.id.tvGameName);
        gameGenre = (TextView) findViewById(R.id.gameGenre);
        gameRating = (Button) findViewById(R.id.button);

        gameDesc.setText(game.getDescription());
        gameName.setText(game.getName());
        gameGenre.setText(game.getGenre());
        gameRating.setText(Float.toString(game.getRating()));

        //tvDesc = (TextView) findViewById(R.id.tvDesc);
        //tvGameName = (TextView) findViewById(R.id.tvGameName);
        //tvGameName.setText(a);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
