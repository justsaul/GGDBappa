package com.psi_stud.arturas.ggdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageWindow extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Bundle b = getIntent().getExtras();
        String message = b.getString("ErrorMessage");

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);

    }
}
