package com.example.game1project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class Stage_MainTwoActivity_Ben2 extends AppCompatActivity {

    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        Bundle extras = getIntent().getExtras();

        String value1 = extras.getString("Key1");
        String value2 = extras.getString("Key2");

        TextView tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText(value1+value2);
    }
}
