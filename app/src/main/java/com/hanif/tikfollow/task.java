package com.hanif.tikfollow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.Dictionary;
import java.util.Hashtable;

public class task extends AppCompatActivity implements View.OnClickListener {
    public Intent myIntent;
    public static TextView userpoints;
    public Array array;
    public String minusUser;
    public Integer minusPoint;
    public Integer plusPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Button reward = findViewById(R.id.reward200);
        Button back = findViewById(R.id.back);
        Button jokes = findViewById(R.id.jokes);
        Button follow = findViewById(R.id.follow);
        Button bonus = findViewById(R.id.bonusbtn);
        userpoints = findViewById(R.id.taskpoint);


        userpoints.setText(autoLoad.points);
        reward.setOnClickListener(this);
        back.setOnClickListener(this);
        jokes.setOnClickListener(this);
        follow.setOnClickListener(this);
        bonus.setOnClickListener(this);



       autoLoad.getDatas();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reward200:
                autoLoad.showReward(this);
                break;
            case  R.id.jokes:
                myIntent = new Intent(task.this, jokes.class);
                startActivity(myIntent);
                break;
            case  R.id.bonusbtn:
                myIntent = new Intent(task.this, bonus.class);
                startActivity(myIntent);
                break;
            case R.id.back:
                myIntent = new Intent(task.this, profile.class);
                startActivity(myIntent);
                break;
            case R.id.follow:
                startTask();
                break;
        }
    }





    public void savedata(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();

    }


    public void startTask(){
        plusPoints = plusPoints+100;
        minusPoint = minusPoint-100;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tiktok.com/"+ minusUser));
        startActivity(intent);
    }







}