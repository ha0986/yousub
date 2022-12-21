package com.hanif.tikfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import kotlin.collections.IntIterator;

public class bonus extends AppCompatActivity implements View.OnClickListener {
    public static String tag;
    public String claimedDate;
    public  Integer next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);


        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

        autoLoad.loadBanner(this,"bottom");
        autoLoad.loadInter(this);
        autoLoad.loadReward(this, "");
        getDatas();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button1:
                tag = "1";
                break;
            case R.id.button2:
                tag = "2";
                break;
            case R.id.button3:
                tag = "3";
                break;
            case R.id.button4:
                tag = "4";
                break;
            case R.id.button5:
                tag = "5";
                break;
            case R.id.button6:
                tag = "6";
                break;
            case R.id.button7:
                tag = "7";
                break;
            case R.id.button8:
                tag = "8";
                break;
        }
    }


    public void getDatas(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        next = Integer.valueOf(pref.getString("next", "1"));
    }





    public void check(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Log.d("hanif", String.valueOf(date));
        if(String.valueOf(date).equals(claimedDate)){
            autoLoad.alart(this,"You can't Claim this offer now");
        }else if(Integer.valueOf(tag).equals(next)){
            autoLoad.alart(this,"You can't Claim this offer now");
        }else{
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            SharedPreferences.Editor editor = pref.edit();
            next = Integer.parseInt(tag)+1;

            editor.putString("date", String.valueOf(date));
            editor.putString("next", String.valueOf(next));



            editor.apply();
            claim();
        }


    }


    public static void claim(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Days");
        myRef.child(tag).setValue(0);
    }


}