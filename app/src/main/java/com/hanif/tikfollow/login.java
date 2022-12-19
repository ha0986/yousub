package com.hanif.tikfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button done = findViewById(R.id.done);
        Button gues = findViewById(R.id.gp);
        EditText text = findViewById(R.id.editTextTextPersonName3);
        String check = getIntent().getStringExtra("change");





        done.setOnClickListener(v -> {
            String inputs = text.getText().toString();
            if(inputs.isEmpty()){
                autoLoad.alart(login.this, "Please enter your Username");
            }else{
                if (Objects.equals(check, "true")) {
                    autoLoad.removedata(autoLoad.userName);
                }
                if (inputs.startsWith("@")){
                    autoLoad.savedata(inputs);
                    save(inputs);
                }else {
                    inputs= "@"+inputs;
                    autoLoad.savedata(inputs);
                    save(inputs);
                }

            }

        });


        gues.setOnClickListener(v -> {
            Intent myIntent = new Intent(login.this, profile.class);
            startActivity(myIntent);
        });
    }


    public void save(String userName){
        autoLoad.userName = userName;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", userName);
        editor.apply();
        changeScene();
    }

    public void changeScene(){
        Intent myIntent = new Intent(login.this, profile.class);
        startActivity(myIntent);
    }


}