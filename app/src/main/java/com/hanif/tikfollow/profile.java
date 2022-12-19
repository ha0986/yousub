package com.hanif.tikfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profile extends AppCompatActivity implements View.OnClickListener {

    public Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button rate = findViewById(R.id.rate);
        Button edit = findViewById(R.id.edit);
        Button task = findViewById(R.id.task);
        Button reward = findViewById(R.id.reward);
        Button more = findViewById(R.id.more);
        Button exit = findViewById(R.id.exit);
        Button bonus = findViewById(R.id.bonus);


        rate.setOnClickListener(this);
        edit.setOnClickListener(this);
        task.setOnClickListener(this);
        reward.setOnClickListener(this);
        more.setOnClickListener(this);
        exit.setOnClickListener(this);
        bonus.setOnClickListener(this);


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rate:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.hanif.tikfollow"));
                startActivity(browserIntent);
                break;
            case R.id.edit:
                myIntent = new Intent(profile.this, login.class);
                startActivity(myIntent);
                break;
            case R.id.task:
                myIntent = new Intent(profile.this, task.class);
                startActivity(myIntent);
                break;
            case R.id.reward:
                autoLoad.loadReward(this);
                break;
            case R.id.exit:
                autoLoad.Exit(profile.this, this);
                break;
            case R.id.bonus:
                myIntent = new Intent(profile.this,bonus.class);
                startActivity(myIntent);
                break;
            case R.id.more:
                myIntent = new Intent(profile.this, more.class);
                startActivity(myIntent);
                break;
        }

    }

}