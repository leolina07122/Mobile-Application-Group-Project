package com.example.game1project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Stage_Alex1 extends AppCompatActivity {

    ImageView sa_ansloc;
    TextView scv;
    TextView one_img;
    RelativeLayout parentView;

    private int lastX;
    private int lastY;
    private int maxRight;
    private int maxBottom;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_alex1);

        parentView = findViewById(R.id.sa_rl);

        scv = findViewById(R.id.sc);
        sa_ansloc = findViewById(R.id.sa_ansloc);
        one_img = findViewById(R.id.textView_num1);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null)
            if(bundle.containsKey("score"))
                score = bundle.getInt("score");

        one_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return imgAction(motionEvent, one_img);
            }
        });

        scv.setText("Score : " + score);

    }

    public boolean imgAction(MotionEvent motionEvent, View view){

        int eventX = (int) motionEvent.getRawX();
        int eventY = (int) motionEvent.getRawY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                if(isViewOverlapping(view, sa_ansloc)) {

                    Bundle bundle = new Bundle();
                    score+=10;
                    bundle.putInt("score", score);

                    Intent intent = new Intent(Stage_Alex1.this,Stage_Alex2.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;
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


    private boolean isViewOverlapping(View firstView, View secondView) {
        int[] firstPosition = new int[2];
        int[] secondPosition = new int[2];

        firstView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        firstView.getLocationOnScreen(firstPosition);
        secondView.getLocationOnScreen(secondPosition);

        int r = firstView.getMeasuredWidth() + firstPosition[0];
        int l = secondPosition[0];
        return r >= l && (r != 0 && l != 0);
    }
}