package com.hanif.likeefollow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class jokes extends AppCompatActivity {
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        ArrayList<String> jokelist = new ArrayList<>();
        autoLoad.loadInter(this);
        autoLoad.loadReward(this,"");
        autoLoad.loadBanner(this,"top");
        TextInputEditText jokes = findViewById(R.id.joke);

        Button next = findViewById(R.id.next);
        Button prev  = findViewById(R.id.prev);
        Button copy  = findViewById(R.id.Copy);



        next.setOnClickListener(v -> {
            count +=1;
            jokelist.get(count);
        });



    }
























    
    public void onBackPressed() {
        Intent myIntent = new Intent(jokes.this, profile.class);
        startActivity(myIntent);
    }




}