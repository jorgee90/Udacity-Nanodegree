package com.court_counter.samsrutidash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int totalPointTeam1 = 0;
    int totalPointTeam2 = 0;

    public void addOnePointTeam1(View view){
        totalPointTeam1 = totalPointTeam1 + 1;
        displayScoreTeam1(totalPointTeam1);
    }
    public void addTwoPointTeam1(View view){
        totalPointTeam1 = totalPointTeam1 + 2;
        displayScoreTeam1(totalPointTeam1);
    }
    public void addThreePointTeam1(View view){
        totalPointTeam1 = totalPointTeam1 + 3;
        displayScoreTeam1(totalPointTeam1);
    }

    public void addOnePointTeam2(View view){
        totalPointTeam2 = totalPointTeam2 + 1;
        displayScoreTeam2(totalPointTeam2);
    }
    public void addTwoPointTeam2(View view){
        totalPointTeam2 = totalPointTeam2 + 2;
        displayScoreTeam2(totalPointTeam2);
    }
    public void addThreePointTeam2(View view){
        totalPointTeam2 = totalPointTeam2 + 3;
        displayScoreTeam2(totalPointTeam2);
    }

    public void resetButton(View view){
        totalPointTeam1 = 0;
        totalPointTeam2 = 0;
        displayScoreTeam1(totalPointTeam1);
        displayScoreTeam2(totalPointTeam2);

    }

    private void displayScoreTeam1 (int score){
        TextView displayPointsTeam1 = (TextView) findViewById(R.id.pointIncrementTeam1);
        displayPointsTeam1.setText(" "+score);
    }

    private void displayScoreTeam2 (int score){
        TextView displayPointsTeam2 = (TextView) findViewById(R.id.pointIncrementTeam2);
        displayPointsTeam2.setText(" "+score);
    }



}
