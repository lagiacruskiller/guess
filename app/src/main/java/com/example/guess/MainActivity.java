package com.example.guess;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button guessbtn;
    private EditText guessbox;
    private ImageView face;
    private int counter = 0;
    private int guessnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int secretnum = (int)(Math.random()*9+1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guessbox = findViewById(R.id.guessbox);
        guessbtn = findViewById(R.id.guessbtn);
        face = findViewById(R.id.face);
        guessbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                guessnum = Integer.parseInt((guessbox.getEditableText().toString()));
                if(guessnum == secretnum && counter < 5) face.setImageResource(R.drawable.shocked);
                else if(guessnum == secretnum) face.setImageResource(R.drawable.smile);
                else{
                    if(guessnum > secretnum) Toast.makeText(MainActivity.this,"Too Big", Toast.LENGTH_LONG).show();
                    if(guessnum < secretnum) Toast.makeText(MainActivity.this,"Too small", Toast.LENGTH_LONG).show();
                    if(counter > 10) face.setImageResource(R.drawable.laugh);
                    counter++;
                }

            }
        });
    }
}
