package com.example.game1project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;import android.media.MediaPlayer;
public class SecondStage extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer;
    TextView text_up;
    TextView text_down;
    Button btn_down;
    Button btn_up;
    int count_green;
    int count_orange;
    private AlertDialog.Builder builder;
    private MediaPlayer unlock_game;
    private MediaPlayer negative_guitar;
    private RelativeLayout layout_2;
    private TextView text_username;
    private  TextView add_score;
    private int score;
    Handler handler;
    Runnable runnable;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_stage);
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true);

        unlock_game = MediaPlayer.create(this,R.raw.unlock_game);
        negative_guitar = MediaPlayer.create(this,R.raw.negative_guitar);

        text_down = findViewById(R.id.text_down);
        text_up = findViewById(R.id.text_up);

        btn_down = findViewById(R.id.btn_down);
        btn_up = findViewById(R.id.btn_up);

        btn_down.setOnClickListener(this);
        btn_up.setOnClickListener(this);

        layout_2 = findViewById(R.id.layout_2);
        layout_2.setOnClickListener(this);

        count_green = 0;
        count_orange = 0;

        builder = new AlertDialog.Builder(this);

        text_username = findViewById(R.id.text_username);

        // Get a reference to the SharedPreferences object
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Retrieve the user's name from the SharedPreferences
        String userName = sharedPreferences.getString("userName", "");

        // Set the user's name
        text_username.setText(userName);

        // Get the Bundle from the Intent
        Bundle bundle = getIntent().getExtras();

        // Retrieve the score from the Bundle
        score = bundle.getInt("score");

        // Display the current score
        add_score = findViewById(R.id.score);
        add_score.setText(String.valueOf(score));


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_down){

            int green = btn_down.getBackgroundTintList().getDefaultColor();
            if (green == Color.parseColor("#99CC00")){
                count_green++;
                text_down.setText(String.valueOf(count_green));
            }

            int orange = btn_down.getBackgroundTintList().getDefaultColor();
            if (orange == Color.parseColor("#F17A0A")){
                count_orange++;
                text_up.setText(String.valueOf(count_orange));
            }

            if (count_green == 3){
                handler = new Handler();
                runnable = new Runnable() {
                    int i = 0;
                    @Override
                    public void run() {
                        if(i%2 == 0){
                            //btn_green.setBackgroundColor(Color.parseColor("#F17A0A"));
                            int greenColor = ContextCompat.getColor(getApplicationContext(), R.color.orange);
                            ColorStateList backgroundTintList = ColorStateList.valueOf(greenColor);
                            btn_down.setBackgroundTintList(backgroundTintList);

                        }else {
                            //btn_green.setBackgroundColor(Color.parseColor("#99CC00"));
                            int greenColor = ContextCompat.getColor(getApplicationContext(), R.color.green);
                            ColorStateList backgroundTintList = ColorStateList.valueOf(greenColor);
                            btn_down.setBackgroundTintList(backgroundTintList);
                        }
                        i++;
                        handler.postDelayed(this,1000);
                    }
                };

                handler.post(runnable);

                // Get the current background color of the button
                ColorStateList backgroundTintList = btn_down.getBackgroundTintList();
                if (backgroundTintList != null) {
                    int buttonColor = backgroundTintList.getDefaultColor();
                    Log.d("Button Color", "Current color: " + buttonColor);
                } else {
                    // Button background is not a solid color
                    Log.d("Button Color", "Background is not a solid color");
                }

            }

            // Get the current background color of the button
            ColorStateList backgroundTintList = btn_down.getBackgroundTintList();
            if (backgroundTintList != null) {
                int buttonColor = backgroundTintList.getDefaultColor();
                Log.d("Button Color", "Current color: " + buttonColor);
            } else {
                // Button background is not a solid color
                Log.d("Button Color", "Background is not a solid color");
            }

        }

        if (view.getId() == R.id.btn_up){
            int orange = btn_up.getBackgroundTintList().getDefaultColor();
            if (orange == Color.parseColor("#F17A0A")){
                count_orange++;
                text_up.setText(String.valueOf(count_orange));
            }
        }

        if (view.getId() == R.id.layout_2){
            if (count_orange == 3 && count_green == 5){
                unlock_game.start();
                Log.d("Message", "You won");

                builder.setTitle("Congratulation message");
                builder.setMessage("You won! click the button to the next stage");

                // Initialize the score to 0 // This is second stage, do not need to Initialize
                //  score = 0;

                // When the user wins a game, add 10 to the score
                score += 10;

                //  Update the score on the current page
                add_score.setText(String.valueOf(score));

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Clean
                        cleanSquare();
                        // Create a new Bundle to store the score
                        Bundle bundle = new Bundle();
                        // Store the score in the Bundle with a key of "score"
                        bundle.putInt("score", score);
                        // Please change the class name to other class name that you are using
                        // To pass the data to another class
                        Intent intent = new Intent(SecondStage.this,SecondStage.class);
                        // Add the Bundle to the Intent
                        intent.putExtras(bundle);
                        //  Jump to other activity
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                negative_guitar.start();
                Log.d("Button", "You lose");
                builder.setTitle("Message");
                builder.setMessage("You Lose! click the button try again");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Clean
                        cleanSquare();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Music pause
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Music start
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  Destroy music
        mediaPlayer.release();
    }

    private void cleanSquare(){
        // Clean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (handler != null && handler.hasCallbacks(runnable)){
                // Stop changing colour
                handler.removeCallbacks(runnable);
            }
        }
        text_down.setText("");
        text_up.setText("");
        btn_up.setBackgroundColor(Color.parseColor("#F17A0A"));
        btn_down.setBackgroundColor(Color.parseColor("#99CC00"));
        count_green = 0;
        count_orange = 0;
    }

}
