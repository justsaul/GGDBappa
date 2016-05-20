package com.psi_stud.arturas.ggdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnUserProfile;
    Button btnSearch;
    Button btnNews;
    Button btnGamesList;
    public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnUserProfile = (Button) findViewById(R.id.btnUserProfile);
        btnNews = (Button) findViewById(R.id.btnNews);
        btnGamesList = (Button) findViewById(R.id.btnGamesList);

        btnUserProfile.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnNews.setOnClickListener(this);
        btnGamesList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        System.out.println("AAAA");
        switch (view.getId()) {
            case R.id.btnUserProfile:
                if(user == null || !user.isLogedIn) {
                    startActivity(new Intent(this, LoginActivity.class));
                    if(user != null)
                        startActivity(new Intent(this, UserProfileActivity.class));
                }
                else{
                    startActivity(new Intent(this, UserProfileActivity.class));
                }
                break;
            case R.id.btnSearch:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.btnNews:
                startActivity(new Intent(this, NewsActivity.class));
                break;
            case R.id.btnGamesList:
                startActivity(new Intent(this, GameListActivity.class));
                break;
        }
    }
}
