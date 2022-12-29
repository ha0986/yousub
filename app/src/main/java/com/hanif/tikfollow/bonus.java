package com.hanif.tikfollow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Locale;
import java.util.Objects;


public class bonus extends AppCompatActivity implements View.OnClickListener {
    public static String tag;
    public String claimedDate;
    public  Integer next;
    public String date;
    public Button claimedButton;

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

        getDatas();
        autoLoad.loadInter(this);
        autoLoad.loadReward(this,"");
        autoLoad.loadBanner(this,"top");
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
        check();
        claimedButton = findViewById(v.getId());
    }


    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    public void getDatas(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        next = Integer.valueOf(pref.getString("next", "1"));
        claimedDate= pref.getString("date", "1");
        Log.d("next", String.valueOf(next));

         int[] BUTTON_IDS = {
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
        };

         for(int i=0; i<next;i++){
            Button button = findViewById(BUTTON_IDS[i]);
            button.setText("Claimed");
            button.setBackgroundColor(R.color.teal_200);
         }
    }





    public void check(){
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if(String.valueOf(next).equals("1") & Objects.equals(tag, "1")){
            loadAdd();
        }else if(String.valueOf(next).equals("1") & !Objects.equals(tag, "1")){
            autoLoad.alart(this, "Click on first Item to get the offer");
        }else if(Objects.equals(date, claimedDate)){
            autoLoad.alart(this, "You have already Claimed This offer");
        }else if(!Objects.equals(date, claimedDate) & Objects.equals(tag, String.valueOf(next))){
            loadAdd();
        }

    }

    public void loadAdd(){
        new android.app.AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Tikfollow")
                .setMessage("Watch add to claim this offer")
                .setPositiveButton("OK", (dialog, which) ->claim())
                .setNegativeButton("No", null)
                .show();

    }




    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    public void claim(){
        autoLoad.showReward(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Days");
        myRef.child(tag).child(autoLoad.userName).setValue(0);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        next = Integer.parseInt(tag)+1;

        editor.putString("date", date);
        editor.putString("next", String.valueOf(next));
        claimedDate = date;
        editor.apply();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tikfollow");
        builder.setMessage("You will get your offer within a day. Please keep patience");
        AlertDialog alert = builder.create();
        alert.show();


        claimedButton.setText("claimed");
        claimedButton.setBackgroundColor(R.color.teal_200);


    }


    public void onBackPressed() {
        autoLoad.showInter(this);
        Intent myIntent = new Intent(bonus.this, profile.class);
        startActivity(myIntent);
    }

}