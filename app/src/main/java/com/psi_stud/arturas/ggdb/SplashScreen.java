package com.psi_stud.arturas.ggdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Arturas on 2015-12-17.
 */
public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainWindow.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
