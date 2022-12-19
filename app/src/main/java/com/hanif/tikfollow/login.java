package com.hanif.tikfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button done = findViewById(R.id.done);
        Button gues = findViewById(R.id.gp);
        EditText text = findViewById(R.id.editTextTextPersonName3);
        String check = getIntent().getStringExtra("change");




        done.setOnClickListener(new View.OnClickListener() {

            public String input = text.getText().toString();
            public void onClick(View v) {
                if(input.isEmpty()){
                    autoLoad.alart(login.this, "Please enter your Username");
                }else {
                    if (input.startsWith("@")){
                        autoLoad.savedata(input,500);
                        save(input);
                    }else {
                        input= "@"+input;
                        autoLoad.savedata(input,500);
                        save(input);
                    }

                }
                if (check == "change") {
                    autoLoad.removedata();
                }
            }
        });


        gues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(login.this, profile.class);
                startActivity(myIntent);
            }
        });
    }


    public void save(String userName){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", userName);
    }


}