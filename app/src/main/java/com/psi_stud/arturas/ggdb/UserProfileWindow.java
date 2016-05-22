package com.psi_stud.arturas.ggdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfileWindow extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView tv = (TextView)findViewById(R.id.username);
        tv.setText(MainWindow.user.getUsername());

        TextView emailtv = (TextView)findViewById(R.id.email);
        emailtv.setText(MainWindow.user.getEmail());

        TextView firsttv = (TextView)findViewById(R.id.name);
        firsttv.setText(MainWindow.user.getFirstName());

        TextView lasttv = (TextView)findViewById(R.id.surename);
        lasttv.setText(MainWindow.user.getLastName());

        Button btnGameList = (Button) findViewById(R.id.gamelist);
        btnGameList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gamelist:
                startActivity(new Intent(this, GameListWindow.class));
                break;
        }
    }
}
