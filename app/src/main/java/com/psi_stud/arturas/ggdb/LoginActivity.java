package com.psi_stud.arturas.ggdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                System.out.println(login.Init());
                if(login.Init()){
                    System.out.println("as cia");
                    MainActivity.user = login.getUser();
                    finish();
                }
                else{
                    System.out.println("Nope");
                }
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
