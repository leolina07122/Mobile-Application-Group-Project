package com.example.myapplication;


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
    ImageView img;
    String msg;
    private android.widget.RelativeLayout.LayoutParams layoutParams;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_peter1);
        loadAnimations();
        loadUI();
        img= (ImageButton) findViewById(R.id.key);


        img.setOnLongClickListener(v -> {
            ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

            ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
            View.DragShadowBuilder myShadow = new View.DragShadowBuilder(img);

            v.startDrag(dragData,myShadow,null,0);
            return true;
        });
        img.setOnDragListener((v, event) -> {
            switch(event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");

                    // Do nothing
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                    int x_cord;
                    int y_cord;
                    break;

                case DragEvent.ACTION_DRAG_EXITED :
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                    x_cord = (int) event.getX();
                    y_cord = (int) event.getY();
                    layoutParams.leftMargin = x_cord;
                    layoutParams.topMargin = y_cord;
                    v.setLayoutParams(layoutParams);
                    break;

                case DragEvent.ACTION_DRAG_LOCATION  :
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                    break;

                case DragEvent.ACTION_DRAG_ENDED   :
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");

                    // Do nothing
                    break;

                case DragEvent.ACTION_DROP:
                    Log.d(msg, "ACTION_DROP event");

                    // Do nothing
                    break;
                default: break;
            }
            return true;
        });

        img.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);

                img.startDrag(data, shadowBuilder, img, 0);
                img.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        });
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
