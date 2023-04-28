package com.example.game1project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Stage_Peter1 extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {

    Animation animFadeOut;
    Animation animFadeIn;
    ImageButton btnMeat;
    ImageButton btnDog;
    ImageButton btnBones;
    ImageButton btnKey;
    boolean enter=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_peter1);
        btnMeat = findViewById(R.id.meat);
        btnMeat.setOnClickListener(this);
        btnDog = findViewById(R.id.dog);
        btnDog.setOnClickListener(this);
        btnBones = findViewById(R.id.bones);
        btnBones.setVisibility(View.INVISIBLE);
        btnBones.setOnClickListener(this);
        btnKey = findViewById(R.id.key);
        btnKey.setVisibility(View.INVISIBLE);
        btnKey.setOnClickListener(this);
        loadAnimations();

    }



    private void loadAnimations() {
        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        btnKey.setVisibility(View.INVISIBLE);
        switch (v.getId()) {
            case R.id.meat: {
                animFadeOut.setAnimationListener(this);
                btnMeat.startAnimation(animFadeOut);

                if (!enter) {
                    animFadeIn.setAnimationListener(this);
                    btnBones.startAnimation(animFadeIn);
                }
            }
            case ((R.id.dog) & (R.id.meat)): {
                animFadeOut.setAnimationListener(this);
                btnBones.startAnimation(animFadeOut);
                btnKey.setVisibility(View.INVISIBLE);
                animFadeOut.setAnimationListener(this);
                btnDog.startAnimation(animFadeOut);
                enter = false;
                btnKey.setVisibility(View.VISIBLE);
                animFadeIn.setAnimationListener(this);
                btnKey.startAnimation(animFadeIn);
                enter = true;
            }

            case (R.id.key)&(R.id.door): {
                Toast.makeText(this, "You get the key and open the door!", Toast.LENGTH_LONG).show();

            }
            startActivity(new Intent(Stage_Peter1.this,Stage_Peter2.class));
        }
    }
            @Override
            public void onAnimationStart (Animation animation){

            }

            @Override
            public void onAnimationEnd (Animation animation){

            }

            @Override
            public void onAnimationRepeat (Animation animation){

            }

    }
