package com.hanif.tikfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bonus extends AppCompatActivity implements View.OnClickListener {
    public static String tag;
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

    public static void claim(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Days");
        myRef.child(tag).setValue(0);
    }


}