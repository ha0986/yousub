package com.hanif.tikfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        move();
        autoLoad.loadAdd(this);
    }

    public void move(){
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            String username = pref.getString("name", "");
            if (Objects.equals(username, "")){
                Intent myIntent = new Intent(splash.this, login.class);
                startActivity(myIntent);
            }else {

                autoLoad.userName = username;
                autoLoad.getdata();
                Intent myIntent = new Intent(splash.this, task.class);
                startActivity(myIntent);
            }
        }, 3000);
    }

}