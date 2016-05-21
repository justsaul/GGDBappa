package com.psi_stud.arturas.ggdb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        btnLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                System.out.println(etUsername.getText().toString());
                System.out.println(etPassword.getText().toString());
                Login login = new Login(etUsername.getText().toString(), etPassword.getText().toString());
                if(login.Init()){
                    try {
                        authenticate(login.ageOfLoggedInUser);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("as cia");
                    //MainActivity.user = login.getUser();

                    finish();
                }
                else{
                    Intent intentErrorMessage = new Intent(getApplicationContext(), MessageActivity.class);
                    Bundle bErrMessage = new Bundle();
                    bErrMessage.putString("ErrorMessage", "Blogi prisijungimo duomenys");
                    intentErrorMessage.putExtras(bErrMessage);
                    startActivity(intentErrorMessage);
                }
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
    public void authenticate(int age) throws IOException {
        //SharedPreferences sharedPref = this.getPreferences(MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
       //editor.putInt("amzius", age);
        //editor.commit();
        //SharedPreferences sharedPref = getSharedPreferences("myPrefs", this.MODE_WORLD_READABLE);

        //SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putInt("age", age);
        //System.out.println("pries commit");
        //System.out.println(age);
        //editor.commit();

        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = saved_values.edit();
        editor.putInt("age", age);
        editor.commit();
    }
}
