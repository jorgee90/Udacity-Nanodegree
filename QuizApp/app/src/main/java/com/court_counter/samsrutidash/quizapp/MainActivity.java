package com.court_counter.samsrutidash.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.court_counter.samsrutidash.quizapp.MESSAGE";
    public final static String EXTRA_TOTALSCORE = "com.court_counter.samsrutidash.quizapp.TOTALSCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //   Method to check the solution of the Question 1

    public double scoreQ1 = 0;
    public void onCheckedQuestion1(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.q1_opt1:
                if (checked)
                    scoreQ1 = 1;
                    break;
            case R.id.q1_opt2:
                if (checked)
                    scoreQ1 = 0;
                    break;
            case R.id.q1_opt3:
                if (checked)
                    scoreQ1 = 0;
                break;
            case R.id.q1_opt4:
                if (checked)
                    scoreQ1 = 0;
                break;
        }
    }


    public double solutionQ1(){
        return scoreQ1;
    }
    //   Method to check the solution of the Question 2

    public double scoreQ2 = 0;
    public void onCheckedQuestion2(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.q2_opt1:
                if (checked)
                    scoreQ2 = 0;
                break;
            case R.id.q2_opt2:
                if (checked)
                    scoreQ2 = 0;
                break;
            case R.id.q2_opt3:
                if (checked)
                    scoreQ2 = 1;
                break;
            case R.id.q2_opt4:
                if (checked)
                    scoreQ2 = 0;
                break;
        }
    }


    public double solutionQ2(){
        return scoreQ2;
    }

    //   Method to check the solution of the Question 3
    public double solutionQ3(){

        double score = 0;
        CheckBox piedPiper = (CheckBox) findViewById(R.id.piedPiper);
        boolean isPiedPiper = piedPiper.isChecked();
        CheckBox verily = (CheckBox) findViewById(R.id.verily);
        boolean isVerily = verily.isChecked();
        CheckBox privateCore = (CheckBox) findViewById(R.id.privateCore);
        boolean isPrivateCore = privateCore.isChecked();
        CheckBox Oculus = (CheckBox) findViewById(R.id.oculusVR);
        boolean isOculusVR = Oculus.isChecked();

        if (isOculusVR){
            score = score - 0.5;
        }
        if (isPiedPiper){
            score = score  - 0.5;
        }
        if (isVerily){
            score = score + 0.5;
        }
        if (isPrivateCore){
            score = score + 0.5;
        }


        return score;
    }

    //   Method to check the solution of the Question 4
    public double solutionQ4(){

        double score = 0;
        EditText solution4 = (EditText) findViewById(R.id.answer4);
        String str = solution4.getText().toString();
        str = str = str.toUpperCase();
        if(str.equals("LARRY PAGE") ||str.equals("LAWRENCE PAGE") ){
            score = 1;
        }
        return score;
    }
    //   Method to check the solution of the Question 5
    public double solutionQ5(){

        double score = 0;
        EditText solution5 = (EditText) findViewById(R.id.answer5);
        String str = solution5.getText().toString();
        str = str.toLowerCase();
        if(str.equals("sundar pichai") ){
            score = 1;
        }
        return score;
    }



    //Method to calculate the Total Score
    private double calculateTotalScore(){
        double q1 = solutionQ1();
        double q2 = solutionQ1();;
        double q3 = solutionQ3();;
        double q4 = solutionQ4();
        double q5=  solutionQ5();

        return q1+q2+q3+q4+q5;
    }

    public void submitAnswers(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText userName = (EditText) findViewById(R.id.userName);
        String name = userName.getText().toString();
        double totalScore = calculateTotalScore();
        intent.putExtra(EXTRA_MESSAGE, name);
        intent.putExtra(EXTRA_TOTALSCORE, totalScore);
        //displayScore(name, totalScore);
        startActivity(intent);
    }


}
