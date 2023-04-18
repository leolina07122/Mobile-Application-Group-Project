package com.example.game1project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    EditText text_name;
    private AlertDialog.Builder builder;
    Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        text_name = findViewById(R.id.text_name);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        builder = new AlertDialog.Builder(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start){
            String user_name = text_name.getText().toString().trim();
            if (user_name.length() == 0){
                builder.setTitle("Error Message");
                builder.setMessage("Please Enter Your name");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                // Get a reference to the SharedPreferences object
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                // Get an editor object to modify the SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Store the user's name in the SharedPreferences
                editor.putString("userName", user_name);
                editor.apply();

                //  Jump to other activity
                Intent intent = new Intent(LogIn.this,FirstStage.class);
                startActivity(intent);

            }
        }
    }
}