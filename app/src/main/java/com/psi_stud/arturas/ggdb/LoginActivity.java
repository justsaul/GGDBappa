package com.psi_stud.arturas.ggdb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

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
                LoginPresenter login = new LoginPresenter(etUsername.getText().toString(), etPassword.getText().toString());
                if(login.validate()){
                    try {
                        authenticate(login.ageOfLoggedInUser);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("as cia");
                    //MainActivity.user = login.getUser();
                    finish();
                } else {
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
        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = saved_values.edit();
        editor.putInt("age", age);
        editor.commit();
    }
}
