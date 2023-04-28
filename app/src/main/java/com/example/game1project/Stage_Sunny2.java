package com.example.game1project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Stage_Sunny2 extends AppCompatActivity implements View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_sunny2);
    }

    //click the dot on the "i" to win
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.answer){
            Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show();
        }
    }
}