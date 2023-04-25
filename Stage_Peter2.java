package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Stage_Peter extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnDog;
    TextView tv1;
    ImageView Door;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        loadUI();

    }

    private void loadUI() {

        btnDog = findViewById(R.id.dog1);
        btnDog.setOnClickListener(this);
        tv1=findViewById(R.id.solve);
        tv1.setOnClickListener(this);



    }

    public void onClick(View v) {
        if (v.getId() == R.id.solve) {
            Toast.makeText(this, "You open the door!",Toast.LENGTH_LONG).show();
            Animation a =new TranslateAnimation(Animation.ABSOLUTE+0,Animation.ABSOLUTE+700,Animation.ABSOLUTE+0,Animation.ABSOLUTE+0);
            a.setDuration(2000);
            btnDog.startAnimation(a);
            }
        }
    }



