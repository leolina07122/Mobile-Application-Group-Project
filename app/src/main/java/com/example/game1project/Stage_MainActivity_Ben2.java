package com.example.game1project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener {
    private static final String TAG = "MyApp";
    TextView tv1, tv2;
    EditText et1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "The onCreate() event");
        /*Log.i(TAG, "I am logging something");
        TextView tv_name = (TextView) findViewById(R.id.tv_name);
        TextView tv_id = (TextView) findViewById(R.id.tv_id);
        TextView tv_course = (TextView) findViewById(R.id.tv_course);
        EditText et1 = findViewById(R.id.et1);
        tv_name.setText("My name is Ben");
        Toast.makeText(getApplicationContext(),
                "You have registered successfully!",
                Toast.LENGTH_LONG).show();
            View tv_btn1 = findViewById(R.id.tv_btn1);
            tv_btn1.setOnClickListener(this);*/


        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com"));
                startActivity(i);
            }
        });
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainTwoActivity.class);
                i.putExtra( "Key1", "Euclidean Geometry Between");
                i.putExtra(  "Key2", " THREE sides of a Right Triangle");
                startActivity(i);
            }
        });
    }
        @Override
         protected void onPause(){
            super.onPause();
            Toast.makeText(this,"The game is paused", Toast.LENGTH_LONG).show();
            }


        @Override
          protected void onResume(){
             super.onResume();
             Toast.makeText(this,"The game is not paused", Toast.LENGTH_LONG).show();
        }

         @Override
          protected void onDestroy(){
             super.onDestroy();
             Toast.makeText(this,"The game is error", Toast.LENGTH_LONG).show();
        }



        public void onClick(View v) {
         switch(v.getId()){
             case R.id.tv_btn1:
                  tv1.setText(et1.getText());
                  break;
         }

    }
}
