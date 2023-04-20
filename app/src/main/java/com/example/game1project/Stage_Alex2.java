package com.example.game1project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Stage_Alex2 extends AppCompatActivity {

    TextView scv;

    ImageView iv_g;
    ImageView iv_bk;
    ImageView iv_m;
    ImageView iv_bd;
    ImageView iv_bn;
    int score = 0;
    RelativeLayout parentView;

    private int lastX;
    private int lastY;
    private int maxRight;
    private int maxBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_alex2);

        scv = findViewById(R.id.sc2);

        parentView = findViewById(R.id.sarl2);

        iv_g = findViewById(R.id.gold_img);
        iv_bk = findViewById(R.id.bike_img);
        iv_m = findViewById(R.id.mesh_img);
        iv_bd = findViewById(R.id.board_img);
        iv_bn= findViewById(R.id.banana_img);


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null)
            if(bundle.containsKey("score"))
                score = bundle.getInt("score");

        iv_bk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return imgAction(motionEvent, iv_bk);
            }
        });

        iv_bd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return imgAction(motionEvent, iv_bd);
            }
        });

        iv_m.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return imgAction(motionEvent, iv_m);
            }
        });

        iv_bn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return imgAction(motionEvent, iv_bn);
            }
        });

        iv_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                score+=10;
                bundle.putInt("score", score);

                scv.setText("Score : " + score);

                Intent intent = new Intent(Stage_Alex2.this,Stage_Alex1.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });


        scv.setText("Score : " + score);
    }


    public boolean imgAction(MotionEvent motionEvent, View view){

        int eventX = (int) motionEvent.getRawX();
        int eventY = (int) motionEvent.getRawY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if(maxRight==0) {
                    maxRight = parentView.getRight();
                    maxBottom = parentView.getBottom();
                }

                lastX =eventX;
                lastY = eventY;

                break;
            case MotionEvent.ACTION_MOVE:

                int dx = eventX-lastX;
                int dy = eventY-lastY;

                int left = view.getLeft()+dx;
                int top = view.getTop()+dy;
                int right = view.getRight()+dx;
                int bottom = view.getBottom()+dy;

                if(left<0) {
                    right += -left;
                    left = 0;
                }
                if(top<0) {
                    bottom += -top;
                    top = 0;
                }
                if(right>maxRight) {
                    left -= right-maxRight;
                    right = maxRight;
                }
                if(bottom>maxBottom) {
                    top -= bottom-maxBottom;
                    bottom = maxBottom;
                }

                view.layout(left, top, right, bottom);

                lastX = eventX;
                lastY = eventY;
                break;

            default:
                break;
        }

        return true;
    }
}