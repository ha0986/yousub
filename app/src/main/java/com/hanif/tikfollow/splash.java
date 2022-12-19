package com.hanif.tikfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            String username = pref.getString("name", "");
            if (username==""){
                Intent myIntent = new Intent(splash.this, login.class);
                startActivity(myIntent);
            }else {
                autoLoad.userName = username;
                Intent myIntent = new Intent(splash.this, profile.class);
                startActivity(myIntent);
            }
        }, 2000);


    }

}