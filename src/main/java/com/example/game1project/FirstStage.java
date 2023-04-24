package com.example.game1project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class FirstStage extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer;
    private MediaPlayer negative_guitar;
    private MediaPlayer unlock_game;
    private ImageView arrow_up;
    private ImageView arrow_down;
    private ImageView arrow_right;
    private ImageView arrow_left;
    private boolean isUp;
    private boolean isDown;
    private boolean isRight;
    private boolean isLeft;
    private RelativeLayout layout;
    private AlertDialog.Builder builder;
    private TextView add_score;
    private TextView text_username;
    private int score;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_stage);

        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true);

        negative_guitar = MediaPlayer.create(this,R.raw.negative_guitar);
        unlock_game = MediaPlayer.create(this,R.raw.unlock_game);


        arrow_up = findViewById(R.id.arrow_up);
        arrow_up.setOnClickListener(this);

        arrow_down = findViewById(R.id.arrow_down);
        arrow_down.setOnClickListener(this);

        arrow_right = findViewById(R.id.arrow_right);
        arrow_right.setOnClickListener(this);

        arrow_left = findViewById(R.id.arrow_left);
        arrow_left.setOnClickListener(this);

        layout = findViewById(R.id.layout);
        layout.setOnClickListener(this);

        builder = new AlertDialog.Builder(this);

        text_username = findViewById(R.id.text_username);

        add_score = findViewById(R.id.score);

        // Get a reference to the SharedPreferences object
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Retrieve the user's name from the SharedPreferences
        String userName = sharedPreferences.getString("userName", "");

        text_username.setText(userName);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Music start
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Music pause
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //  Destroy music
        mediaPlayer.release();
    }


    @Override
    public void onClick(View view) {

        // Rotate the arrows
        if (view.getId() == R.id.arrow_up) {arrow_up.animate().rotationBy(90).start();}
        if (view.getId() == R.id.arrow_down) {arrow_down.animate().rotationBy(90).start();}
        if (view.getId() == R.id.arrow_right){arrow_right.animate().rotationBy(90).start();}
        if (view.getId() == R.id.arrow_left){arrow_left.animate().rotationBy(90).start();}

        if(view.getId() == R.id.layout){

            isUp = checkUpRotation();
            isDown = checkDownRotation();
            isRight = checkRightRotation();
            isLeft = checkLeftRotation();

            if(isUp && isDown && isRight && isLeft){
                unlock_game.start();
                builder.setTitle("Congratulation message");
                builder.setMessage("You won! click the button to the next stage");

                // Initialize the score to 0
                score = 0;

                // When the user wins a game, add 10 to the score
                score += 10;

                //  Update the score on the current page
                add_score.setText(String.valueOf(score));

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Create a new Bundle to store the score
                        Bundle bundle = new Bundle();
                        // Store the score in the Bundle with a key of "score"
                        bundle.putInt("score", score);
                        Intent intent = new Intent(FirstStage.this,SecondStage.class);
                        // Add the Bundle to the Intent
                        intent.putExtras(bundle);
                        //  Jump to other activity
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                negative_guitar.start();
            }
        }
    }


    public boolean checkUpRotation() {
        // Check rotation degree
        // Reduced the value between 0 - 360 degree
        float up_rotation = arrow_up.getRotation() % 360;
        if (up_rotation == 0) {// The ImageView is not rotated
        } else if (up_rotation == 90) {
            // The ImageView is rotated 90 degrees
        } else if (up_rotation == 180) {
            // The ImageView is rotated 180 degrees
        } else if (up_rotation == 270) {
            // The ImageView is rotated 270 degrees
            return true;
        }
        return false;
    }


    public boolean checkDownRotation() {
        // Check rotation degree
        // Reduced the value between 0 - 360 degree
        float down_rotation = arrow_down.getRotation() % 360;
        if (down_rotation == 0) {
            // The ImageView is not rotated
        } else if (down_rotation == 90) {
            // The ImageView is rotated 90 degrees
            return true;
        } else if (down_rotation == 180) {
            // The ImageView is rotated 180 degrees
        } else if (down_rotation == 270) {
            // The ImageView is rotated 270 degrees
        }
        return false;
    }


    public boolean checkRightRotation(){

        float right_rotation = arrow_right.getRotation() % 360;
        if (right_rotation == 0) {
            // The ImageView is not rotated
        } else if (right_rotation == 90) {
            // The ImageView is rotated 90 degrees
        } else if (right_rotation == 180) {
            // The ImageView is rotated 180 degrees
            return true;
        } else if (right_rotation == 270) {
            // The ImageView is rotated 270 degrees
        }
        return false;
    }

    public boolean checkLeftRotation(){
        float left_rotation = arrow_left.getRotation() % 360;
        if (left_rotation == 0) {
            // The ImageView is not rotated
            return true;
        } else if (left_rotation == 90) {
            // The ImageView is rotated 90 degrees
        } else if (left_rotation == 180) {
            // The ImageView is rotated 180 degrees
        } else if (left_rotation == 270) {
            // The ImageView is rotated 270 degrees
        }
        return false;
    }
}
