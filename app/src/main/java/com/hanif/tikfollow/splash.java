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
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Write whatever to want to do after delay specified (1 sec)
                Log.d("Handler", "Running Handler");
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                String username = pref.getString("name", "");
                autoLoad.userName = username;
                Log.d("name", username);
                if (username==""){
                    Intent myIntent = new Intent(splash.this, login.class);
                    startActivity(myIntent);
                }else {
                    Intent myIntent = new Intent(splash.this, profile.class);
                    startActivity(myIntent);
                }
            }
        }, 1000);


    }

}