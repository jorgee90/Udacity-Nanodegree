package com.samsrutidash.habittracker;

/**
 * Created by samsrutidash on 6/30/2016.
 */
public class HabitDetails {
    int habitID;
    String habitTitle;

    public HabitDetails(){

    }

    public HabitDetails( String habitTitle){
        this.habitTitle = habitTitle;
    }
    //Getters

    public int getHabitID(){
        return habitID;
    }
    public String getHabitTitle(){
        return this.habitTitle;
    }


    //Setters
    public  void setHabitID(int id){
        this.habitID = id;
    }

    public  void setHabitTitle(String name){
        this.habitTitle = name;
    }


}

