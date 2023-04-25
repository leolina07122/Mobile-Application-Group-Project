package com.example.game1project;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Stage_Sunny1 extends AppCompatActivity implements View.OnClickListener{

    int petcount;
    ProgressBar petbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_sunny1);
        petcount=0;

        petbar = findViewById(R.id.progressBar);
    }
    //tap on the dog six times to win
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dog){
            petcount++;
            petbar.setProgress(Math.round((50*petcount)/3));
            if(petcount==6){
                Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
