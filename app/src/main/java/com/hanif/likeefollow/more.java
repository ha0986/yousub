package com.hanif.likeefollow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class more extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Button gdelete = findViewById(R.id.gdelete);
        Button abc = findViewById(R.id.abc);
        Button puzzle = findViewById(R.id.puzzle);
        Button word = findViewById(R.id.word);
        Button tikfollow = findViewById(R.id.tikfollow);
        Button tiklikes = findViewById(R.id.tiklikes);
        Button ludo = findViewById(R.id.ludo);
        Button likee = findViewById(R.id.likee);
        Button close = findViewById(R.id.close);

        gdelete.setOnClickListener(this);
        abc.setOnClickListener(this);
        puzzle.setOnClickListener(this);
        word.setOnClickListener(this);
        tikfollow.setOnClickListener(this);
        tiklikes.setOnClickListener(this);
        likee.setOnClickListener(this);
        ludo.setOnClickListener(this);
        close.setOnClickListener(this);

    }







    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.gdelete:
                openUrl("https://play.google.com/store/apps/details?id=com.hanif.gdele");
                break;
            case R.id.abc:
                openUrl("https://play.google.com/store/apps/details?id=com.hanira.love");
                break;
            case R.id.word:
                openUrl("https://play.google.com/store/apps/details?id=com.hanif.easyPuzzle");
                break;
            case R.id.puzzle:
                openUrl("https://play.google.com/store/apps/details?id=com.hanif.puzzle");
                break;
            case R.id.tiklikes:
                openUrl("https://play.google.com/store/apps/details?id=com.hanif.tiklikes");
                break;
            case R.id.tikfollow:
                openUrl("https://play.google.com/store/apps/details?id=com.hanif.tikfollow");
                break;
            case R.id.ludo:
                openUrl("https://play.google.com/store/apps/details?id=com.hanif.lodu");
                break;
            case R.id.likee:
                openUrl("https://play.google.com/store/apps/details?id=com.hanif.likeefollow");
                break;
            case R.id.close:
                Intent myIntent = new Intent(more.this, profile.class);
                startActivity(myIntent);
                break;
        }

    }




    public void openUrl(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public void onBackPressed() {
        Intent myIntent = new Intent(more.this, profile.class);
        startActivity(myIntent);
    }
}