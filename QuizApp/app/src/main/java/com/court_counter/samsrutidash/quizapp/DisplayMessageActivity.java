package com.court_counter.samsrutidash.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        displayScore(message, intent.getDoubleExtra(MainActivity.EXTRA_TOTALSCORE,0.0));
    }

    public void displayScore(String name, double score){
        TextView displayScoreOfTheUser = (TextView) findViewById(R.id.resultText);
        String message = "Hey, "+ name+ "\nYou scored " + score + " out of 5.";
        displayScoreOfTheUser.setText(message);
    }

}
