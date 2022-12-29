package com.hanif.tikfollow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class doTask extends AppCompatActivity implements View.OnClickListener {
    public Intent myIntent;
    public static TextView userpoints;
    public String minusUser;
    public Integer minusPoint=500;
    public static Integer plusPoints=500;
    public Integer click=1;
    public ArrayList<Integer> showInter= new ArrayList<>(Arrays.asList(3,7,10,13));


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
        autoLoad.checkNetwork(this);
        autoLoad.loadInter(this);
        autoLoad.loadReward(this,"");
        autoLoad.loadBanner(this,"top");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reward200:
                autoLoad.showReward(this);
                break;
            case  R.id.jokes:
                myIntent = new Intent(doTask.this, jokes.class);
                startActivity(myIntent);
                break;
            case  R.id.bonusbtn:
                myIntent = new Intent(doTask.this, bonus.class);
                startActivity(myIntent);
                break;
            case R.id.back:
                myIntent = new Intent(doTask.this, profile.class);
                startActivity(myIntent);
                break;
            case R.id.follow:
                startTask();
                click+=1;
                break;
        }
    }




    @SuppressLint("SetTextI18n")
    public void startTask(){
        String[] toSplit = autoLoad.nameList.get(click).split("=");
        minusUser = toSplit[0];
        plusPoints = plusPoints+100;
        minusPoint =Integer.parseInt(toSplit[1])-100;
        autoLoad.storePlusMinus(plusPoints,minusUser, minusPoint);
        clicked();
        autoLoad.points = String.valueOf(plusPoints);
        userpoints.setText(plusPoints.toString());
        if(showInter.contains(click)){
            autoLoad.showInter(this);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tiktok.com/"+ minusUser.trim()));
        startActivity(intent);
    }



    public void clicked(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        autoLoad.followed = autoLoad.followed+ ","+ minusUser;
        editor.putString("done", autoLoad.followed);
        editor.apply();
    }

    public void onBackPressed() {
        Intent myIntent = new Intent(doTask.this, profile.class);
        startActivity(myIntent);
    }


}