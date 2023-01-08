package com.hanif.likeefollow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class jokes extends AppCompatActivity {
    int count = 0;
    int size = 1;
    ArrayList<String> jokelist = new ArrayList<>();
    TextInputEditText jokes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        autoLoad.loadInter(this);
        autoLoad.loadReward(this,"");
        autoLoad.loadBanner(this,"top");
        jokes = findViewById(R.id.joke);

        Button next = findViewById(R.id.next);
        Button prev  = findViewById(R.id.prev);
        Button copy  = findViewById(R.id.Copy);



        getJokes();


        next.setOnClickListener(v -> {
            if(count<size){
                count +=1;
                setJokes();
            }else {
                count = 1;
                setJokes();
            }

        });

        prev.setOnClickListener(v -> {
            if(count<2){
                count = size-1;
                setJokes();
            }else {
                count-=1;
                setJokes();
            }
        });

        copy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("jokes",jokelist.get(count));
            clipboard.setPrimaryClip(clip);
        });

    }


    public void setJokes(){
        jokes.setText(jokelist.get(count));
    }

    public void getJokes(){

    }




    
    public void onBackPressed() {
        Intent myIntent = new Intent(jokes.this, profile.class);
        startActivity(myIntent);
    }




}