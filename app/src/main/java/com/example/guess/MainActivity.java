package com.example.guess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView gameturnbox, guessedbox;
    private Button guessbtn;
    private EditText guessbox;
    private ImageView face;
    private int counter = 0;
    private int guessnum;
    private int secretnum;
    public int gameturn = 1;
    public List<String> guessattempt = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        secretnum = new Random().nextInt(10)+1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guessbox = findViewById(R.id.guessbox);
        guessbtn = findViewById(R.id.guessbtn);
        guessedbox = findViewById(R.id.guessedbox);
        face = findViewById(R.id.face);
        gameturnbox = findViewById(R.id.gameturnbox);
        gameturnbox.setText("Game Turn: " + gameturn);
        guessbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                guessnum = Integer.parseInt((guessbox.getEditableText().toString()));
                if(guessnum == secretnum && counter < 5) {
                    face.setImageResource(R.drawable.shocked);
                    //Toast.makeText(MainActivity.this,"Total guess: "+counter,Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.drawable.shocked)
                            .setMessage("Bingo")
                            .setMessage("Total guess: " + counter)
                            .setPositiveButton("Replay?", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    secretnum = new Random().nextInt(10)+1;
                                    counter = 0;
                                    gameturn++;
                                    face.setVisibility(View.INVISIBLE);
                                    gameturnbox.setText("Game Turn: " + gameturn);
                                }
                            })
                            .show();
                }
                else if(guessnum == secretnum){
                    face.setImageResource(R.drawable.smile);
                    //Toast.makeText(MainActivity.this,"Total guess: "+counter,Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.drawable.smile)
                            .setMessage("Bingo")
                            .setMessage("Total guess: " + counter)
                            .setPositiveButton("Replay?", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    secretnum = new Random().nextInt(10)+1;
                                    counter = 0;
                                    gameturn++;
                                    face.setVisibility(View.INVISIBLE);
                                    gameturnbox.setText("Game Turn: " + gameturn);
                                    guessbox.setText(null);
                                }
                            })
                            .show();
                }
                else{
                    if(guessnum > secretnum){
                        //Toast.makeText(MainActivity.this,"Too Big", Toast.LENGTH_LONG).show();
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage("Smaller")
                                .setPositiveButton("OK",null)
                                .show();
                    }
                    if(guessnum < secretnum){
                        //Toast.makeText(MainActivity.this,"Too small", Toast.LENGTH_LONG).show();
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage("Bigger")
                                .setPositiveButton("OK",null)
                                .show();
                    }
                    if(counter > 10) face.setImageResource(R.drawable.laugh);
                    counter++;
                    if(!guessattempt.contains(guessnum)) guessattempt.add(String.valueOf(guessnum));
                    guessedbox.setText("Attempted Guess: " + guessattempt);
                    Toast.makeText(MainActivity.this,"guess: "+counter,Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
