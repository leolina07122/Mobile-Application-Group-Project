package com.example.game1project;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.appcompat.app.AppCompatActivity;

public class Stage_Peter1 extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {

    Animation animFadeOut;
    Animation animFadeIn;
    ImageButton btnMeat;
    ImageButton btnDog;
    ImageButton btnBones;
    ImageButton btnKey;
    boolean enter=false;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_peter1);
        loadAnimations();
        loadUI();
    }

    private void loadUI() {
        btnMeat=findViewById(R.id.meat);
        btnMeat.setOnClickListener(this);
        btnDog=findViewById(R.id.dog);
        btnDog.setOnClickListener(this);
        btnBones=findViewById(R.id.bones);
        btnBones.setVisibility(View.INVISIBLE);
        btnBones.setOnClickListener(this);
        btnKey=findViewById(R.id.key);
        btnKey.setVisibility(View.INVISIBLE);
        btnKey.setOnClickListener(this);
    }

    private void loadAnimations() {
        animFadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        animFadeOut= AnimationUtils.loadAnimation(this,R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.meat) {
            animFadeOut.setAnimationListener(this);
            btnMeat.startAnimation(animFadeOut);
            enter= true;

            if (enter=true) {
                animFadeIn.setAnimationListener(this);
                btnBones.startAnimation(animFadeIn);

            }

        }
        if (v.getId()==R.id.dog)
        {
            animFadeOut.setAnimationListener(this);
            btnBones.startAnimation(animFadeOut);
            enter=true;
            if(enter=true){
                btnKey.setVisibility(View.VISIBLE);
                btnKey.startAnimation(animFadeIn);
                animFadeOut.setAnimationListener(this);
                btnDog.startAnimation(animFadeOut);
            }
        }
        if(v.getId()==R.id.key){
            btnKey.setOnClickListener(this);
            if(v.getId()==R.id.door){
                //link to end game page
            }


        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
